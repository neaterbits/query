package com.neaterbits.query.sql.dsl.api;

final class CollectedQueryResult_Count extends CollectedQueryResult_Aggregate {

	CollectedQueryResult_Count(Class<?> type, Getter getter) {
		super(type, getter);
	}

	@Override
	EAggregateFunction getAggregateFunction() {
		return EAggregateFunction.COUNT;
	}
}
