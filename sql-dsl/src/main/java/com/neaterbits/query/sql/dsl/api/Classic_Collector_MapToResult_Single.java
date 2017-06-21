package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Supplier;

final class Classic_Collector_MapToResult_Single<MODEL, RESULT>

	extends Classic_Collector_MapToResult_Base<
		MODEL,
		RESULT,
		
		IClassicResult_Mapped_Single_Named<MODEL, RESULT>,
		
		// single-entry so non result processing
		ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL, RESULT>,
		IClassicLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>,
		Void
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
	public <R> ISharedResultMap_To<MODEL, RESULT, R, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>> map(Supplier<R> getter) {
		return new ResultMapperToImpl<>(new FieldExpression(getter), this);
	}
	

	@Override
	ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL, RESULT> createWhereOrJoinForNamed() {
		return new Classic_Collector_WhereOrJoin_SingleResult_Named<>(getThisInitial());
	}

	@Override
	IClassicLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT> createWhereOrJoinForAlias() {
		return new Classic_Collector_WhereOrJoin_SingleResult_Alias<>(getThisInitial());
	}

	@Override
	CollectedQueryResult getCollectedQueryResult() {
		throw new UnsupportedOperationException("Should never be invoked since passes " + CollectedQueryResult.class.getSimpleName() + "  in constructor");

	}

	@Override
	public ISharedMapFunctions_All_Undecided<
		MODEL, 
		RESULT,
	
		IClassicResult_Mapped_Single_Named<MODEL, RESULT>,
		IClassicResult_Mapped_Single_Alias<MODEL, RESULT>, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Byte, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Short, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigInteger, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Float, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Double, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_String_Named<MODEL, RESULT, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Date, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Calendar, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>, 
		ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Date, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>, 
		ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, Time, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, Timestamp, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		
		ISharedResultMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>, 
		ISharedResultMap_To<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		
		ISharedResultMap_To<MODEL, RESULT, Byte, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Short, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, BigInteger, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Float, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Double, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, String, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Date, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Calendar, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, java.sql.Date, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Time, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>, 
		ISharedResultMap_To<MODEL, RESULT, Timestamp, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
		
		ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>, 
		ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		
		ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Byte, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Short, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>, 
		ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, BigInteger, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Float, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Double, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_String_Alias<MODEL, RESULT, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Date, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Calendar, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>, 
		ISharedResultMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Date, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, Time, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, Timestamp, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		
		ISharedResultMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		
		ISharedResultMap_To<MODEL, RESULT, Byte, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Short, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, BigInteger, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>, 
		ISharedResultMap_To<MODEL, RESULT, Float, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Double, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, String, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Date, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Calendar, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, java.sql.Date, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Time, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Timestamp, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
		
		ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		
		ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Byte, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Short, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, BigInteger, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Float, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Double, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_String_Undecided<MODEL, RESULT, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Date, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Calendar, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Date, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, Time, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		ISharedResultMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, Timestamp, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		
		ISharedResultMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		
		ISharedResultMap_To<MODEL, RESULT, Byte, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Short, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, BigInteger, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>, 
		ISharedResultMap_To<MODEL, RESULT, Float, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Double, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		
		
		ISharedResultMap_To<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, String,IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>, 
		ISharedResultMap_To<MODEL, RESULT, Date, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
		ISharedResultMap_To<MODEL, RESULT, Calendar, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>, 
		ISharedResultMap_To<MODEL, RESULT, java.sql.Date, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>, 
		ISharedResultMap_To<MODEL, RESULT, Time, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>, 
		ISharedResultMap_To<MODEL, RESULT, Timestamp, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>
	
	> map() {
		throw new UnsupportedOperationException("TODO - map to result for classic");
	}
}
