package com.neaterbits.query.sql.dsl.api;

final class CollectedQueryResult_Avg extends CollectedQueryResult_Aggregate {

	CollectedQueryResult_Avg(Class<?> type, Getter getter) {
		super(type, getter);
	}

	@Override
	EAggregateFunction getAggregateFunction() {
		return EAggregateFunction.AVG;
	}
}
