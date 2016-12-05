package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

final class JPAHalfwayPreparedQuery extends JPABasePreparedQuery {

	private final String base;
	private final JPAOp op;
	private final List<JPACondition> conditions;
	private final EntityManager entityManager;
	
	
	JPAHalfwayPreparedQuery(QueryResultMode resultMode, ParamNameAssigner paramNameAssigner, String base, JPAOp op, List<JPACondition> conditions, EntityManager entityManager) {
		super(resultMode, paramNameAssigner);
		
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
		
		if (conditions.size() > 1 && op == null) {
			throw new IllegalArgumentException("no op when conditions > 1");
		}
		
		if (entityManager == null) {
			throw new IllegalArgumentException("entityManager == null");
		}
		
		this.base = base;
		this.op = op;
		this.conditions = new ArrayList<>(conditions);
		this.entityManager = entityManager;
	}

	@Override
	public Object execute(ParamValueResolver collectedParams) {
		
		final StringBuilder sb = new StringBuilder(base);
		
		sb.append(" ");
		
		CompileConditionParam.addAllConditions(sb, conditions, collectedParams);
		
		final Query jpaQuery = entityManager.createQuery(sb.toString()); 

		return executeWithParams(jpaQuery, collectedParams); 
	}
}
