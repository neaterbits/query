package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

public class Collector_MapToResult_Single<MODEL, RESULT>
	extends Collector_MapToResult_Base<MODEL, RESULT>
	implements IClassicSingleMapToResult<MODEL, RESULT>,
			   IClassicSingleMapToResultNamed<MODEL, RESULT>,
			   IClassicSingleMapToResultAlias<MODEL, RESULT> {

	Collector_MapToResult_Single(Class<?> resultType, ModelCompiler<MODEL> modelCompiler) {
		super(new CollectedQueryResult_Mapped_Single(resultType), modelCompiler);
	}

	@Override
	public <T, R> ISharedResultMapperTo<MODEL, RESULT, R, IClassicSingleMapToResultNamed<MODEL, RESULT>>
				map(Function<T, R> getter) {

		return new ResultMapperToImpl<MODEL, RESULT, R, IClassicSingleMapToResultNamed<MODEL, RESULT>>(getter, this);
	}

	@Override
	public <T, R> ISharedResultMapperTo<MODEL, RESULT, R, IClassicSingleMapToResultAlias<MODEL, RESULT>> map(Supplier<R> getter) {

		return new ResultMapperToImpl<MODEL, RESULT, R, IClassicSingleMapToResultAlias<MODEL, RESULT>>(getter, this);
	}
}
