package com.neaterbits.query.sql.dsl.api;

abstract class JPABasePreparedQuery implements DSPreparedQuery {

	private final boolean singleResult;

	JPABasePreparedQuery(boolean singleResult) {
		this.singleResult = singleResult;
	}
	
	final boolean isSingleResult() {
		return singleResult;
	}
}
