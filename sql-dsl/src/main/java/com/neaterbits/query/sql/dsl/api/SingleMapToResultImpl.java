package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public class SingleMapToResultImpl<MODEL, RESULT>
	extends BaseMapToResultImpl<MODEL, RESULT> implements SingleMapToResult<MODEL, RESULT> {

	SingleMapToResultImpl(Class<?> resultType, ModelCompiler<MODEL> modelCompiler) {
		super(resultType, modelCompiler);
	}

	@Override
	public <T, R> ResultMapperTo<MODEL, RESULT, R, SingleMapToResult<MODEL, RESULT>>
				map(Function<T, R> getter) {

		return new ResultMapperToImpl<MODEL, RESULT, R, SingleMapToResult<MODEL, RESULT>>(getter, this);
	}
}
