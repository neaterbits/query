package com.neaterbits.query.sql.dsl.api;

import javax.persistence.Query;

import com.neaterbits.query.sql.dsl.api.DSPreparedQuery;

final class JPAPreparedQuery implements DSPreparedQuery {

	private final javax.persistence.Query jpaQuery;

	JPAPreparedQuery(Query jpaQuery) {
		
		if (jpaQuery == null) {
			throw new IllegalArgumentException("jpaQuery == null");
		}
		
		this.jpaQuery = jpaQuery;
	}
}
