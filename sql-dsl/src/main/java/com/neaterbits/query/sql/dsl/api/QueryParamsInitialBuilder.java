package com.neaterbits.query.sql.dsl.api;

public interface QueryParamsInitialBuilder extends QueryParamsDSBuilder {

	<T> QueryParamsValueBuilder<T, QueryParamsAdditionalBuilder> with(Param<T> param);
	
}
