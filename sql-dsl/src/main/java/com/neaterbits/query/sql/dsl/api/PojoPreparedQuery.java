package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

final class PojoPreparedQuery<QUERY> extends BasePojoPreparedQuery<QUERY> {

	PojoPreparedQuery(QueryDataSource_Pojo_Base dataSource, PreparedQueryMetaData metaData, QUERY query, ExecuteQueryPOJOs<QUERY> executor, ExecuteQueryPOJOsInput input, QueryMetaModel queryMetaModel) {
		super(dataSource, metaData, query, executor, input, queryMetaModel);
	}
}
