package com.neaterbits.query.sql.dsl.api;

abstract class BaseTypeResultImpl<RESULT> extends SelectSourceBuilderImpl<RESULT> {

	BaseTypeResultImpl(Class<?> resultType) {
		super(resultType);
	}
}
