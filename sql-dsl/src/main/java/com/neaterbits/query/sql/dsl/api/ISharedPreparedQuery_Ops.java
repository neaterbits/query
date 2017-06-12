package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface ISharedPreparedQuery_Ops<RESULT_TYPE> {
	
	RESULT_TYPE execute();
	
	<T> QueryParamsValueBuilder<T, QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE>> executeWith(ValParam<T> param);

	<T> QueryParamsValueBuilderForCollection<T, QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE>> executeWith(InParam<T> param);

	RESULT_TYPE execute(Function<QueryParamsInitialBuilder, QueryParamsEnd> builder);

}
