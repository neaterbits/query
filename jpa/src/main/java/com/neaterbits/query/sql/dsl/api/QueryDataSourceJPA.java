package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EmbeddableType;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.IdentifiableType;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;

/**
 * Query data source implementation for JPA
 * @author nhl
 *
 */

public abstract class QueryDataSourceJPA extends QueryDataSourceORM<
									javax.persistence.Query,
									ManagedType<?>,
									EmbeddableType<?>,
									IdentifiableType<?>,
									Attribute<?, ?>,
									Set<Attribute<?, ?>>> {
	
	final EntityManager em;

	public QueryDataSourceJPA(EntityManager entityManager) {
		super(new JPAEntityModelUtil(new JPAEntityModel(entityManager.getMetamodel())));

		this.em = entityManager;
	}
	
	

	@Override
	final PreparedQueryConditionsBuilder createConditionsBuilder(PreparedQueryBuilderORM queryBuilderORM, boolean atRoot) {
		return new PreparedQueryConditionsBuilderJPA(queryBuilderORM, atRoot);
	}


	@Override
	final <QUERY> DSPreparedQueryDB<QUERY, Query> makeHalfwayPreparedQuery(ExecutableQuery<QUERY> queryAccess, QUERY query,
			ParamNameAssigner paramNameAssigner, String base, PreparedQueryConditionsBuilder conditions) {
		
		return new JPAHalfwayPreparedQuery<QUERY>(this, queryAccess, query, paramNameAssigner, base, (PreparedQueryConditionsBuilderJPA)conditions, em);
	}

	private static final JPAConditionToOperator conditionToOperator = new JPAConditionToOperator();

	
	@Override
	final <QUERY> PreparedQueryComparisonRHS convertConditions(ExecutableQuery<QUERY> q, QUERY query, int conditionIdx, ConditionValueImpl value, StringBuilder sb) {

		return conditionToOperator.convert(q, query, conditionIdx, value, sb);
	}


	@Override
	final String getColumnNameForGetter(TypeMapSource source, CompiledGetter getter) {

		// Look up in entity manager
		
		final Metamodel metaModel = em.getEntityManagerFactory().getMetamodel();
				final EntityType<?> entityType = metaModel.entity(source.getType());

		if (entityType == null) {
			throw new IllegalStateException("No entity type for " + source.getType());
		}

		final Attribute<?, ?> attr = findAttr(entityType, getter.getGetterMethod());
		
		if (attr == null) {
			throw new IllegalArgumentException("No attribute for getter " + getter);
		}

		return attr.getName();
	}
	
	private static Attribute<?, ?> findAttr(EntityType<?> entityType, Method getterMethod) {
		Attribute<?, ?> found = null;
		
		// Find attribute
		for (Attribute<?, ?> attr : entityType.getAttributes()) {
			final Member m  = attr.getJavaMember();

			if (m instanceof Method) {
				final Method method = (Method)m;
				
				if (method.equals(getterMethod)) {
					found = attr;
				}
			}
			else {
				throw new UnsupportedOperationException("Does not support field members for now");
			}
		}

		return found;
	}
}

