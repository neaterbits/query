package com.neaterbits.query.sql.dsl.api;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

import com.neaterbits.query.sql.dsl.api.entity.IEntityAttribute;
import com.neaterbits.query.sql.dsl.api.entity.OneToManyJoinConditionResolver;
import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;
import com.neaterbits.query.sql.dsl.api.entity.Relation;
import com.neaterbits.query.sql.dsl.api.entity.ScalarType;

final class ExecutableQueryForCompiledQuery implements ExecutableQuery<CompiledQuery> {

	@Override
	public ExecuteQueryScratch createScratchArea(CompiledQuery query, QueryMetaModel queryMetaModel) {
		final int numResultParts 	= getNumResultParts(query);
		final int numSelectSources 	= getSourceCount(query);
		final int numConditions	 	= getConditionCount(query);
		
		return new ExecuteQueryScratch(numResultParts, numSelectSources, numConditions, queryMetaModel);
	}

	@Override
	public EQueryResultDimension getDimension(CompiledQuery query) {
		return query.getResultMode();
	}
	
	@Override
	public EQueryResultGathering getGathering(CompiledQuery query) {
		return query.getGathering();
	}

	@Override
	public EAggregateFunction getAggregateResultFunction(CompiledQuery query) {
		return ((QueryResultAggregate)query.getResult().getOriginal()).getAggregateFunction();
	}
	
	@Override
	public ENumericType getAggregateNumericInputType(CompiledQuery query) {
		return ((QueryResultAggregate)query.getResult().getOriginal()).getInputNumericType();
	}
	
	@Override
	public ENumericType getAggregateNumericOutputType(CompiledQuery query) {
		return ((QueryResultAggregate)query.getResult().getOriginal()).getOutputNumericType();
	}

	@Override
	public Object getAggregateResultValue(CompiledQuery query, Object instance) {
		return ((CompiledQueryResultAggregate)query.getResult()).getField().getValue(instance);
	}

	@Override
	public int getMappingCount(CompiledQuery query) {

		/*
		if (query.getGathering() != QueryResultGathering.MAPPED)) {
			throw new IllegalStateException("Expected mapped");
		}
		*/
		
		final CompiledQueryResultMapped mapped = (CompiledQueryResultMapped)query.getResult();
		
		return mapped.getMappings().getMappings().size();
	}

	@Override
	public int getMappingSourceIdx(CompiledQuery query, int mappingIdx) {
		final CompiledQueryResultMapped mapped = (CompiledQueryResultMapped)query.getResult();

		return mapped.getMappings().getMappings().get(mappingIdx).getField().getSource().getIdx();
	}

	@Override
	public Object createMappedInstance(CompiledQuery query) {
		
		final Class<?> type = query.getResult().getOriginal().getType();
		final Object ret;

		try {
			ret = type.newInstance();
		} catch (InstantiationException | IllegalAccessException ex) {
			throw new IllegalStateException("Failed to instantia type " + type);
		}
		
		return ret;
	}

	@Override
	public Object executeMappingGetter(CompiledQuery query, int mappingIdx, Object instance) {
		final CompiledQueryResultMapped mapped = (CompiledQueryResultMapped)query.getResult();
		
		final CompiledMapping mapping = mapped.getMappings().getMappings().get(mappingIdx);
		
		return mapping.executeGetter(instance);
	}

	@Override
	public void executeMappingSetter(CompiledQuery query, int mappingIdx, Object instance, Object value) {
		final CompiledQueryResultMapped mapped = (CompiledQueryResultMapped)query.getResult();

		mapped.getMappings().getMappings().get(mappingIdx).executeSetter(instance, value);
	}

	@Override
	public int getSourceCount(CompiledQuery query) {
		return query.getSelectSources().getSources().size();
	}

	@Override
	public int getJoinCount(CompiledQuery query) {
		final CompiledJoins joins = query.getJoins();
		
		return joins != null ? joins.getJoins().size() : 0;
	}

	@Override
	public JoinType getJoinType(CompiledQuery query, int joinIdx) {
		return getJoin(query, joinIdx).getJoinType();
	}

