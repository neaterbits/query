package com.neaterbits.query.sql.dsl.api;

import java.util.List;

abstract class QueryDataSource_DB extends QueryDataSource_Base<QueryDataSource_DB> {

	abstract <QUERY> List<Object> mapMultipleEntitities(ExecutableQuery<QUERY> q, QUERY query, List<?> input);

	abstract <QUERY> Object mapSingleEntity(ExecutableQuery<QUERY> q, QUERY query, Object input);
	
	Object convertAvgAggregateResult(Class<?> aggregateResultType, Object input) {
		if (!aggregateResultType.equals(input.getClass())) {
			throw new IllegalStateException("Not of aggregated type " + aggregateResultType.getName() + ": " + input.getClass().getName());
		}

		return input;
	}

	Object convertCountAggregateResult(Class<?> aggregateResultType, Object input) {
		if (!aggregateResultType.equals(input.getClass())) {
			throw new IllegalStateException("Not of aggregated type " + aggregateResultType.getName() + ": " + input.getClass().getName());
		}

		return input;
	}
	
}
