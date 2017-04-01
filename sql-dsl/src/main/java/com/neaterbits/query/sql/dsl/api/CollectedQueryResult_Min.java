package com.neaterbits.query.sql.dsl.api;

final class CollectedQueryResult_Min extends CollectedQueryResult_Aggregate {

	CollectedQueryResult_Min(Class<?> type, Getter getter) {
		super(type, getter);
	}

	@Override
	EAggregateFunction getAggregateFunction() {
		return EAggregateFunction.MIN;
	}
}
