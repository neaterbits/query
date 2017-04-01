package com.neaterbits.query.sql.dsl.api;

final class CollectedQueryResult_Sum extends CollectedQueryResult_Aggregate {

	CollectedQueryResult_Sum(Class<?> type, Getter getter) {
		super(type, getter);
	}

	@Override
	EAggregateFunction getAggregateFunction() {
		return EAggregateFunction.SUM;
	}
}
