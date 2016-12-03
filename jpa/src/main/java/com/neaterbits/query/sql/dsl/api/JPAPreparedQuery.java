package com.neaterbits.query.sql.dsl.api;

import javax.persistence.Query;

final class JPAPreparedQuery implements DSPreparedQuery {

	private final javax.persistence.Query jpaQuery;

	JPAPreparedQuery(Query jpaQuery) {
		
		if (jpaQuery == null) {
			throw new IllegalArgumentException("jpaQuery == null");
		}
		
		this.jpaQuery = jpaQuery;
	}
}
