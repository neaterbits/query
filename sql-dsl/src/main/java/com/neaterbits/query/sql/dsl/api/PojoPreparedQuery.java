package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

final class PojoPreparedQuery<QUERY> extends BasePojoPreparedQuery<QUERY> {


	PojoPreparedQuery(QUERY query, ExecuteQueryPOJOs<QUERY> executor, ExecuteQueryPOJOsInput input, QueryMetaModel queryMetaModel) {
		super(query, executor, input, queryMetaModel);
	}
}
