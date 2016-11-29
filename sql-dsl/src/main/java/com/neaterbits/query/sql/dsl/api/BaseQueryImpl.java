package com.neaterbits.query.sql.dsl.api;

abstract class BaseQueryImpl<RESULT, RESULT_INSTANCE_TYPE> implements Query<RESULT_INSTANCE_TYPE> {

	private final CompiledQuery compiledQuery;
	
	BaseQueryImpl(CompiledQuery compiledQuery) {
		this.compiledQuery = compiledQuery;
	}

	final CompiledQuery getCompiledQuery() {
		return compiledQuery;
	}
}
