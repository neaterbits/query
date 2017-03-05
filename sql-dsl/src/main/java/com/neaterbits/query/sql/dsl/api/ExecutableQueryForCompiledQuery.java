package com.neaterbits.query.sql.dsl.api;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.function.Function;

import com.neaterbits.query.sql.dsl.api.entity.IEntityAttribute;
import com.neaterbits.query.sql.dsl.api.entity.OneToManyJoinConditionResolver;
import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;
import com.neaterbits.query.sql.dsl.api.entity.Relation;

final class ExecutableQueryForCompiledQuery extends ExecutableQueryForCompiledBase implements ExecutableQuery<CompiledQuery> {

	private static final ExecutableQueryConditions_ForCompiledQuery_Conditions conditions
				= new ExecutableQueryConditions_ForCompiledQuery_Conditions();
	
	private static final ExecutableQueryConditions_ForCompiledQuery_Having having
				= new ExecutableQueryConditions_ForCompiledQuery_Having();
	
	@Override
	public ExecuteQueryScratch createScratchArea(CompiledQuery query, QueryMetaModel queryMetaModel) {
		final int numResultParts 	= getNumResultParts(query);
		final int numSelectSources 	= getAllSourceCount(query);
		final int numConditions	 	= getRootConditionCount(query);
		final int maxDepth 			= query.getConditionsMaxDepth();
		
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
	public ExecutableQueryConditions<CompiledQuery> getExecutableQueryConditions() {
		return conditions;
	}


	private static CompiledMappings getMappedResult(CompiledQuery query) {
		final CompiledQueryResult_Mapped mapped = (CompiledQueryResult_Mapped)query.getResult();
		
		return mapped.getMappings();
	}
	
	private static CompiledMapping getMapping(CompiledQuery query, int mappingIdx) {
		return getMappedResult(query).getMappings().get(mappingIdx);
	}
	
	@Override
	public int getMappingCount(CompiledQuery query) {

		return getMappedResult(query).getMappings().size();
	}

	@Override
	public int getMappingSourceIdx(CompiledQuery query, int mappingIdx) {

		return getMapping(query, mappingIdx).getField().getSource().getIdx();
	}
	
	@Override
	public int getMappingNumFunctions(CompiledQuery query, int mappingIdx) {
		return getMapping(query, mappingIdx).getNumFunctions();
	}

	@Override
	public FunctionBase getMappingFunction(CompiledQuery query, int mappingIdx, int functionIdx) {
		return getMapping(query, mappingIdx).getFunctionAt(functionIdx);
	}

	@Override
	public CompiledFieldReference getMappingField(CompiledQuery query, int mappingIdx) {
		return getMappedResult(query).getMappings().get(mappingIdx).getField();
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
	public int getRootConditionNumFunctions(CompiledQuery query, int conditionIdx) {
		return getRootComparisonCondition(query, conditionIdx).getNumFunctions();
	}


	@Override
	public FunctionCalcBase getRootConditionFunction(CompiledQuery query, int conditionIdx, int functionIdx) {
		return (FunctionCalcBase)getRootComparisonCondition(query, conditionIdx).getFunctionAt(functionIdx);
	}

	@Override
	public ConditionsType getRootConditionsType(CompiledQuery query) {
		
		final CompiledConditions conditions = query.getConditions();

		return ExecutableQueryForCompiledConditions.getConditionsType(conditions);
	}

	@Override
	public final int getRootConditionSourceIdx(CompiledQuery query, int conditionIdx) {
		return getRootComparisonCondition(query, conditionIdx).getLhsSource().getIdx();
	}

	@Override
	public final boolean evaluateRootCondition(CompiledQuery query, Object instance, int conditionIdx, ConditionValuesScratch scratch) {

		final CompiledConditionComparison condition = (CompiledConditionComparison)query.getConditions().getConditions().get(conditionIdx);

		return evaluateCondition(condition, instance, scratch);
	}
	

	@Override
	public final boolean isRootConditionOnly(CompiledQuery query) {

		final int maxDepth = query.getConditionsMaxDepth();
		final boolean ret;

		switch (maxDepth) {

		case -1:
			throw new IllegalStateException("-1 maxdepth so no conditions at all");

		case 0:
			throw new IllegalStateException("should never have max depth 0");
			
		case 1:
			ret = true;
			break;

		default:
			if (maxDepth < 0) {
				throw new IllegalStateException("negative maxdepth");
			}
			ret = false;
			break;
		}

		return ret;
	}

	//-------------------------------------------------------------------
	// Result processing
	//-------------------------------------------------------------------
	
	// --------------------- group by --------------------- 
	
	@Override
	public int getGroupByFieldCount(CompiledQuery query) {
		
		
		return query.getResultProcessing() == null
				
				? 0
				: query.getResultProcessing().getGroupBy() == null
					? 0
					: query.getResultProcessing().getGroupBy().getIndicesStartingAtOne().length;
	}


	@Override
	public int getGroupByFieldIndex(CompiledQuery query, int idx) {
		return query.getResultProcessing().getGroupBy().getIndicesStartingAtOne()[idx] - 1;
	}

	// --------------------- having ---------------------
	
	
	
	/*
	
	private CompiledConditionComparison getComparisonHaving(CompiledQuery query, int level, int[] conditionIndices) {

		final CompiledCondition condition = getHaving(query, level, conditionIndices);
		
		return (CompiledConditionComparison)condition;
	}

	
	private CompiledCondition getHaving(CompiledQuery query, int level, int[] conditionIndices) {
		// First find CompiledConditions
		CompiledConditions conditions = getHavingList(query, level, conditionIndices);

		return conditions.getConditions().get(conditionIndices[level]);
	}
	

	private CompiledConditions getHavingList(CompiledQuery query, int level, int[] conditionIndices) {
		return getConditionsList(query.getResultProcessing().getHaving().getConditions(), level, conditionIndices);
	}
	*/

	@Override
	public ExecutableQueryConditions<CompiledQuery> getExecutableQueryHaving() {
		return having;
	}


	@Override
	public boolean hasHaving(CompiledQuery query) {
		return query.getResultProcessing() != null && query.getResultProcessing().getHaving() != null;
	}
	
	// --------------------- order by ---------------------
	
	@Override
	public int getOrderByFieldCount(CompiledQuery query) {
		return query.getResultProcessing() == null
				
				? 0
				: query.getResultProcessing().getOrderBy() == null
					? 0
					: query.getResultProcessing().getOrderBy().getIndicesStartingAtOne().length;
	}


	@Override
	public int getOrderByFieldIndex(CompiledQuery query, int idx) {
		return query.getResultProcessing().getOrderBy().getIndicesStartingAtOne()[idx] - 1;
	}


	@Override
	public ESortOrder getOrderBySortOrder(CompiledQuery query, int idx) {
		return query.getResultProcessing().getOrderBy().getSortOrders()[idx];
	}

	@Override
	public Function<?, ?> getEntityOrderByFieldGetter(CompiledQuery query, int idx) {
		throw new UnsupportedOperationException("TODO");
	}
}
