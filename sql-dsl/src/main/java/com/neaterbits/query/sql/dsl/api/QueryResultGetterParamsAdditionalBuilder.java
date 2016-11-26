package com.neaterbits.query.sql.dsl.api;

public interface QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE> extends QueryResultGetter<RESULT_TYPE>{

	<T> QueryParamsValueBuilder<T, QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE>> and(Param<T> param);
	
}