	@Override
	public int getJoinLeftSourceIdx(CompiledQuery query, int joinIdx) {
		return getJoin(query, joinIdx).getLeft().getIdx();
	}

	@Override
	public int getJoinRightSourceIdx(CompiledQuery query, int joinIdx) {
		return getJoin(query, joinIdx).getRight().getIdx();
	}

	
	@Override
	public int getJoinConditionCount(CompiledQuery query, int joinIdx) {
		return getJoin(query, joinIdx).getConditions().size();
	}

	/*
	@Override
	public ConditionsType getJoinConditionType(CompiledQuery query) {
		// TODO Auto-generated method stub
		return null;
	}
	*/

	
	@Override
	public int getJoinConditionLeftSourceIdx(CompiledQuery query, int joinIdx, int conditionIdx) {

		return getJoin(query, joinIdx).getConditions().get(conditionIdx).getLeft().getIdx();
	}

	@Override
	public int getJoinConditionRightSourceIdx(CompiledQuery query, int joinIdx, int conditionIdx) {

		return getJoin(query, joinIdx).getConditions().get(conditionIdx).getRight().getIdx();
	}
	
	private CompiledJoin getJoin(CompiledQuery query, int joinIdx) {
		final CompiledJoin join = query.getJoins().getJoins().get(joinIdx);

		return join;
	}

	@Override
	public boolean evaluateJoinCondition(CompiledQuery query, int joinIdx, Object instance1, Object instance2, int conditionIdx, OneToManyJoinConditionResolver oneToManyResolver) {
		
		final CompiledJoinCondition condition = query.getJoins().getJoins().get(joinIdx).getConditions().get(conditionIdx);
		
		// Evaluate now
		
		final boolean ret;
		
		if (condition instanceof CompiledJoinConditionComparison) {
			ret = evaluateJoinConditionComparison((CompiledJoinConditionComparison)condition, instance1, instance2);
		}
		else if (condition instanceof CompiledJoinConditionOneToMany) {
			// Check whether we are able to evaluate this using direct-mapping
			ret = evaluateJoinConditionOneToMany((CompiledJoinConditionOneToMany)condition, instance1, instance2, oneToManyResolver);
		}
		else {
			throw new UnsupportedOperationException("Unknown condition type " + condition.getClass().getSimpleName());
		}
		
		return ret;
	}

	private boolean evaluateJoinConditionOneToMany(CompiledJoinConditionOneToMany condition, Object instance1, Object instance2, OneToManyJoinConditionResolver oneToManyResolver) {
		
		final boolean ret;
		
		if (oneToManyResolver != null) {

			final Class<?> fromType   = condition.getLeft().getType();
			final Class<?> toType     = condition.getRight().getType();
			final Method getterMethod = condition.getCollectionGetter().getGetterMethod();
			
			final Relation resolved = oneToManyResolver.resolveOneToMany(
					fromType,
					toType,
					getterMethod);
			
			if (resolved == null) {
				throw new IllegalStateException("Failed to resolved getter " + getterMethod + " to " + toType + " from " + fromType);
			}

			// Look at resolved to evaluate
			final Object toVal = getValue(instance2, resolved.getTo().getAttribute());

			// True if points back to same instance
			// TODO: Check for equality instead of identity? But we do not have ID field
			ret = toVal == instance1;
		}
		else {
			throw new UnsupportedOperationException("TODO - resolve directly on lists, ie. without using resolving");
		}

		return ret;
	}

	private Object getValue(Object instance, IEntityAttribute attribute) {

		final Member member = attribute.getJavaMember();
		
		final Object ret;
		
		if (member instanceof Method) {
			try {
				ret = ((Method)member).invoke(instance);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
				throw new IllegalStateException("Failed to invoke getter", ex);
			}
		}
		else {
			throw new UnsupportedOperationException("Only methods supported for now");
		}
		
		return ret;
	}
	
	
	private static boolean evaluateJoinConditionComparison(CompiledJoinConditionComparison comparison, Object instance1, Object instance2) {

		final Object lhs = comparison.getLhs().getValue(instance1);
		final Object rhs = comparison.getRhs().getValue(instance2);

		return evaluateComparables(lhs, rhs);
	}
	
