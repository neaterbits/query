package com.neaterbits.query.sql.dsl.api;

import javax.persistence.Query;

final class JPACompletePreparedQuery extends JPABasePreparedQuery {

	private final javax.persistence.Query jpaQuery;

	JPACompletePreparedQuery(QueryResultMode resultMode, ParamNameAssigner paramNameAssigner, Query jpaQuery) {
		super(resultMode, paramNameAssigner);
		
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
