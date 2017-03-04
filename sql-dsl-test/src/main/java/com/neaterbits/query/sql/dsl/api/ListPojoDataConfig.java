package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

import com.neaterbits.query.sql.dsl.api.entity.EntityModel;
import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

public class ListPojoDataConfig extends PojoDataConfig {

	private final Collection<?> instances;

	public ListPojoDataConfig(Collection<?> instances, QueryMetaModel queryMetaModel) {
		super(queryMetaModel);

		if (instances == null) {
			throw new IllegalArgumentException("instances == null");
		}
		this.instances = instances;
	}

	@Override
	protected EntityModel<Void, Void, Void, Void, Collection<Void>> getEntityModel() {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	protected DataStore<Void> getDataStore() {
		throw new UnsupportedOperationException("Read-only");
	}

	@Override
	protected QueryDataSource getDataSource() {
		return new QueryDataSourcePojoWithList(instances, getQueryMetaModel());
	}
}
