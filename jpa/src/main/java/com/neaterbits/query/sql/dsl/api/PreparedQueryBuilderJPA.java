package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import com.neaterbits.query.sql.dsl.api.entity.EntityModelUtil;

abstract class PreparedQueryBuilderJPA<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL extends Collection<ATTRIBUTE>> 

		extends PreparedQueryBuilderORM<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> {
	
	private final EntityManager em;
	
	PreparedQueryBuilderJPA(EntityModelUtil<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> entityModelUtil, EntityManager em, QueryDialect_SQL dialect) {
		super(entityModelUtil, dialect);

		this.em = em;
	}

	@Override
	final PreparedQueryConditionsBuilder createConditionsBuilder(QueryDialect_SQL dialect, EConditionsClause conditionsClause, boolean atRoot) {
		return new PreparedQueryConditionsBuilderORM(dialect, conditionsClause, atRoot);
	}

	private static final JPAConditionToOperator conditionToOperator = new JPAConditionToOperator();
	
	@Override
	final <QUERY> PreparedQueryComparisonRHS convertCondition(EClauseOperator operator, ConditionValue value, ConditionStringBuilder sb) {
		return conditionToOperator.convert(operator, value, sb);
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
