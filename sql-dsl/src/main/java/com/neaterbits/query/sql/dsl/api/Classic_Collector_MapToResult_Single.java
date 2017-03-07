package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Supplier;

final class Classic_Collector_MapToResult_Single<MODEL, RESULT>

	extends Classic_Collector_MapToResult_Base<
		MODEL,
		RESULT,
		
		IClassicResult_Mapped_Single_Named<MODEL, RESULT>,
		
		// single-entry so non result processing
		ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL, RESULT>,
		ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>
	>


	implements IClassicResult_Mapped_Single_All<MODEL, RESULT>,
			   IClassicResult_Mapped_Single_Named<MODEL, RESULT>,
			   IClassicResult_Mapped_Single_Alias<MODEL, RESULT> {

	Classic_Collector_MapToResult_Single(ClassicSelect select, Class<?> resultType, ModelCompiler<MODEL> modelCompiler) {
		super(select, new CollectedQueryResult_Mapped_Single(resultType), modelCompiler);
	}

	/*
	@Override
	public <T, R> ISharedResultMapperTo<MODEL, RESULT, R, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>
				map(Function<T, R> getter) {

		return new ResultMapperToImpl<>(getter, this);
	}
	*/

	@Override
	public <R> ISharedResultMapperTo<MODEL, RESULT, R, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>> map(Supplier<R> getter) {
		return new ResultMapperToImpl<>(getter, this);
	}
	

	@Override
	ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL, RESULT> createWhereOrJoinForNamed() {
		return new SQL_Collector_WhereOrJoin_SingleResult_Named<>(this);
	}

	@Override
	ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT> createWhereOrJoinForAlias() {
		return new SQL_Collector_WhereOrJoin_SingleResult_Alias<>(this);
	}

	@Override
	CollectedQueryResult getCollectedQueryResult() {
		throw new UnsupportedOperationException("Should never be invoked since passes " + CollectedQueryResult.class.getSimpleName() + "  in constructor");
	}

	@Override
	public ISharedMapFunctions_Initial<
				MODEL,
				RESULT,
				
				IClassicResult_Mapped_Single_Named<MODEL, RESULT>,
				IClassicResult_Mapped_Single_Alias<MODEL, RESULT>,

				ISharedResultOps_Numeric_Named<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultOps_Numeric_Named<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultOps_Numeric_Named<MODEL, RESULT, Short, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultOps_Numeric_Named<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultOps_Numeric_Named<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultOps_Numeric_Named<MODEL, RESULT, Double, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultOps_Numeric_Named<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultOps_String_Named<MODEL, RESULT, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
				
				ISharedResultMapperTo<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Short, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Double, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, String, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,

				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Short, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>, 
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Double, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>, 
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultOps_String_Alias<MODEL, RESULT, String, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
				
				ISharedResultMapperTo<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Short, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>, 
				ISharedResultMapperTo<MODEL, RESULT, Double, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>, 
				ISharedResultMapperTo<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, String, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>
			> 
				map() {
		throw new UnsupportedOperationException("TODO - map to result for classic");
	}
}
