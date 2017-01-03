package com.neaterbits.query.sql.dsl.api;

abstract class SharedQuery_Base<RESULT, RESULT_INSTANCE_TYPE> implements Query<RESULT_INSTANCE_TYPE> {

	static final ExecutableQueryForCompiledQuery q = new ExecutableQueryForCompiledQuery();

	private final CompiledQuery compiledQuery;
	
	SharedQuery_Base(CompiledQuery compiledQuery) {
		this.compiledQuery = compiledQuery;
	}

	final CompiledQuery getCompiledQuery() {
		return compiledQuery;
	}
}
