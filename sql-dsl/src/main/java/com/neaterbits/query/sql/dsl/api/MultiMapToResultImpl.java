package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

final class MultiMapToResultImpl<MODEL, RESULT>
		extends Collector_MapToResult_Base<MODEL, RESULT>
		implements IClassicResult_Mapped_Multi_All<MODEL, RESULT>,
		           IClassicResult_Mapped_Multi_Alias<MODEL, RESULT> {

	MultiMapToResultImpl(Class<?> resultType, ECollectionType collectionType, ModelCompiler<MODEL> modelCompiler) {
		super(new CollectedQueryResult_Mapped_Multi(resultType, collectionType), modelCompiler);
	}

	@Override
	public <T, R> ISharedResultMapperTo<MODEL, RESULT, R, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>
				map(Function<T, R> getter) {

		return new ResultMapperToImpl<MODEL, RESULT, R, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>(getter, this);
	}

	@Override
	public <T, R> ISharedResultMapperTo<MODEL, RESULT, R, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>> map(
			Supplier<R> getter) {

		return new ResultMapperToImpl<MODEL, RESULT, R, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>(getter, this);
	}
}
