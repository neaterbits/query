package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

final class MultiMapToResultImpl<MODEL, RESULT>
		extends BaseMapToResultImpl<MODEL, RESULT>
		implements IClassicMultiMapToResult<MODEL, RESULT>,
		           IClassicMultiMapToResultAlias<MODEL, RESULT> {

	MultiMapToResultImpl(Class<?> resultType, ModelCompiler<MODEL> modelCompiler) {
		super(new QueryResultMappedMulti(resultType), modelCompiler);
	}

	@Override
	public <T, R> ISharedResultMapperTo<MODEL, RESULT, R, IClassicMultiMapToResultTable<MODEL, RESULT>>
				map(Function<T, R> getter) {

		return new ResultMapperToImpl<MODEL, RESULT, R, IClassicMultiMapToResultTable<MODEL, RESULT>>(getter, this);
	}

	@Override
	public <T, R> ISharedResultMapperTo<MODEL, RESULT, R, IClassicMultiMapToResultAlias<MODEL, RESULT>> map(
			Supplier<R> getter) {

		return new ResultMapperToImpl<MODEL, RESULT, R, IClassicMultiMapToResultAlias<MODEL, RESULT>>(getter, this);
	}
}
