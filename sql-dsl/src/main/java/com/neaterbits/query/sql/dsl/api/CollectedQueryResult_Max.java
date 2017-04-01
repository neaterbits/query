package com.neaterbits.query.sql.dsl.api;

final class CollectedQueryResult_Max extends CollectedQueryResult_Aggregate {
	CollectedQueryResult_Max(Class<?> type, Getter getter) {
		super(type, getter);
	}

	@Override
	EAggregateFunction getAggregateFunction() {
		return EAggregateFunction.MAX;
	}
}
