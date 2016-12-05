package com.neaterbits.query.sql.dsl.api;

import javax.persistence.Query;

final class JPACompletePreparedQuery extends JPABasePreparedQuery {

	private final javax.persistence.Query jpaQuery;

	JPACompletePreparedQuery(boolean singleResult, Query jpaQuery) {
		super(singleResult);
		
		if (jpaQuery == null) {
			throw new IllegalArgumentException("jpaQuery == null");
		}
		
		this.jpaQuery = jpaQuery;
	}

	@Override
	public Object execute() {
		return isSingleResult()
				? jpaQuery.getSingleResult()
				: jpaQuery.getResultList();
	}
}
