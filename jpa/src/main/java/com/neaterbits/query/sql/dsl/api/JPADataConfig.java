package com.neaterbits.query.sql.dsl.api;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EmbeddableType;
import javax.persistence.metamodel.IdentifiableType;
import javax.persistence.metamodel.ManagedType;

import com.neaterbits.query.sql.dsl.api.entity.EntityModel;

public abstract class JPADataConfig extends DataConfigBase<
		EntityManager,
		ManagedType<?>,
		EmbeddableType<?>,
		IdentifiableType<?>,
		Attribute<?, ?>,
		Set<Attribute<?, ?>>
	> {

	private final String persistenceUnitName;

	public JPADataConfig(String persistenceUnitName) {
		
		if (persistenceUnitName == null) {
			throw new IllegalArgumentException("persistenceUnitName == null");
		}
		
		if (persistenceUnitName.isEmpty()) {
			throw new IllegalArgumentException("persistenceUnitName is empty");
		}
		
		
		this.persistenceUnitName = persistenceUnitName;
	}

	final String getPersistenceUnitName() {
		return persistenceUnitName;
	}
	
	@Override
	protected final EntityModel<ManagedType<?>, EmbeddableType<?>, IdentifiableType<?>, Attribute<?, ?>, Set<Attribute<?, ?>>> getEntityModel() {
		
		final JPAEntityModel entityModel;

		final EntityManagerFactory emf = Persistence.createEntityManagerFactory(getPersistenceUnitName());
		
		try {
			entityModel = new JPAEntityModel(emf.getMetamodel());
		}
		finally {
			emf.close();
		}

		return entityModel;
	}
	
}
