package com.neaterbits.query.sql.dsl.api;


final class QueryDataSourcePojoWithInput extends QueryDataSourcePojoBase {

	private final ExecuteQueryPOJOsInput input;

	QueryDataSourcePojoWithInput(ExecuteQueryPOJOsInput input) {
		this.input = input;
	}

	@Override
	DSPreparedQuery prepareSingleQuery(CompiledQuery compiled) {
		return new PojoPreparedQuery(compiled, input);
	}

	@Override
	DSPreparedQuery prepareMultiQuery(CompiledQuery compiled) {
		return new PojoPreparedQuery(compiled, input);
	}
}
