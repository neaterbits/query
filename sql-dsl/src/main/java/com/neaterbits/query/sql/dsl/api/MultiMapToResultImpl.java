package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

final class MultiMapToResultImpl<MODEL, RESULT>
		extends BaseMapToResultImpl<MODEL, RESULT>
		implements MultiMapToResult<MODEL, RESULT> {

	MultiMapToResultImpl(Class<?> resultType, ModelCompiler<MODEL> modelCompiler) {
		super(resultType, modelCompiler);
	}

	@Override
	public <T, R> ResultMapperTo<MODEL, RESULT, R, MultiMapToResultTable<MODEL, RESULT>>
				mapF(Function<T, R> getter) {

		return new ResultMapperToImpl<MODEL, RESULT, R, MultiMapToResultTable<MODEL, RESULT>>(getter, this);
	}

	@Override
	public <T, R> ResultMapperTo<MODEL, RESULT, R, MultiMapToResultAlias<MODEL, RESULT>> map(
			Supplier<R> getter) {

		return new ResultMapperToImpl<MODEL, RESULT, R, MultiMapToResultAlias<MODEL, RESULT>>(getter, this);
	}
}
