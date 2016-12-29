package com.neaterbits.query.sql.dsl.api;

import javax.persistence.Query;

final class JPACompletePreparedQuery<QUERY> extends JPABasePreparedQuery<QUERY> {

	private final javax.persistence.Query jpaQuery;

	JPACompletePreparedQuery(ExecutableQuery<QUERY> queryAccess, QUERY query, ParamNameAssigner paramNameAssigner, Query jpaQuery) {
		super(queryAccess, query, paramNameAssigner);
		
		if (jpaQuery == null) {
			throw new IllegalArgumentException("jpaQuery == null");
		}
		
		this.jpaQuery = jpaQuery;
	}

	@Override
	public Object execute(ParamValueResolver paramCollector) {

		return executeWithParams(jpaQuery, paramCollector);
	}
}
