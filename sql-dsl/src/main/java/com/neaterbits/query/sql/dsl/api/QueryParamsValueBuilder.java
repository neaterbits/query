package com.neaterbits.query.sql.dsl.api;

public interface QueryParamsValueBuilder<T, RET> extends QueryParamsValueBuilderBase<T, RET> {

	RET setTo(T value);

}

