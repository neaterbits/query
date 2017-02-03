package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

public class Collector_MapToResult_Single<MODEL, RESULT>

	extends Collector_MapToResult_Base<
		MODEL,
		RESULT,
		
		// single-entry so non result processing
		IClassicLogical_WhereOrJoin_NonProcessResult_Named<MODEL, RESULT>,
		IClassicLogical_WhereOrJoin_NonProcessResult_Alias<MODEL, RESULT>
	>


	implements IClassicResult_Mapped_Single_All<MODEL, RESULT>,
			   IClassicResult_Mapped_Single_Named<MODEL, RESULT>,
			   IClassicResult_Mapped_Single_Alias<MODEL, RESULT> {

	Collector_MapToResult_Single(Class<?> resultType, ModelCompiler<MODEL> modelCompiler) {
		super(new CollectedQueryResult_Mapped_Single(resultType), modelCompiler);
	}

	@Override
	public <T, R> ISharedResultMapperTo<MODEL, RESULT, R, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>
				map(Function<T, R> getter) {

		return new ResultMapperToImpl<
				MODEL,
				RESULT,
				R,
				IClassicResult_Mapped_Single_Named<MODEL, RESULT>,
				IClassicLogical_WhereOrJoin_NonProcessResult_Named<MODEL, RESULT>,
				IClassicLogical_WhereOrJoin_NonProcessResult_Alias<MODEL, RESULT>
		
			>(getter, this);
	}

	@Override
	public <T, R> ISharedResultMapperTo<MODEL, RESULT, R, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>> map(Supplier<R> getter) {

		return new ResultMapperToImpl<
				MODEL,
				RESULT,
				R,
				IClassicResult_Mapped_Single_Alias<MODEL, RESULT>,
				IClassicLogical_WhereOrJoin_NonProcessResult_Named<MODEL, RESULT>,
				IClassicLogical_WhereOrJoin_NonProcessResult_Alias<MODEL, RESULT>
		
			>(getter, this);
	}
}
