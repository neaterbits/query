package com.neaterbits.query.sql.dsl.api;

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
	
	
	ExecuteQueryScratch createScratchArea(QUERY query, QueryMetaModel queryMetaModel);
	
	ECollectionType getResultCollectionType(QUERY query);
	
	
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

	ENumericType getAggregateNumericInputType(QUERY query);

	ENumericType getAggregateNumericOutputType(QUERY query);
	
	Object getAggregateResultValue(QUERY query, Object instance);
	
	
	/**
	 * Get count of mappings from result
	 * @param query the query
	 * 
	 * @return number of mappings
	 */
	
	int getMappingCount(QUERY query);
	
	int getMappingSourceIdx(QUERY query, int mappingIdx);
	

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
	
	int getSourceCount(QUERY query);
	
	
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
	
	int getJoinConditionCount(QUERY query, int joinIdx);

	//ConditionsType getJoinConditionType(QUERY query);
	
	// TODO: Might make sure through validation that is always the same as the main left and source indices
	// but for now, just assume they may be differ
	int getJoinConditionLeftSourceIdx(QUERY query, int joinIdx, int conditionIdx);
	int getJoinConditionRightSourceIdx(QUERY query, int joinIdx, int conditionIdx);
	
	boolean evaluateJoinCondition(QUERY query, int joinIdx, Object instance1, Object instance2, int conditionIdx, OneToManyJoinConditionResolver oneToManyResolver);
	
	
	/**
	 * 
	 */
	
	int getConditionCount(QUERY query);

	/**
	 * Get type of condition for this query
	 * @param query
	 * @return
	 */
	
	ConditionsType getConditionsType(QUERY query);
	
	int getConditionSourceIdx(QUERY query, int conditionIdx);

	boolean evaluateCondition(QUERY query, Object instance, int conditionIdx, ConditionValuesScratch scratch);
	
}
