package com.neaterbits.query.sql.dsl.api;

public interface QueryParamsAdditionalBuilder extends QueryParamsDSBuilder {

	<T> QueryParamsValueBuilder<T, QueryParamsAdditionalBuilder> and(Param<T> param);

}
