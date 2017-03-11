package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.neaterbits.query.sql.dsl.api.entity.OneToManyJoinConditionResolver;
import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

/**
 * Interface access to query, so that can run in an abstracted way
 * and only operating on one interface, completely hiding
 * the internal memory representation.
 * 
 * @author nhl
 *
 */

interface ExecutableQuery<QUERY> {

	default EJoinConditionType getJoinConditionType(QUERY query, int joinIdx) {
		
		final int numConditions = getJoinConditionCount(query, joinIdx);
		
		if (numConditions == 0) {
			throw new IllegalStateException("numConditions == 0");
		}
		
		EJoinConditionType found = null;
		
		for (int conditionIdx = 0; conditionIdx < numConditions; ++ conditionIdx) {

			final EJoinConditionType conditionType = getJoinConditionType(query, joinIdx, conditionIdx);
			
			if (conditionType == null) {
				throw new IllegalStateException("conditionType == null");
			}
			
			if (found == null) {
				found = conditionType;
			}
			else {
				if (found != conditionType) {
					throw new IllegalStateException("Multiple condtition types found at " + joinIdx); 
				}
				
			}
		}
		
		if (found == null) {
			throw new IllegalStateException("found == null");
		}
		

		return found;
	}
	
	default QueryParametersDistinct getDistinctParams(QUERY query) {
		return new QueryParametersDistinct(QueryHelper.getConditionParamRefs(this, getExecutableQueryConditions(), query, true));
	}
	
	
	default PreparedQueryMetaData makeMetaData(QUERY query) {

		// Figure whether requires parameters by recursing downwards 
		final boolean hasParams = QueryHelper.hasConditionParams(this, getExecutableQueryConditions(), query);
		
		return new PreparedQueryMetaData(hasParams);
	}
	
	default int getNumResultParts(QUERY query) {
		
		final int numResultParts;
		final EQueryResultGathering gathering = getGathering(query);

		switch (gathering) {
		case AGGREGATE:
		case ENTITY:
			numResultParts = 1;
			break;
			
		case MAPPED:
			numResultParts = getMappingCount(query);
			break;

		default:
			throw new UnsupportedOperationException("Unknown query gathering " + gathering);
		}

		return numResultParts;
	}


	public static Object getZeroValue(ENumericType numericType) {
		
		final Object ret;
		
		switch (numericType) {
		case SHORT:
			ret = (short)0;
			break;

		case INTEGER:
			ret = 0;
			break;

		case LONG:
			ret = 0L;
			break;

		case BIGINT:
			ret = BigInteger.ZERO;
			break;

		case DECIMAL:
			ret = BigDecimal.ZERO;
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown numeric type " + numericType);
		}

		return ret;
	}
	
	
	default Object getAggregateDefault(QUERY query) {

		final  EAggregateFunction function = getAggregateResultFunction(query);
		final  ENumericType numericOutputType = getAggregateNumericInputType(query);

		final Object ret;

		switch (function) {
		case AVG:
		case COUNT:
		case SUM:
			ret = getZeroValue(numericOutputType);
			break;
			
		case MIN:
		case MAX:
			ret = null;
			break;

		case MIN_INSTANCE:
		case MAX_INSTANCE:
			ret = null;
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown function " + function);
		}
		
		return ret;
	}
	
	
	default boolean hasJoins(QUERY query) {
		return getJoinCount(query) != 0;
	}
	
	default boolean joinHasConditions(QUERY query, int joinIdx) {
		return getJoinConditionCount(query, joinIdx) != 0;
	}
	
	ExecutableQueryConditions<QUERY> getExecutableQueryConditions();
	
	
	ExecuteQueryScratch createScratchArea(QUERY query, QueryMetaModel queryMetaModel);
	
	ECollectionType getResultCollectionType(QUERY query);

	Class<?> getResultJavaType(QUERY query);
	
