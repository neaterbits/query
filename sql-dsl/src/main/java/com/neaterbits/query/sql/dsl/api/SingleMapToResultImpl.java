package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

public class SingleMapToResultImpl<MODEL, RESULT>
	extends BaseMapToResultImpl<MODEL, RESULT>
	implements SingleMapToResult<MODEL, RESULT>,
			   SingleMapToResultTable<MODEL, RESULT>,
			   SingleMapToResultAlias<MODEL, RESULT> {

	SingleMapToResultImpl(Class<?> resultType, ModelCompiler<MODEL> modelCompiler) {
		super(resultType, modelCompiler);
	}

	@Override
	public <T, R> ResultMapperTo<MODEL, RESULT, R, SingleMapToResultTable<MODEL, RESULT>>
				mapF(Function<T, R> getter) {

		return new ResultMapperToImpl<MODEL, RESULT, R, SingleMapToResultTable<MODEL, RESULT>>(getter, this);
	}

	@Override
	public <T, R> ResultMapperTo<MODEL, RESULT, R, SingleMapToResultAlias<MODEL, RESULT>> map(Supplier<R> getter) {

		return new ResultMapperToImpl<MODEL, RESULT, R, SingleMapToResultAlias<MODEL, RESULT>>(getter, this);
	}
}
