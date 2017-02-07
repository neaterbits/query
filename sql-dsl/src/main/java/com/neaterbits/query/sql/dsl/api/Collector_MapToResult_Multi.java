package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

final class Collector_MapToResult_Multi<
		MODEL,
		RESULT>

		extends Collector_MapToResult_Base<
			MODEL,
			RESULT,
			IClassicLogical_WhereOrJoin_ProcessResult_Named<MODEL, RESULT>,
			IClassicLogical_WhereOrJoin_ProcessResult_Alias<MODEL, RESULT>>

		implements IClassicResult_Mapped_Multi_All<MODEL, RESULT>,
				   IClassicResult_Mapped_Multi_Named<MODEL, RESULT>,
		           IClassicResult_Mapped_Multi_Alias<MODEL, RESULT> {

	Collector_MapToResult_Multi(Class<?> resultType, ECollectionType collectionType, ModelCompiler<MODEL> modelCompiler) {
		super(new CollectedQueryResult_Mapped_Multi(resultType, collectionType), modelCompiler);
	}

	@Override
	public <T, R> ISharedResultMapperTo<MODEL, RESULT, R, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>
				map(Function<T, R> getter) {

		return new ResultMapperToImpl<
					MODEL,
					RESULT,
					R,
					IClassicResult_Mapped_Multi_Named<MODEL, RESULT>,
					IClassicLogical_WhereOrJoin_ProcessResult_Named<MODEL, RESULT>,
					IClassicLogical_WhereOrJoin_ProcessResult_Alias<MODEL, RESULT>
					>
		(getter, this);
	}

	@Override
	public <T, R> ISharedResultMapperTo<MODEL, RESULT, R, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>> map(
			Supplier<R> getter) {

		return new ResultMapperToImpl<
						MODEL,
						RESULT,
						R,
						IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>,
						IClassicLogical_WhereOrJoin_ProcessResult_Named<MODEL, RESULT>,
						IClassicLogical_WhereOrJoin_ProcessResult_Alias<MODEL, RESULT>
						>
		
		(getter, this);
	}

	@Override
	IClassicLogical_WhereOrJoin_ProcessResult_Named<MODEL, RESULT> createWhereOrJoinForNamed() {
		return new Classic_Collector_WhereOrJoin_ProcessResult_Named<>(this);
	}

	@Override
	IClassicLogical_WhereOrJoin_ProcessResult_Alias<MODEL, RESULT> createWhereOrJoinForAlias() {
		return new Classic_Collector_WhereOrJoin_ProcessResult_Alias<>(this);
	}
}
