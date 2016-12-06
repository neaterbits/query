
package com.neaterbits.query.sql.dsl.api;

final class QueryResultMappedMulti extends QueryResultMapped {

	QueryResultMappedMulti(Class<?> type) {
		super(type);
	}

	@Override
	QueryResultMode getMode() {
		return QueryResultMode.MULTI;
	}
}