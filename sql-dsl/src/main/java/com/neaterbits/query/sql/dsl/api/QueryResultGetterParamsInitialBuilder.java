package com.neaterbits.query.sql.dsl.api;

public interface QueryResultGetterParamsInitialBuilder<RESULT_TYPE> extends QueryResultGetter<RESULT_TYPE> {

	<T> QueryParamsValueBuilder<T, QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE>> with(Param<T> param);
	
}
