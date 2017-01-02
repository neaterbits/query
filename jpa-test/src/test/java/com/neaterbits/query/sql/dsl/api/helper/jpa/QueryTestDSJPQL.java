package com.neaterbits.query.sql.dsl.api.helper.jpa;

import javax.persistence.EntityManager;

import com.neaterbits.query.sql.dsl.api.QueryDataSource;
import com.neaterbits.query.sql.dsl.api.QueryDataSourceJPQL;

public final class QueryTestDSJPQL extends QueryTestDSJPAManaged {

	public QueryTestDSJPQL(String persistenceUnitName) {
		super(persistenceUnitName);
	}

	@Override
	protected QueryDataSource createDataSource(EntityManager em) {
		return new QueryDataSourceJPQL(em);
	}
}

