package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

final class PojoPreparedQuery<QUERY> extends BasePojoPreparedQuery<QUERY> {

	PojoPreparedQuery(QueryDataSourcePojoBase dataSource, QUERY query, ExecuteQueryPOJOs<QUERY> executor, ExecuteQueryPOJOsInput input, QueryMetaModel queryMetaModel) {
		super(dataSource, query, executor, input, queryMetaModel);
	}
}
