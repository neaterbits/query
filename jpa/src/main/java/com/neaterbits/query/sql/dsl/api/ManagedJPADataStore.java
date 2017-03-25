package com.neaterbits.query.sql.dsl.api;

import java.util.List;

import javax.persistence.EntityManager;

abstract class ManagedJPADataStore extends JPADataStore {

	ManagedJPADataStore(String persistenceUnitName) {
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

	protected final List<?> executeSql(EntityManager em, String sql) {
		return em.createNativeQuery(sql).getResultList();
	}
}
