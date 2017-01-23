package com.neaterbits.query.sql.dsl.api;

import javax.persistence.Query;

final class PreparedQuery_JPA_Complete<QUERY> extends PreparedQuery_JPA_Base<QUERY> {

	private final javax.persistence.Query jpaQuery;

	PreparedQuery_JPA_Complete(QueryDataSourceJPA dataSource, ExecutableQuery<QUERY> queryAccess, QUERY query, ParamNameAssigner paramNameAssigner, Query jpaQuery) {
		super(dataSource, queryAccess, query, paramNameAssigner);
		
		if (jpaQuery == null) {
			throw new IllegalArgumentException("jpaQuery == null");
		}
		
		this.jpaQuery = jpaQuery;
	}

	@Override
	Object execute(ParamValueResolver paramCollector) {

		return executeWithParams(jpaQuery, paramCollector);
	}
}
