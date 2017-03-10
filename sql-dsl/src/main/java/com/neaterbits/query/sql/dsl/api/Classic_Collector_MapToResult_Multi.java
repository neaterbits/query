package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Supplier;

final class Classic_Collector_MapToResult_Multi<
		MODEL,
		RESULT>

		extends Classic_Collector_MapToResult_Base<
			MODEL,
			RESULT,
			
			IClassicResult_Mapped_Multi_Named<MODEL, RESULT>,
			
			ISQLLogical_WhereOrJoin_MultiMapped_Named<MODEL, RESULT>,
			ISQLLogical_WhereOrJoin_MultiMapped_Alias<MODEL, RESULT>>

		implements IClassicResult_Mapped_Multi_All<MODEL, RESULT>,
				   IClassicResult_Mapped_Multi_Named<MODEL, RESULT>,
		           IClassicResult_Mapped_Multi_Alias<MODEL, RESULT> {

	Classic_Collector_MapToResult_Multi(ClassicSelect select, Class<?> resultType, ECollectionType collectionType, ModelCompiler<MODEL> modelCompiler) {
		super(select, new CollectedQueryResult_Mapped_Multi(resultType, collectionType), modelCompiler);
	}
	

	/*
	@Override
	public <T, R> ISharedResultMapperTo<MODEL, RESULT, R, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>
				map(Function<T, R> getter) {

		return new ResultMapperToImpl<>(getter, this);
	}
	*/
	

	

	@Override
	public <R> ISharedResultMapperTo<MODEL, RESULT, R, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>> map(Supplier<R> getter) {

		return new ResultMapperToImpl<>(new FieldExpression(getter), this);
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


	@Override
	public final ISharedMapFunctions_Initial<

			MODEL,
			RESULT,

			IClassicResult_Mapped_Multi_Named<MODEL, RESULT>,
			IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>,
			

			ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Short, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Integer, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Double, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedResultOps_String_Named<MODEL, RESULT, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			
			ISharedResultMapperTo<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Short, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Integer, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Double, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, String, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,

			
			ISharedResultOps_Numeric_Alias<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedResultOps_Numeric_Alias<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedResultOps_Numeric_Alias<MODEL, RESULT, Short, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedResultOps_Numeric_Alias<MODEL, RESULT, Integer, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedResultOps_Numeric_Alias<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedResultOps_Numeric_Alias<MODEL, RESULT, Double, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedResultOps_Numeric_Alias<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedResultOps_String_Alias<MODEL, RESULT, String, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			
			
			ISharedResultMapperTo<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Short, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Integer, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Double, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, String, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>
		>

			
			map() {
		
		throw new UnsupportedOperationException("TODO - map to result for classic");
	}
}

