package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface PreparedQueryOps<RESULT_TYPE> {
	
	RESULT_TYPE execute();
	
	<T> QueryParamsValueBuilder<T, QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE>> executeWith(Param<T> param);


	RESULT_TYPE execute(Function<QueryParamsInitialBuilder, QueryParamsEnd> builder);

}