	@SuppressWarnings("unchecked")
	private static boolean evaluateComparables(Object lhs, Object rhs) {
		final boolean ret;
		
		if (lhs == null) {
			ret = rhs == null;
		}
		else if (rhs == null) {
			ret = true;
		}
		else {
			ret = ((Comparable<Object>)lhs).compareTo((Comparable<Object>)rhs) == 0;
		}
		
		return ret;
	}

	@Override
	public int getConditionCount(CompiledQuery query) {
		final CompiledConditions conditions = query.getConditions();
		
		return conditions != null ? conditions.getConditions().size() : 0;
	}

	@Override
	public ConditionsType getConditionsType(CompiledQuery query) {
		
		final CompiledConditions conditions = query.getConditions();
		
		final ConditionsType ret;
		
		if (conditions == null) {
			ret = ConditionsType.NONE;
		}
		else if (conditions instanceof CompiledConditionsAnd) {
			ret = ConditionsType.AND;
		}
		else if (conditions instanceof CompiledConditionsOr) {
			ret = ConditionsType.OR;
		}
		else if (conditions instanceof CompiledConditionsSingle) {
			ret = ConditionsType.SINGLE;
		}
		else {
			throw new UnsupportedOperationException("Unknown condition type "
							+ conditions.getClass().getSimpleName());
		}

		return ret;
	}

	@Override
	public int getConditionSourceIdx(CompiledQuery query, int conditionIdx) {
		return query.getConditions().getConditions().get(conditionIdx).getLhsSource().getIdx();
	}

	@Override
	public boolean evaluateCondition(CompiledQuery query, Object instance, int conditionIdx, ConditionValuesScratch scratch) {
		
		final CompiledCondition condition = query.getConditions().getConditions().get(conditionIdx);
		
		// Must evaluate condition with params.
		// First figure lhs
		final Object lhs  = condition.getLhs().getValue(instance);

		// Then figure rhs
		final Object rhs = condition.getValue().visit(valueVisitor, scratch.getCollectedParams());
		
		// At last, evaluate
		scratch.initConditionScratchValues(lhs, rhs);
		
		final ScalarType scalarType = condition.getScalarType();
		
		final boolean ret;
		
		if (scalarType == ScalarType.STRING) {
			ret = condition.getOriginal().visit(stringEvaluator, scratch);
		}
		else {
			ret = condition.getOriginal().visit(comparableEvaluator, scratch);
		}

		return ret;
	}

	private static final ConditionEvaluatorComparable comparableEvaluator = new ConditionEvaluatorComparable();
	private static final ConditionEvaluatorComparableString stringEvaluator = new ConditionEvaluatorComparableString();
	
	
	private static final ConditionValueVisitor<ParamValueResolver, Object> valueVisitor = new ConditionValueVisitor<ParamValueResolver, Object>() {
		
		@Override
		public Object onParam(ConditionValueParamImpl value, ParamValueResolver param) {
			return param.resolveParam(value.getParam());
		}
		
		@Override
		public Object onLiteralString(ConditionValueLiteralStringImpl value, ParamValueResolver param) {
			return value.getLiteral();
		}
		
		@Override
		public Object onLiteralAny(ConditionValueLiteralAnyImpl<?> value, ParamValueResolver param) {
			return value.getLiteral();
		}
		
		@Override
		public Object onGetter(ConditionValueGetterImpl value, ParamValueResolver param) {
			throw new UnsupportedOperationException("Should only call getters in joins");
		}
		
		@Override
		public Object onFieldReference(ConditionValueFieldRerefenceImpl value, ParamValueResolver param) {
			throw new UnsupportedOperationException("Should only call getters in joins");
		}
		
		@Override
		public Object onArray(ConditionValueArrayImpl value, ParamValueResolver param) {
			return value.getValues();
		}
	};
}
