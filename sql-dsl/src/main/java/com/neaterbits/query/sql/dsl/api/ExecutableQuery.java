package com.neaterbits.query.sql.dsl.api;

/**
 * Interface access to query, so that can run in an abstracted way
 * and only operating on one interface, completely hiding
 * the internal memory representation.
 * 
 * @author nhl
 *
 */

interface ExecutableQuery<QUERY> {
	
	enum ConditionType {
		AND,
		OR,
		SINGLE,
		NONE;
	}
	

	/**
	 * Result mode, whether produces a single or multiple results
	 * 
	 * @param query query
	 * 
	 * @return mode - single or multi response
	 */
	
	QueryResultDimension getResultMode(QUERY query);

	/**
	 * Get count of mappings from result
	 * @param query the query
	 * 
	 * @return number of mappings
	 */
	
	int getMappingCount(QUERY query);


	
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
	
	
	JoinType getJoinType(QUERY query, int joinIdx);
	
	
	int getJoinLeftSourceIdx(QUERY query, int joinIdx);
	
	int getJoinRightSourceIdx(QUERY query, int joinIdx);
	
	
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
	 * 
	 */
	
	int getConditionCount(QUERY query);

	ConditionType getConditionType(QUERY query);
	
	int getConditionSourceIdx(QUERY query, int conditionIdx);

	boolean evaluateCondition(QUERY query, Object instance, int conditionIdx);
	
}
