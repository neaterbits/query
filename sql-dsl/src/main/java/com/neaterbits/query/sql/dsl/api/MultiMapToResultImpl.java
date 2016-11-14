package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class MultiMapToResultImpl<RESULT>
		extends BaseMapToResultImpl<RESULT>
		implements MultiMapToResult<RESULT> {

	MultiMapToResultImpl(Class<?> resultType) {
		super(resultType);
	}

	@Override
	public <T, R> ResultMapperTo<RESULT, R, MultiMapToResult<RESULT>>
				map(Function<T, R> getter) {

		return new ResultMapperToImpl<RESULT, R, MultiMapToResult<RESULT>>(getter, this);
	}
}
