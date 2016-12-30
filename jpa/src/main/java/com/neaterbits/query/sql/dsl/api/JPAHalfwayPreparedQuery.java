package com.neaterbits.query.sql.dsl.api;

import javax.persistence.EntityManager;
import javax.persistence.Query;

final class JPAHalfwayPreparedQuery<QUERY> extends JPABasePreparedQuery<QUERY> {

	private final String base;
	private final PreparedQueryConditionsBuilderJPA conditions;
	private final EntityManager entityManager;
	
	JPAHalfwayPreparedQuery(ExecutableQuery<QUERY> queryAccess, QUERY query, ParamNameAssigner paramNameAssigner, String base, PreparedQueryConditionsBuilderJPA conditions, EntityManager entityManager) {
		super(queryAccess, query, paramNameAssigner);
		
		if (base == null) {
			throw new IllegalArgumentException("base == null");
		}
		
		if (base.trim().isEmpty()) {
			throw new IllegalArgumentException("no base");
		}
		
		if (conditions == null) {
			throw new IllegalArgumentException("conditions == null");
		}
		
		if (conditions.isEmpty()) {
			throw new IllegalArgumentException("no conditions");
		}
		
		if (conditions.size() > 1 && conditions.getType() == null) {
			throw new IllegalArgumentException("no op when conditions > 1");
		}
		
		if (entityManager == null) {
			throw new IllegalArgumentException("entityManager == null");
		}
		
		this.base = base;
		this.conditions = conditions;
		this.entityManager = entityManager;
	}

	@Override
	public Object execute(ParamValueResolver collectedParams) {
		
		final StringBuilder sb = new StringBuilder(base);

		sb.append(" ");

		conditions.resolveFromParams(sb, collectedParams);

		final Query jpaQuery = entityManager.createQuery(sb.toString()); 

		return executeWithParams(jpaQuery, collectedParams); 
	}
}
