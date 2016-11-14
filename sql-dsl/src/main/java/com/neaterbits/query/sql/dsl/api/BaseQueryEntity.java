package com.neaterbits.query.sql.dsl.api;

abstract class BaseQueryEntity {

	private final QueryCollectorImpl queryCollector;

	BaseQueryEntity(BaseQueryEntity last) {
		this(last.queryCollector);
	}

	BaseQueryEntity(QueryCollectorImpl queryCollector) {
		
		if (queryCollector == null) {
			throw new IllegalArgumentException("queryCollector == null");
		}
		
		this.queryCollector = queryCollector;
	}

	QueryCollectorImpl getQueryCollector() {
		return queryCollector;
	}
}
