package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;

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

	public default EJoinConditionType getJoinConditionType(QUERY query, int joinIdx) {
		
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
	
	public default PreparedQueryMetaData makeMetaData(QUERY query) {

		// Figure whether requires parameters by recursing downwards 
		final boolean hasParams = QueryHelper.hasConditionParams(this, query);
		
		return new PreparedQueryMetaData(hasParams);
	}
	
	public default int getNumResultParts(QUERY query) {
		
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
	
	
	public default Object getAggregateDefault(QUERY query) {

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
			
		default:
			throw new UnsupportedOperationException("Unknown functionn " + function);
		}
		
		return ret;
	}
	
	
	public default boolean hasJoins(QUERY query) {
		return getJoinCount(query) != 0;
	}
	
	public default boolean joinHasConditions(QUERY query, int joinIdx) {
		return getJoinConditionCount(query, joinIdx) != 0;
	}
	
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

	CompiledFieldReference getMappingField(QUERY query, int mappingIdx);

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
	
	public default Method getForDebugRootConditionLhsMethod(QUERY query, int conditionIdx) {
		return getRootConditionLhs(query, conditionIdx).getGetter().getGetterMethod();
	}

	public default String getForDebugRootConditionValue(QUERY query, int conditionIdx) {
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
	
	/**
	 * Get max depth of dub conditions
	 * @param query query to get max depth for
	 * 
	 *  - returns -1 if has no conditions at all
	 *  - 0 should never occur
	 *  - returns 1 if only has conditions at the root level
	 *  - returns 2+ if eg. has a nested or inside where-and at the topmost level, etc 
	 *  
	 * @return max depth
	 */

	int getConditionsMaxDepth(QUERY query);
	
	
	// Generic nested-condition evaluation
	ConditionsType getConditionsType(QUERY query, int level, int [] conditionIndices);

	int getConditionSourceIdx(QUERY query, int level, int [] conditionIndices);

	boolean evaluateCondition(QUERY query, int level, int [] conditionIndices, Object instance, ConditionValuesScratch scratch);
	
	boolean isSubCondition(QUERY query, int level, int [] conditionIndices);
	
	int getConditionsCount(QUERY query, int level, int [] conditionIndices);

	EClauseOperator getOperator(QUERY query, int level, int [] conditionIndices);

	// for compiled-queries only?
	CompiledFieldReference getConditionLhs(QUERY query, int level, int [] conditionIndices);

	ConditionValue getConditionValue(QUERY query, int level, int [] conditionIndices);
	
	Method getForDebugConditionLhsMethod(QUERY query, int level, int [] conditionIndices);
	
	String getForDebugConditionValue(QUERY query, int level, int [] conditionIndices);
}
