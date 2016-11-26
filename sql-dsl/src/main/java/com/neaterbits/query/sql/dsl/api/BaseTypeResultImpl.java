package com.neaterbits.query.sql.dsl.api;

abstract class BaseTypeResultImpl<MODEL, RESULT> extends SelectSourceBuilderImpl<MODEL, RESULT> {

	BaseTypeResultImpl(Class<?> resultType) {
		super(resultType);
	}
}