	/**
	 * Either query is all references or it is all entities
	 * 
	 * @return
	 */
	
	FieldReferenceType getQueryFieldReferenceType(QUERY query);
	
	
	/**
	 * Result mode, whether produces a single or multiple results
	 * 
	 * @param query query
	 * 
	 * @return mode - single or multi response
	 */
	
	EQueryResultDimension getDimension(QUERY query);

	EQueryResultGathering getGathering(QUERY query);
	
	EAggregateFunction getAggregateResultFunction(QUERY query);

	CompiledFieldReference getAggregateResultField(QUERY query);

	ENumericType getAggregateNumericInputType(QUERY query);

	ENumericType getAggregateNumericOutputType(QUERY query);
	
	Object getAggregateResultValue(QUERY query, Object instance);
	
	// Entity results
	int getEntityResultSourceIdx(QUERY query);

	/**
	 * Get count of mappings from result
	 * @param query the query
	 * 
	 * @return number of mappings
	 */
	
	int getMappingCount(QUERY query);
	
	int getMappingSourceIdx(QUERY query, int mappingIdx);

	// TODO: perhaps should nest expression as well the same way as for conditions as to avoid construction of Expression instances in Adhoc queries?
	// TODO: must use switch-case on type if so, but for now just return nested-expressions
	CompiledExpression getMappingExpression(QUERY query, int mappingIdx);
	
	@Deprecated
	CompiledFieldReference getMappingField(QUERY query, int mappingIdx);
	
	// Can perform mutiple builtin functions within mapping
	@Deprecated
	int getMappingNumFunctions(QUERY query, int mappingIdx);

	@Deprecated
	FunctionBase getMappingFunction(QUERY query, int mappingIdx, int functionIdx);

	Object createMappedInstance(QUERY query);


	/**
	 * Execute mapping getter on a particular instance
	 * 
	 * @param query a query
	 * @param mappingIdx index of mapping, 0 to getMappingCount() - 1
	 * @param instance instance to perform getter on
	 * @return
	 */
	
	Object executeMappingGetter(QUERY query, int mappingIdx, Object instance);
	
	/**
	 * Set a value in mapping
	 * 
	 * @param query a query
	 * @param mappingIdx index of mapping
	 * @param instance the instance to set value on
	 * @param value value to set
	 */
	
	void executeMappingSetter(QUERY query, int mappingIdx, Object instance, Object value);

	/**
	 * Get the total number of sources (eg. what is in "from" of an SQL)
	 * 
	 * @return
	 */

	
	int getAllSourceCount(QUERY query);
	
	Class<?> getSourceJavaType(QUERY query, int sourceIdx);
	
	String getSourceName(QUERY query, int sourceIdx);
	
	Class<?> [] getSelectSourceClasses(QUERY query);	
	
	/**
	 * Get the total number of Joins
	 * @param query the query
	 * 
	 * @return number of joins
	 */
	
	int getJoinCount(QUERY query);
	
	EJoinType getJoinType(QUERY query, int joinIdx);
	
	int getJoinLeftSourceIdx(QUERY query, int joinIdx);
	
	int getJoinRightSourceIdx(QUERY query, int joinIdx);

	Class<?> getJoinLeftJavaType(QUERY query, int joinIdx);
	
	Class<?> getJoinRightJavaType(QUERY query, int joinIdx);
	
	int getJoinConditionCount(QUERY query, int joinIdx);
	
	EJoinConditionType getJoinConditionType(QUERY query, int joinIdx, int conditionIdx);
	
	// TODO: Might make sure through validation that is always the same as the main left and source indices
	// but for now, just assume they may be differ
	int getJoinConditionLeftSourceIdx(QUERY query, int joinIdx, int conditionIdx);

	int getJoinConditionRightSourceIdx(QUERY query, int joinIdx, int conditionIdx);

	String getJoinConditionLeftName(QUERY query, int joinIdx, int conditionIdx);

