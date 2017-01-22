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
		final int numSelectSources 	= getAllSourceCount(query);
		final int numConditions	 	= getRootConditionCount(query);
		final int maxDepth 			= getConditionsMaxDepth(query);
		
		return new ExecuteQueryScratch(numResultParts, numSelectSources, numConditions, queryMetaModel, maxDepth);
	}

	
	@Override
	public FieldReferenceType getQueryFieldReferenceType(CompiledQuery query) {
		
		final CompiledSelectSources<?> source = query.getSelectSources();
		
		final FieldReferenceType ret;
		
		if (source instanceof CompiledSelectSources_Alias) {
			ret = FieldReferenceType.ALIAS;
		}
		else if (source instanceof CompiledSelectSources_Named) {
			ret = FieldReferenceType.ENTITY;
		}
		else {
			throw new IllegalStateException("Unknown select source class " + source.getClass());
		}
		return ret;
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
	public Class<?> getResultJavaType(CompiledQuery query) {
		return query.getResultType();
	}

	@Override
	public ECollectionType getResultCollectionType(CompiledQuery query) {
		return query.getResult().getCollectionType();
	}

	@Override
	public EAggregateFunction getAggregateResultFunction(CompiledQuery query) {
		return ((CompiledQueryResult_Aggregate)query.getResult()).getAggregateFunction();
	}
	
	@Override
	public CompiledFieldReference getAggregateResultField(CompiledQuery query) {
		return ((CompiledQueryResult_Aggregate)query.getResult()).getField();
	}

	@Override
	public ENumericType getAggregateNumericInputType(CompiledQuery query) {
		return ((CompiledQueryResult_Aggregate)query.getResult()).getInputNumericType();
	}
	
	@Override
	public ENumericType getAggregateNumericOutputType(CompiledQuery query) {
		return ((CompiledQueryResult_Aggregate)query.getResult()).getOutputNumericType();
	}

	@Override
	public Object getAggregateResultValue(CompiledQuery query, Object instance) {
		return ((CompiledQueryResult_Aggregate)query.getResult()).getField().getValue(instance);
	}

	@Override
	public int getEntityResultSourceIdx(CompiledQuery query) {
		final SharedSelectSource selectSource = ((CompiledQueryResult_Entity)query.getResult()).getSelectSource();
		
		return query.getSelectSources().getSourceIdx(selectSource);
	}


	@Override
	public int getMappingCount(CompiledQuery query) {

		/*
		if (query.getGathering() != QueryResultGathering.MAPPED)) {
			throw new IllegalStateException("Expected mapped");
		}
		*/
		
		final CompiledQueryResult_Mapped mapped = (CompiledQueryResult_Mapped)query.getResult();
		
		return mapped.getMappings().getMappings().size();
	}

	@Override
	public int getMappingSourceIdx(CompiledQuery query, int mappingIdx) {
		final CompiledQueryResult_Mapped mapped = (CompiledQueryResult_Mapped)query.getResult();

		return mapped.getMappings().getMappings().get(mappingIdx).getField().getSource().getIdx();
	}

	@Override
	public CompiledFieldReference getMappingField(CompiledQuery query, int mappingIdx) {
		final CompiledQueryResult_Mapped mapped = (CompiledQueryResult_Mapped)query.getResult();

		return mapped.getMappings().getMappings().get(mappingIdx).getField();
	}

	@Override
	public Object createMappedInstance(CompiledQuery query) {
		
		final Class<?> type = query.getResult().getResultType();
		final Object ret;

		try {
			ret = type.newInstance();
		} catch (InstantiationException | IllegalAccessException ex) {
			throw new IllegalStateException("Failed to instantiate type " + type);
		}
		
		return ret;
	}

	@Override
	public Object executeMappingGetter(CompiledQuery query, int mappingIdx, Object instance) {
		final CompiledQueryResult_Mapped mapped = (CompiledQueryResult_Mapped)query.getResult();
		
		final CompiledMapping mapping = mapped.getMappings().getMappings().get(mappingIdx);
		
		return mapping.executeGetter(instance);
	}

	@Override
	public void executeMappingSetter(CompiledQuery query, int mappingIdx, Object instance, Object value) {
		final CompiledQueryResult_Mapped mapped = (CompiledQueryResult_Mapped)query.getResult();

		mapped.getMappings().getMappings().get(mappingIdx).executeSetter(instance, value);
	}
	
	@Override
	public int getAllSourceCount(CompiledQuery query) {
		return query.getSelectSources().getSources().size();
	}

	@Override
	public Class<?> getSourceJavaType(CompiledQuery query, int sourceIdx) {
		return query.getSelectSources().getSources().get(sourceIdx).getType();
	}

	@Override
	public String getSourceName(CompiledQuery query, int sourceIdx) {
		return query.getSelectSources().getSources().get(sourceIdx).getName();
	}

	@Override
	public Class<?>[] getSelectSourceClasses(CompiledQuery query) {
		return query.getSelectSourceClasses();
	}

	
	@Override
	public int getJoinCount(CompiledQuery query) {
		final CompiledJoins joins = query.getJoins();
		
		return joins != null ? joins.getJoins().size() : 0;
	}

	@Override
	public EJoinType getJoinType(CompiledQuery query, int joinIdx) {
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
	
	@Override
	public EJoinConditionType getJoinConditionType(CompiledQuery query, int joinIdx, int conditionIdx) {
		return getJoin(query, joinIdx).getConditions().get(conditionIdx).getJoinConditionType();
	}

	@Override
	public Class<?> getJoinLeftJavaType(CompiledQuery query, int joinIdx) {
		return getJoin(query, joinIdx).getLeft().getType();
	}

	@Override
	public Class<?> getJoinRightJavaType(CompiledQuery query, int joinIdx) {
		return getJoin(query, joinIdx).getRight().getType();
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
	
	@Override
	public String getJoinConditionLeftName(CompiledQuery query, int joinIdx, int conditionIdx) {
		return getJoin(query, joinIdx).getConditions().get(conditionIdx).getLeft().getName();
	}

	@Override
	public String getJoinConditionRightName(CompiledQuery query, int joinIdx, int conditionIdx) {
		return getJoin(query, joinIdx).getConditions().get(conditionIdx).getLeft().getName();
	}
	
	@Override
	public Class<?> getJoinConditionLeftJavaType(CompiledQuery query, int joinIdx, int conditionIdx) {
		return getJoin(query, joinIdx).getConditions().get(conditionIdx).getLeft().getType();
	}

	@Override
	public Class<?> getJoinConditionRightJavaType(CompiledQuery query, int joinIdx, int conditionIdx) {
		return getJoin(query, joinIdx).getConditions().get(conditionIdx).getRight().getType();
	}
	
	@Override
	public Method getJoinConditionOneToManyCollectionGetter(CompiledQuery query, int joinIdx, int conditionIdx) {
		
		final CompiledJoinCondition_OneToMany condition = (CompiledJoinCondition_OneToMany)getJoin(query, joinIdx).getConditions().get(conditionIdx);

		return condition.getCollectionGetter().getGetterMethod();
	}
	
	@Override
	public CompiledFieldReference getJoinConditionComparisonLhs(CompiledQuery query, int joinIdx, int conditionIdx) {
		final CompiledJoinCondition_Comparison condition = (CompiledJoinCondition_Comparison)getJoin(query, joinIdx).getConditions().get(conditionIdx);

		return condition.getLhs();
	}

	@Override
	public CompiledFieldReference getJoinConditionComparisonRhs(CompiledQuery query, int joinIdx, int conditionIdx) {
		final CompiledJoinCondition_Comparison condition = (CompiledJoinCondition_Comparison)getJoin(query, joinIdx).getConditions().get(conditionIdx);

		return condition.getRhs();
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
		
		if (condition instanceof CompiledJoinCondition_Comparison) {
			ret = evaluateJoinConditionComparison((CompiledJoinCondition_Comparison)condition, instance1, instance2);
		}
		else if (condition instanceof CompiledJoinCondition_OneToMany) {
			// Check whether we are able to evaluate this using direct-mapping
			ret = evaluateJoinConditionOneToMany((CompiledJoinCondition_OneToMany)condition, instance1, instance2, oneToManyResolver);
		}
		else {
			throw new UnsupportedOperationException("Unknown condition type " + condition.getClass().getSimpleName());
		}
		
		return ret;
	}

	private boolean evaluateJoinConditionOneToMany(CompiledJoinCondition_OneToMany condition, Object instance1, Object instance2, OneToManyJoinConditionResolver oneToManyResolver) {
		
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
	
	
	private static boolean evaluateJoinConditionComparison(CompiledJoinCondition_Comparison comparison, Object instance1, Object instance2) {

		final Object lhs = comparison.getLhs().getValue(instance1);
		final Object rhs = comparison.getRhs().getValue(instance2);

		return EvaluateUtil.evaluateComparables(lhs, rhs);
	}
	
	
	@Override
	public boolean hasConditions(CompiledQuery query) {
		
		final CompiledConditions conditions = query.getConditions();
		
		return conditions != null && !conditions.getConditions().isEmpty();
	}

	@Override
	public int getRootConditionCount(CompiledQuery query) {
		final CompiledConditions conditions = query.getConditions();
		
		return conditions != null ? conditions.getConditions().size() : 0;
	}

	private CompiledConditionComparison getRootComparisonCondition(CompiledQuery query, int conditionIdx) {
		return (CompiledConditionComparison)query.getConditions().getConditions().get(conditionIdx);
	}
	
	@Override
	public EClauseOperator getRootConditionOperator(CompiledQuery query, int conditionIdx) {

		return getRootComparisonCondition(query, conditionIdx).getOperator();
	}
	
	
	@Override
	public CompiledFieldReference getRootConditionLhs(CompiledQuery query, int conditionIdx) {

		return getRootComparisonCondition(query, conditionIdx).getLhs();
	}

	@Override
	public ConditionValue getRootConditionValue(CompiledQuery query, int conditionIdx) {

		return getRootComparisonCondition(query, conditionIdx).getValue();
	}

	@Override
	public ConditionsType getRootConditionsType(CompiledQuery query) {
		
		final CompiledConditions conditions = query.getConditions();
		
		final ConditionsType ret;
		
		if (conditions == null) {
			ret = ConditionsType.NONE;
		}
		else if (conditions instanceof CompiledConditions_And) {
			ret = ConditionsType.AND;
		}
		else if (conditions instanceof CompiledConditions_Or) {
			ret = ConditionsType.OR;
		}
		else if (conditions instanceof CompiledConditions_Single) {
			ret = ConditionsType.SINGLE;
		}
		else {
			throw new UnsupportedOperationException("Unknown condition type "
							+ conditions.getClass().getSimpleName());
		}

		return ret;
	}

	@Override
	public int getRootConditionSourceIdx(CompiledQuery query, int conditionIdx) {
		
		return getRootComparisonCondition(query, conditionIdx).getLhsSource().getIdx();
	}

	@Override
	public boolean evaluateRootCondition(CompiledQuery query, Object instance, int conditionIdx, ConditionValuesScratch scratch) {
		
		final CompiledConditionComparison condition = (CompiledConditionComparison)query.getConditions().getConditions().get(conditionIdx);
		
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
	
	

	@Override
	public boolean isRootConditionOnly(CompiledQuery query) {
		return true; // For now
	}

	@Override
	public int getConditionsMaxDepth(CompiledQuery query) {
		return 1; // For now
	}

	@Override
	public ConditionsType getConditionsType(CompiledQuery query, int level, int[] conditionIndices) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public int getConditionSourceIdx(CompiledQuery query, int level, int[] conditionIndices) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public boolean isSubCondition(CompiledQuery query, int level, int[] conditionIndices) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public int getConditionsCount(CompiledQuery query, int level, int[] conditionIndices) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public EClauseOperator getOperator(CompiledQuery query, int level, int[] conditionIndices) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public boolean evaluateCondition(CompiledQuery query, int level, int[] conditionIndices, Object instance, ConditionValuesScratch scratch) {
		throw new UnsupportedOperationException("TODO");
	}
	
	@Override
	public Method getForDebugConditionLhsMethod(CompiledQuery query, int level, int[] conditionIndices) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public String getForDebugConditionValue(CompiledQuery query, int level, int[] conditionIndices) {
		throw new UnsupportedOperationException("TODO");
	}


	private static final ConditionEvaluator_Comparable comparableEvaluator = new ConditionEvaluator_Comparable();
	private static final ConditionEvaluator_Comparable_String stringEvaluator = new ConditionEvaluator_Comparable_String();
	
	
	private static final ConditionValueVisitor<ParamValueResolver, Object> valueVisitor = new ConditionValueVisitor<ParamValueResolver, Object>() {
		
		@Override
		public Object onParam(ConditionValue_Param value, ParamValueResolver param) {
			return param.resolveParam(value.getParam());
		}
		
		@Override
		public Object onLiteralString(ConditionValue_Literal_String value, ParamValueResolver param) {
			return value.getLiteral();
		}
		
		@Override
		public Object onLiteralAny(ConditionValue_Literal_Any<?> value, ParamValueResolver param) {
			return value.getLiteral();
		}
		
		@Override
		public Object onGetter(ConditionValue_Getter value, ParamValueResolver param) {
			throw new UnsupportedOperationException("Should only call getters in joins");
		}
		
		@Override
		public Object onFieldReference(ConditionValue_FieldRerefence value, ParamValueResolver param) {
			throw new UnsupportedOperationException("Should only call getters in joins");
		}
		
		@Override
		public Object onArray(ConditionValue_Array value, ParamValueResolver param) {
			return value.getValues();
		}
	};
}
