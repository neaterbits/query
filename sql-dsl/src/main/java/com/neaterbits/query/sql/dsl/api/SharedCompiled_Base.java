package com.neaterbits.query.sql.dsl.api;

abstract class SharedCompiled_Base<RESULT, RESULT_INSTANCE_TYPE> implements Query<RESULT_INSTANCE_TYPE> {

	private final CompiledQuery compiledQuery;
	
	SharedCompiled_Base(CompiledQuery compiledQuery) {
		this.compiledQuery = compiledQuery;
	}

	final CompiledQuery getCompiledQuery() {
		return compiledQuery;
	}
}
