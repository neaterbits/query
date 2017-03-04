package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

public abstract class PojoDataConfig extends DataConfigBase<Void, Void, Void, Void, Void, Collection<Void>> {

	private final QueryMetaModel queryMetaModel;

	protected PojoDataConfig(QueryMetaModel queryMetaModel) {
		
		if (queryMetaModel == null) {
			throw new IllegalArgumentException("queryMetaModel == null");
		}
		
		this.queryMetaModel = queryMetaModel;
	}

	final QueryMetaModel getQueryMetaModel() {
		return queryMetaModel;
	}
}
