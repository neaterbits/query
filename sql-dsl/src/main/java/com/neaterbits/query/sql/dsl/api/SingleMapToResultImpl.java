package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

public class SingleMapToResultImpl<MODEL, RESULT>
	extends BaseMapToResultImpl<MODEL, RESULT>
	implements IClassicSingleMapToResult<MODEL, RESULT>,
			   IClassicSingleMapToResultTable<MODEL, RESULT>,
			   IClassicSingleMapToResultAlias<MODEL, RESULT> {

	SingleMapToResultImpl(Class<?> resultType, ModelCompiler<MODEL> modelCompiler) {
		super(new QueryResultMappedSingle(resultType), modelCompiler);
	}

	@Override
	public <T, R> ISharedResultMapperTo<MODEL, RESULT, R, IClassicSingleMapToResultTable<MODEL, RESULT>>
				map(Function<T, R> getter) {

		return new ResultMapperToImpl<MODEL, RESULT, R, IClassicSingleMapToResultTable<MODEL, RESULT>>(getter, this);
	}

	@Override
	public <T, R> ISharedResultMapperTo<MODEL, RESULT, R, IClassicSingleMapToResultAlias<MODEL, RESULT>> map(Supplier<R> getter) {

		return new ResultMapperToImpl<MODEL, RESULT, R, IClassicSingleMapToResultAlias<MODEL, RESULT>>(getter, this);
	}
}
