package com.neaterbits.query.sql.dsl.api;

final class JPAHalfwayPreparedQuery extends JPABasePreparedQuery {

	JPAHalfwayPreparedQuery(boolean singleResult) {
		super(singleResult);
	}

	@Override
	public Object execute() {
		throw new UnsupportedOperationException("TODO");
	}
}
