package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;


public interface QueryOps<RESULT, RESULT_TYPE> extends Query<RESULT> {

	QueryResultGetterParamsInitialBuilder<RESULT_TYPE> executeOn(QueryDataSource dataSource);

	RESULT_TYPE execute(Function<QueryParamsInitialBuilder, QueryParamsEnd> builder);
}

