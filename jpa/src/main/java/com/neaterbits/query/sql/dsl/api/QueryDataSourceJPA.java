package com.neaterbits.query.sql.dsl.api;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EmbeddableType;
import javax.persistence.metamodel.IdentifiableType;
import javax.persistence.metamodel.ManagedType;

/**
 * Query data source implementation for JPA
 * @author nhl
 *
 */

public abstract class QueryDataSourceJPA extends QueryDataSource_ORM<
									javax.persistence.Query,
									ManagedType<?>,
									EmbeddableType<?>,
									IdentifiableType<?>,
									Attribute<?, ?>,
									Set<Attribute<?, ?>>> {
	
	final EntityManager em;
	
	abstract Query createJPAQuery(String queryString);
	

	public QueryDataSourceJPA(EntityManager entityManager) {
		super(new JPAEntityModelUtil(new JPAEntityModel(entityManager.getMetamodel())));

		this.em = entityManager;
	}
}

