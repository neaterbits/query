package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

final class Classic_Collector_SingleResult<MODEL, RESULT>

	extends Classic_Collector_Result<
		MODEL,
		RESULT,

		IClassicResult_Mapped_Single_Named<MODEL, RESULT>,		

		ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL, RESULT>,
		ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>,
	
		ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL, RESULT>,
		IClassicLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>,
		
		IClassicResult_Mapped_Single_Named<MODEL, RESULT>,
		IClassicResult_Mapped_Single_Alias<MODEL, RESULT>,
		
		Void
		>

	implements IClassicResult_Single<MODEL, RESULT> {

	private final ClassicSelect select;
	
	Classic_Collector_SingleResult(ClassicSelect select, SharedSelectSource selectSource, ModelCompiler<MODEL> modelCompiler) {
		super(select, selectSource, modelCompiler);
		
		this.select = select;
	}

	@Override
	Classic_Collector_MapToResult_Base<
					MODEL, RESULT,
					IClassicResult_Mapped_Single_Named<MODEL, RESULT>,
					ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL, RESULT>,
					IClassicLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>,
					Void>
	
			createMapToResult() {
		
		return new Classic_Collector_MapToResult_Single<>(select, getResultType(), getModelCompiler());
	}

	@Override
	ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL, RESULT> createWhereOrJoinForNamed() {
		return new Classic_Collector_WhereOrJoin_SingleResult_Named<>(getThisInitial());
	}

	@Override
	ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT> createWhereOrJoinForAlias() {
		return new Classic_Collector_WhereOrJoin_SingleResult_Alias<>(getThisInitial());
	}

	@Override
	CollectedQueryResult getCollectedQueryResult() {
		// Called directly on initial collector returned from list(), so return multi-entity since no map() calls occured
		return new CollectedQueryResult_Entity_Single(getSelectSource());
	}
	@Override
	public ISharedMap_Functions_All_Undecided<
			MODEL,
			RESULT, 
			
			IClassicResult_Mapped_Single_Named<MODEL, RESULT>,
			IClassicResult_Mapped_Single_Alias<MODEL, RESULT>,
			IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>,
			
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,

			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Byte, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Short, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigInteger, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Float, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Double, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_String_Named<MODEL, RESULT, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Date, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Calendar, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Date, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Time, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Timestamp, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			
			ISharedMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			
			ISharedMap_To<MODEL, RESULT, Byte, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Short, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, BigInteger, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Float, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Double, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, String, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.util.Date, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.util.Calendar, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Date, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Time, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Timestamp, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,

			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Byte, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Short, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, BigInteger, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Float, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Double, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_String_Alias<MODEL, RESULT, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, java.util.Date, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, java.util.Calendar, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Date, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Time, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Timestamp, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,

			ISharedMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			
			ISharedMap_To<MODEL, RESULT, Byte, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Short, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, BigInteger, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Float, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Double, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, String, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.util.Date, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.util.Calendar, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Date, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Time, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Timestamp, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			
			
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,

			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Byte, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Short, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, BigInteger, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Float, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Double, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_String_Undecided<MODEL, RESULT, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, java.util.Date, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, java.util.Calendar, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Date, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Time, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Timestamp, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,

			ISharedMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			
			ISharedMap_To<MODEL, RESULT, Byte, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Short, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, BigInteger, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Float, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, Double, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, String, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.util.Date, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.util.Calendar, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Date, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Time, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>,
			ISharedMap_To<MODEL, RESULT, java.sql.Timestamp, IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>>
				
	
	>
			
			map() {
		
		throw new UnsupportedOperationException("TODO - map to result for classic");
	}
}
