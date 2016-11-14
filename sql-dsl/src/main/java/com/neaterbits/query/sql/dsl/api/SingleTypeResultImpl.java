package com.neaterbits.query.sql.dsl.api;

final class SingleTypeResultImpl<RESULT> extends BaseTypeResultImpl<RESULT> 
	implements SingleTypeResult<RESULT> {

	SingleTypeResultImpl(Class<?> resultType) {
		super(resultType);
	}
}
