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

public final class QueryDataSourceJPA extends QueryDataSourceORM<
									javax.persistence.Query,
									ManagedType<?>,
									EmbeddableType<?>,
									IdentifiableType<?>,
									Attribute<?, ?>,
									Set<Attribute<?, ?>>> {
	
	private final EntityManager em;

	public QueryDataSourceJPA(EntityManager entityManager) {
		super(new JPAEntityModelUtil(new JPAEntityModel(entityManager.getMetamodel())));

		this.em = entityManager;
	}
	
	
	@Override
	PreparedQueryBuilder createBuilder() {
		return new JPAPreparedQueryBuilder();
	}

	@Override
	PreparedQueryConditionsBuilder createConditionsBuilder(boolean atRoot) {
		return new PreparedQueryConditionsBuilderJPA(atRoot);
	}


	@Override
	<QUERY> DSPreparedQueryDB<QUERY, Query> makeHalfwayPreparedQuery(ExecutableQuery<QUERY> queryAccess, QUERY query,
			ParamNameAssigner paramNameAssigner, String base, PreparedQueryConditionsBuilder conditions) {
		
		return new JPAHalfwayPreparedQuery<QUERY>(queryAccess, query, paramNameAssigner, base, (PreparedQueryConditionsBuilderJPA)conditions, em);
	}

	@Override
	final <QUERY> DSPreparedQueryDB<QUERY, javax.persistence.Query> makeCompletePreparedQuery(ExecutableQuery<QUERY> q, QUERY query, ParamNameAssigner paramNameAssigner, PreparedQueryBuilder sb) {
		final String jpql = sb.toString();
		
		System.out.println("## jpql:\n" + jpql);
		
		final javax.persistence.Query jpaQuery = em.createQuery(jpql);

		return new JPACompletePreparedQuery<QUERY>(q, query, paramNameAssigner, jpaQuery);
	}


	private static final JPAConditionToOperator conditionToOperator = new JPAConditionToOperator();

	
	@Override
	<QUERY> PreparedQueryComparisonRHS convertConditions(ExecutableQuery<QUERY> q, QUERY query, int conditionIdx, ConditionValueImpl value, StringBuilder sb) {

		return conditionToOperator.convert(q, query, conditionIdx, value, sb);
	}


	@Override
	String getColumnNameForGetter(TypeMapSource source, CompiledGetter getter) {

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

