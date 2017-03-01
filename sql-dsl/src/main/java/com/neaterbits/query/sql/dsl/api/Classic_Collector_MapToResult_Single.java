package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

final class Classic_Collector_MapToResult_Single<MODEL, RESULT>

	extends Classic_Collector_MapToResult_Base<
		MODEL,
		RESULT,
		
		// single-entry so non result processing
		ISQLLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT>,
		ISQLLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT>
	>


	implements IClassicResult_Mapped_Single_All<MODEL, RESULT>,
			   IClassicResult_Mapped_Single_Named<MODEL, RESULT>,
			   IClassicResult_Mapped_Single_Alias<MODEL, RESULT> {

	Classic_Collector_MapToResult_Single(Class<?> resultType, ModelCompiler<MODEL> modelCompiler) {
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
				ISQLLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT>,
				ISQLLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT>
		
			>(getter, this);
	}

	@Override
	public <T, R> ISharedResultMapperTo<MODEL, RESULT, R, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>> map(Supplier<R> getter) {

		return new ResultMapperToImpl<
				MODEL,
				RESULT,
				R,
				IClassicResult_Mapped_Single_Alias<MODEL, RESULT>,
				ISQLLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT>,
				ISQLLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT>
		
			>(getter, this);
	}

	@Override
	ISQLLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT> createWhereOrJoinForNamed() {
		return new SQL_Collector_WhereOrJoin_SingleResult_Named<>(this);
	}

	@Override
	ISQLLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT> createWhereOrJoinForAlias() {
		return new SQL_Collector_WhereOrJoin_SingleResult_Alias<>(this);
	}

	@Override
	CollectedQueryResult getCollectedQueryResult() {
		throw new UnsupportedOperationException("Should never be invoked since passes " + CollectedQueryResult.class.getSimpleName() + "  in constructor");
	}
}