	String getJoinConditionRightName(QUERY query, int joinIdx, int conditionIdx);
	
	Class<?> getJoinConditionLeftJavaType(QUERY query, int joinIdx, int conditionIdx);

	Class<?> getJoinConditionRightJavaType(QUERY query, int joinIdx, int conditionIdx);
	
	Method getJoinConditionOneToManyCollectionGetter(QUERY query, int joinIdx, int conditionIdx);

	CompiledFieldReference getJoinConditionComparisonLhs(QUERY query, int joinIdx, int conditionIdx);
	
	CompiledFieldReference getJoinConditionComparisonRhs(QUERY query, int joinIdx, int conditionIdx);
	
	boolean evaluateJoinCondition(QUERY query, int joinIdx, Object instance1, Object instance2, int conditionIdx, OneToManyJoinConditionResolver oneToManyResolver);


	boolean hasConditions(QUERY query);
	
	/**
	 * Query has only root conditions
	 * @param query
	 * @return 
	 */
	
	boolean isRootConditionOnly(QUERY query);
	
	
	/**
	 * 
	 */
	
	int getRootConditionCount(QUERY query);
	
	CompiledFieldReference getRootConditionLhs(QUERY query, int conditionIdx);


	
	// TODO: convert this somehow to enum or simila
	ConditionValue getRootConditionValue(QUERY query, int conditionIdx);
	
	EClauseOperator getRootConditionOperator(QUERY query, int conditionIdx);
	
	int getRootConditionNumFunctions(QUERY query, int conditionIdx);

	FunctionCalcBase getRootConditionFunction(QUERY query, int conditionIdx, int functionIdx);
	
	default List<FunctionCalcBase> getRootConditionFunctions(QUERY query, int conditionIdx) {
		final int numFunctions = getRootConditionNumFunctions(query, conditionIdx);
		
		final List<FunctionCalcBase> ret;
		
		if (numFunctions == 0) {
			ret = null;
		}
		else {
			ret = new ArrayList<>(numFunctions);
		
			for (int i = 0; i < numFunctions; ++ i) {
				
				final FunctionCalcBase function = getRootConditionFunction(query, conditionIdx, i);
				
				if (function == null) {
					throw new IllegalStateException("function == null");
				}
	
				ret.add(function);
			}
		}
		
		return ret;
	}
	
	default Method getForDebugRootConditionLhsMethod(QUERY query, int conditionIdx) {
		return getRootConditionLhs(query, conditionIdx).getGetter().getGetterMethod();
	}

	default String getForDebugRootConditionValue(QUERY query, int conditionIdx) {
		return getRootConditionValue(query, conditionIdx).toString();
	}
	
	/**
	 * Get type of condition for this query
	 * @param query
	 * @return
	 */

	ConditionsType getRootConditionsType(QUERY query);

	int getRootConditionSourceIdx(QUERY query, int conditionIdx);

	boolean evaluateRootCondition(QUERY query, Object instance, int conditionIdx, ConditionValuesScratch scratch);

	
	// result grouping, filtering and sorting
	
	int getGroupByFieldCount(QUERY query);

	// returns index into selected fields, starting at 0 (not 1 as is the case with SQL)
	int getGroupByFieldIndex(QUERY query, int idx);

	ExecutableQueryConditions<QUERY> getExecutableQueryHaving();

	boolean hasHaving(QUERY query);
	

	int getOrderByFieldCount(QUERY query);

	// returns index into selected fields, starting at 0 (not 1 as is the case with SQL)
	int getOrderByFieldIndex(QUERY query, int idx);

	ESortOrder getOrderBySortOrder(QUERY query, int idx);
	
	// For POJO execution with order-by on entities, we require the Function
	// TODO: perhaps change to executeEntityFieldGetter with an index?
	Function<?, ?> getEntityOrderByFieldGetter(QUERY query, int idx);
	
}
