package com.neaterbits.query.sql.dsl.api;

final class QueryResultCount extends QueryResultAggregate {

	QueryResultCount(Class<?> type, Getter getter) {
		super(type, getter);
	}

	@Override
	EAggregateFunction getAggregateFunction() {
		return EAggregateFunction.COUNT;
	}
}
