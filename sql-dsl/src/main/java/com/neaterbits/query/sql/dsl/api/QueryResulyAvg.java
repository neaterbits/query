package com.neaterbits.query.sql.dsl.api;

final class QueryResulyAvg extends QueryResultAggregate {
	
	QueryResulyAvg(Class<?> type, Getter getter) {
		super(type, getter);
	}

	@Override
	EAggregateFunction getAggregateFunction() {
		return EAggregateFunction.AVG;
	}
}
