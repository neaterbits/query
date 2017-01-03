package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

final class MultiMapToResultImpl<MODEL, RESULT>
		extends BaseMapToResultImpl<MODEL, RESULT>
		implements IClassicMultiMapToResult<MODEL, RESULT>,
		           IClassicMultiMapToResultAlias<MODEL, RESULT> {

	MultiMapToResultImpl(Class<?> resultType, ECollectionType collectionType, ModelCompiler<MODEL> modelCompiler) {
		super(new QueryResultMappedMulti(resultType, collectionType), modelCompiler);
	}

	@Override
	public <T, R> ISharedResultMapperTo<MODEL, RESULT, R, IClassicMultiMapToResultNamed<MODEL, RESULT>>
				map(Function<T, R> getter) {

		return new ResultMapperToImpl<MODEL, RESULT, R, IClassicMultiMapToResultNamed<MODEL, RESULT>>(getter, this);
	}

	@Override
	public <T, R> ISharedResultMapperTo<MODEL, RESULT, R, IClassicMultiMapToResultAlias<MODEL, RESULT>> map(
			Supplier<R> getter) {

		return new ResultMapperToImpl<MODEL, RESULT, R, IClassicMultiMapToResultAlias<MODEL, RESULT>>(getter, this);
	}
}
