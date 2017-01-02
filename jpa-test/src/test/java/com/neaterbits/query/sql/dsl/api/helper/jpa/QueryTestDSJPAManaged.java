package com.neaterbits.query.sql.dsl.api.helper.jpa;

import javax.persistence.EntityManager;

public abstract class QueryTestDSJPAManaged extends QueryTestDSJPABase {
	
	protected QueryTestDSJPAManaged(String persistenceUnitName) {
		super(persistenceUnitName);
	}

	@Override
	protected final void persist(EntityManager em, Object instance) {
		em.persist(instance);
	}

	@Override
	protected final void remove(EntityManager em, Object instance, Object pk) {

		final Object found = em.find(instance.getClass(), pk);

		if (found == null) {
			throw new IllegalStateException("found == null");
		}

		em.remove(found);
	}
	
}
