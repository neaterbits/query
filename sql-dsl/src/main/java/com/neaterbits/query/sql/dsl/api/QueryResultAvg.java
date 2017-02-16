package com.neaterbits.query.sql.dsl.api;

final class QueryResultAvg extends QueryResultAggregate {

	QueryResultAvg(Class<?> type, Getter getter) {
		super(type, getter);
	}

	@Override
	EAggregateFunction getAggregateFunction() {
		return EAggregateFunction.AVG;
	}
}
