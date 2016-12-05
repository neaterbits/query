package com.neaterbits.query.sql.dsl.api;

import javax.persistence.Query;

final class JPACompletePreparedQuery extends JPABasePreparedQuery {

	private final javax.persistence.Query jpaQuery;

	JPACompletePreparedQuery(CompiledQuery compiledQuery, ParamNameAssigner paramNameAssigner, Query jpaQuery) {
		super(compiledQuery, paramNameAssigner);
		
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
