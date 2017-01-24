package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

final class QueryDataSource_Pojo_WithInput extends QueryDataSource_Pojo_Base {

	private final ExecuteQueryPOJOsInput input;

	QueryDataSource_Pojo_WithInput(ExecuteQueryPOJOsInput input, QueryMetaModel queryMetaModel) {
		super(queryMetaModel);
		
		this.input = input;
	}

	@Override
	<QUERY> PreparedQuery_DS<QueryDataSource_Pojo_Base> prepareSingleQuery(ExecutableQuery<QUERY> q, QUERY query) {
		final ExecuteQueryPOJOs<QUERY> executeQueryPOJOs = new ExecuteQueryPOJOs<>(q);

		return new PojoPreparedQuery<>(this, q.makeMetaData(query), query, executeQueryPOJOs, input, getQueryMetaModel());
	}

	@Override
	<QUERY> PreparedQuery_DS<QueryDataSource_Pojo_Base> prepareMultiQuery(ExecutableQuery<QUERY> q, QUERY query) {
		final ExecuteQueryPOJOs<QUERY> executeQueryPOJOs = new ExecuteQueryPOJOs<>(q);

		return new PojoPreparedQuery<>(this, q.makeMetaData(query), query, executeQueryPOJOs, input, getQueryMetaModel());
	}
}
