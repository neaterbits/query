package com.neaterbits.query.sql.dsl.api;

public interface QueryParamsValueBuilderForCollection<T, RET> extends QueryParamsValueBuilderBase<T, RET> {

	RET setTo(@SuppressWarnings("unchecked") T ... values);

	RET setTo(Iterable<T> values);
	
}
