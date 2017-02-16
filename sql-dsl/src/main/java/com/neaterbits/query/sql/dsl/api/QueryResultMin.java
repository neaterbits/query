package com.neaterbits.query.sql.dsl.api;

final class QueryResultMin extends QueryResultAggregate {

	QueryResultMin(Class<?> type, Getter getter) {
		super(type, getter);
	}

	@Override
	EAggregateFunction getAggregateFunction() {
		return EAggregateFunction.MIN;
	}
}
