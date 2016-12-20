package com.neaterbits.query.sql.dsl.api;

abstract class BasePojoPreparedQuery<QUERY> implements DSPreparedQuery {

	private final QUERY query;
	private final ExecuteQueryPOJOs<QUERY> executor;
	private final ExecuteQueryPOJOsInput input;
	
	BasePojoPreparedQuery(QUERY query, ExecuteQueryPOJOs<QUERY> executor, ExecuteQueryPOJOsInput input) {
		
		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}
		
		if (executor == null) {
			throw new IllegalArgumentException("executor == null");
		}
		
		if (input == null) {
			throw new IllegalArgumentException("input == null"); 
		}

		this.query = query;
		this.executor = executor;
		this.input = input;
	}

	@Override
	public Object execute(ParamValueResolver collectedParams) {
		return executor.execute(query, input, collectedParams);
	}
}
