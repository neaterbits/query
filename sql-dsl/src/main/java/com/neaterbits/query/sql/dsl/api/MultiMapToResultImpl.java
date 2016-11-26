package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class MultiMapToResultImpl<MODEL, RESULT>
		extends BaseMapToResultImpl<MODEL, RESULT>
		implements MultiMapToResult<MODEL, RESULT> {

	MultiMapToResultImpl(Class<?> resultType) {
		super(resultType);
	}

	@Override
	public <T, R> ResultMapperTo<MODEL, RESULT, R, MultiMapToResult<MODEL, RESULT>>
				map(Function<T, R> getter) {

		return new ResultMapperToImpl<MODEL, RESULT, R, MultiMapToResult<MODEL, RESULT>>(getter, this);
	}
}
