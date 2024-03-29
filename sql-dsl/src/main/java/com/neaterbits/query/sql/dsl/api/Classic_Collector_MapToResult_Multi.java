package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Supplier;

final class Classic_Collector_MapToResult_Multi<
		MODEL,
		RESULT>

		extends Classic_Collector_MapToResult_Base<
			MODEL,
			RESULT,
			
			IClassicResult_Mapped_Multi_Named<MODEL, RESULT>,
			
			ISQLLogical_WhereOrJoin_MultiMapped_Named<MODEL, RESULT>,
			IClassicLogical_WhereOrJoin_MultiMapped_Alias<MODEL, RESULT>,
			Void>

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
	public <R> ISharedMap_To<MODEL, RESULT, R, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>> map(Supplier<R> getter) {

		return new ResultMapperToImpl<>(new FieldExpression(getter), this);
	}

	@Override
	ISQLLogical_WhereOrJoin_MultiMapped_Named<MODEL, RESULT> createWhereOrJoinForNamed() {
		return new Classic_Collector_WhereOrJoin_MultiMapped_Named<>(getThisInitial());
	}

	@Override
	IClassicLogical_WhereOrJoin_MultiMapped_Alias<MODEL, RESULT> createWhereOrJoinForAlias() {
		return new Classic_Collector_WhereOrJoin_MultiMapped_Alias<>(getThisInitial());
	}

	@Override
	CollectedQueryResult getCollectedQueryResult() {
		throw new UnsupportedOperationException("Should never be invoked since passes " + CollectedQueryResult.class.getSimpleName() + "  in constructor");
	}


	@Override
	public final ISharedMap_Functions_All_Undecided<

			MODEL,
			RESULT,

			IClassicResult_Mapped_Multi_Named<MODEL, RESULT>,
			IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>,
			IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>,
			

			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,

			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Byte, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Short, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigInteger, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Float, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Double, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_String_Named<MODEL, RESULT, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Date, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Calendar, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Date, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Time, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Timestamp, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			
			ISharedMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Integer, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			
			ISharedMap_To<MODEL, RESULT, Byte, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Short, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Integer, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, BigInteger, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Float, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Double, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, String, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.util.Date, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.util.Calendar, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Date, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Time, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Timestamp, IClassicResult_Mapped_Multi_Named<MODEL, RESULT>>,

			
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Integer, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Byte, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Short, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Integer, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, BigInteger, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Float, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Double, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_String_Alias<MODEL, RESULT, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, java.util.Date, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, java.util.Calendar, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Date, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Time, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Timestamp, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			
			
			ISharedMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Integer, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			
			ISharedMap_To<MODEL, RESULT, Byte, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Short, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Integer, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, BigInteger, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Float, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Double, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, String, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.util.Date, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.util.Calendar, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Date, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Time, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Timestamp, IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>>,
			
			
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Integer, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Byte, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Short, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Integer, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, BigInteger, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Float, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Double, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_String_Undecided<MODEL, RESULT, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, java.util.Date, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, java.util.Calendar, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Date, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Time, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Timestamp, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			
			
			ISharedMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Integer, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			
			ISharedMap_To<MODEL, RESULT, Byte, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Short, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Integer, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, BigInteger, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Float, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Double, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, String, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.util.Date, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.util.Calendar, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Date, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Time, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Timestamp, IClassicResult_Mapped_Multi_Undecided<MODEL, RESULT>>
			
		>

			
			map() {
		
		throw new UnsupportedOperationException("TODO - map to result for classic");
	}
}

