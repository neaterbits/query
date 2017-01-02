package com.neaterbits.query.sql.dsl.api.helper.jpa;

import javax.persistence.EntityManager;

import com.neaterbits.query.sql.dsl.api.QueryDataSource;
import com.neaterbits.query.sql.dsl.api.QueryDataSourceJPANative;

public class QueryTestDSJPANative extends QueryTestDSJPAManaged {

	public QueryTestDSJPANative(String persistenceUnitName) {
		super(persistenceUnitName);
	}

	@Override
	protected QueryDataSource createDataSource(EntityManager entities) {
		return new QueryDataSourceJPANative(entities);
	}
}
