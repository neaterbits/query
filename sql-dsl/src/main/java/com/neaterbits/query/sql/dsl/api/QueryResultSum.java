package com.neaterbits.query.sql.dsl.api;

final class QueryResultSum extends QueryResultAggregate {

	QueryResultSum(Class<?> type, Getter getter) {
		super(type, getter);
	}

	@Override
	AggregateFunction getAggregateFunction() {
		return AggregateFunction.SUM;
	}
}
