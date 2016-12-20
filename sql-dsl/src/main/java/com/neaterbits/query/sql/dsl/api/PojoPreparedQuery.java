package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

final class PojoPreparedQuery extends BasePojoPreparedQuery<CompiledQuery> {

	private static final ExecutableQueryForCompiledQuery executableQueryForCompiledQuery = new ExecutableQueryForCompiledQuery();
	private static final ExecuteQueryPOJOs<CompiledQuery> executeQueryPOJOs = new ExecuteQueryPOJOs<>(executableQueryForCompiledQuery);

	PojoPreparedQuery(CompiledQuery query, ExecuteQueryPOJOsInput input, QueryMetaModel queryMetaModel) {
		super(query, executeQueryPOJOs, input, queryMetaModel);
	}
}
