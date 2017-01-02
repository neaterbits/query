package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

final class QueryDataSourcePojoWithInput extends QueryDataSourcePojoBase {

	private final ExecuteQueryPOJOsInput input;

	QueryDataSourcePojoWithInput(ExecuteQueryPOJOsInput input, QueryMetaModel queryMetaModel) {
		super(queryMetaModel);
		
		this.input = input;
	}

	@Override
	<QUERY> DSPreparedQuery<QueryDataSourcePojoBase> prepareSingleQuery(ExecutableQuery<QUERY> q, QUERY query) {
		final ExecuteQueryPOJOs<QUERY> executeQueryPOJOs = new ExecuteQueryPOJOs<>(q);

		return new PojoPreparedQuery<>(this, query, executeQueryPOJOs, input, getQueryMetaModel());
	}

	@Override
	<QUERY> DSPreparedQuery<QueryDataSourcePojoBase> prepareMultiQuery(ExecutableQuery<QUERY> q, QUERY query) {
		final ExecuteQueryPOJOs<QUERY> executeQueryPOJOs = new ExecuteQueryPOJOs<>(q);

		return new PojoPreparedQuery<>(this, query, executeQueryPOJOs, input, getQueryMetaModel());
	}
}
