package com.neaterbits.query.sql.dsl.api;

final class PojoPreparedQuery extends BasePojoPreparedQuery<CompiledQuery> {

	private static final ExecutableQueryForCompiledQuery executableQueryForCompiledQuery = new ExecutableQueryForCompiledQuery();
	private static final ExecuteQueryPOJOs<CompiledQuery> executeQueryPOJOs = new ExecuteQueryPOJOs<>(executableQueryForCompiledQuery);

	PojoPreparedQuery(CompiledQuery query, ExecuteQueryPOJOsInput input) {
		super(query, executeQueryPOJOs, input);
	}
}
