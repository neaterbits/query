package com.neaterbits.query.sql.dsl.api;

abstract class QueryResultAggregate extends QueryResult {

	private final Getter getter;
	
	QueryResultAggregate(Class<?> type, Getter getter) {
		super(type);
		
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		this.getter = getter;
	}
	

	final Getter getGetter() {
		return getter;
	}

	@Override
	final QueryResultMode getMode() {
		return QueryResultMode.SINGLE;
	}
}
