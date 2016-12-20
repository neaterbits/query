package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

public abstract class QueryDataSourcePojoBase extends QueryDataSourceBase {

	private final QueryMetaModel queryMetaModel;

	protected QueryDataSourcePojoBase(QueryMetaModel queryMetaModel) {
		this.queryMetaModel = queryMetaModel;
	}

	protected final QueryMetaModel getQueryMetaModel() {
		return queryMetaModel;
	}
}
