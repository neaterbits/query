package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

final class Classic_Collector_MapToResult_Multi<
		MODEL,
		RESULT>

		extends Classic_Collector_MapToResult_Base<
			MODEL,
			RESULT,
			ISQLLogical_WhereOrJoin_MultiMapped_Named<MODEL, RESULT>,
			ISQLLogical_WhereOrJoin_MultiMapped_Alias<MODEL, RESULT>>

		implements IClassicResult_Mapped_Multi_All<MODEL, RESULT>,
				   IClassicResult_Mapped_Multi_Named<MODEL, RESULT>,
		           IClassicResult_Mapped_Multi_Alias<MODEL, RESULT> {

	Classic_Collector_MapToResult_Multi(Class<?> resultType, ECollectionType collectionType, ModelCompiler<MODEL> modelCompiler) {
		super(new CollectedQueryResult_Mapped_Multi(resultType, collectionType), modelCompiler);
	}
	

	@Override
	public <T, R> ISharedResultMapperTo<MODEL, RESULT, R, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>
				map(Function<T, R> getter) {

		return new ResultMapperToImpl<>(getter, this);
	}

	@Override
	public <T, R> ISharedResultMapperTo<MODEL, RESULT, R, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>> map(
			Supplier<R> getter) {

		return new ResultMapperToImpl<>(getter, this);
	}

	@Override
	ISQLLogical_WhereOrJoin_MultiMapped_Named<MODEL, RESULT> createWhereOrJoinForNamed() {
		return new SQL_Collector_WhereOrJoin_MultiMapped_Named<>(this);
	}

	@Override
	ISQLLogical_WhereOrJoin_MultiMapped_Alias<MODEL, RESULT> createWhereOrJoinForAlias() {
		return new SQL_Collector_WhereOrJoin_MultiMapped_Alias<>(this);
	}

	@Override
	CollectedQueryResult getCollectedQueryResult() {
		throw new UnsupportedOperationException("Should never be invoked since passes " + CollectedQueryResult.class.getSimpleName() + "  in constructor");
	}
}
