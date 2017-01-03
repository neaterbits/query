package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

abstract class QueryDataSource_Pojo_Base extends QueryDataSource_Base<QueryDataSource_Pojo_Base> {

	private final QueryMetaModel queryMetaModel;

	protected QueryDataSource_Pojo_Base(QueryMetaModel queryMetaModel) {
		this.queryMetaModel = queryMetaModel;
	}

	protected final QueryMetaModel getQueryMetaModel() {
		return queryMetaModel;
	}
}
