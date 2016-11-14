package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public class SingleMapToResultImpl<RESULT>
	extends BaseMapToResultImpl<RESULT> implements SingleMapToResult<RESULT> {

	SingleMapToResultImpl(Class<?> resultType) {
		super(resultType);
	}

	@Override
	public <T, R> ResultMapperTo<RESULT, R, SingleMapToResult<RESULT>>
				map(Function<T, R> getter) {

		return new ResultMapperToImpl<RESULT, R, SingleMapToResult<RESULT>>(getter, this);
	}
}
