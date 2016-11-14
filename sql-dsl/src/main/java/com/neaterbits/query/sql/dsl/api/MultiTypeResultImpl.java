package com.neaterbits.query.sql.dsl.api;

final class MultiTypeResultImpl<RESULT> extends BaseTypeResultImpl<RESULT>
	implements MultiTypeResult<RESULT> {

	MultiTypeResultImpl(Class<?> resultType) {
		super(resultType);
	}
}
