package com.neaterbits.query.sql.dsl.api;

import javax.persistence.Query;

final class PreparedQuery_JPA_Halfway<QUERY> extends PreparedQuery_JPA_Base<QUERY> {

	private final String base;
	private final PreparedQueryConditionsBuilderJPA conditions;
	
	PreparedQuery_JPA_Halfway(QueryDataSourceJPA dataSource, ExecutableQuery<QUERY> queryAccess, QUERY query, ParamNameAssigner paramNameAssigner, String base, PreparedQueryConditionsBuilderJPA conditions) {
		super(dataSource, queryAccess, query, paramNameAssigner);
		
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
		
		
		this.base = base;
		this.conditions = conditions;
	}

	@Override
	Object execute(ParamValueResolver collectedParams) {
		
		final StringBuilder sb = new StringBuilder(base);

		sb.append(" ");

		conditions.resolveFromParams(sb, collectedParams);

		final String queryString = sb.toString();

		final Query jpaQuery = ((QueryDataSourceJPA)getDataSource()).createJPAQuery(queryString); 

		return executeWithParams(jpaQuery, collectedParams); 
	}
}
