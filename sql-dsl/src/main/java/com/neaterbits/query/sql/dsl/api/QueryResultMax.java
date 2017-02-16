package com.neaterbits.query.sql.dsl.api;

final class QueryResultMax extends QueryResultAggregate {
	QueryResultMax(Class<?> type, Getter getter) {
		super(type, getter);
	}

	@Override
	EAggregateFunction getAggregateFunction() {
		return EAggregateFunction.MAX;
	}
}
