package com.neaterbits.query.sql.dsl.api;

abstract class BaseQueryImpl<RESULT> implements Query<RESULT> {

	private final CompiledQuery compiledQuery;
	
	BaseQueryImpl(CompiledQuery compiledQuery) {
		this.compiledQuery = compiledQuery;
	}

	final CompiledQuery getCompiledQuery() {
		return compiledQuery;
	}
}
