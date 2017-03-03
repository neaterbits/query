package com.neaterbits.query.sql.dsl.api;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

abstract class QueryTestDSJPABase extends QueryTestDSBasePersistent<EntityManagerFactory, EntityManager, EntityTransaction> {

	public QueryTestDSJPABase(JPADataStore dataStore) {
		super(dataStore);
	}
}
