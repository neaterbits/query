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
	public ISharedMapFunctions_Initial<
			MODEL,
			RESULT, 
			
			IClassicResult_Mapped_Single_Named<MODEL, RESULT>,
			IClassicResult_Mapped_Single_Alias<MODEL, RESULT>,
			
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
			ISharedResultOps_String_Named<MODEL, RESULT, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Date, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Calendar, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Date, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Time, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Timestamp, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			
			ISharedResultMapperTo<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			
			ISharedResultMapperTo<MODEL, RESULT, Byte, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Short, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, BigInteger, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Float, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Double, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, String, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, java.util.Date, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, java.util.Calendar, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, java.sql.Date, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, java.sql.Time, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, java.sql.Timestamp, IClassicResult_Mapped_Single_Named<MODEL, RESULT>>,
			
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
			ISharedResultOps_String_Alias<MODEL, RESULT, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, java.util.Date, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, java.util.Calendar, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultOps_NumericSQLTimeType_Alias<MODEL, RESULT, java.sql.Date, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultOps_NumericSQLTimeType_Alias<MODEL, RESULT, java.sql.Time, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultOps_NumericSQLTimeType_Alias<MODEL, RESULT, java.sql.Timestamp, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,

			ISharedResultMapperTo<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			
			ISharedResultMapperTo<MODEL, RESULT, Byte, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Short, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Integer, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Long, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, BigInteger, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Float, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Double, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, BigDecimal, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, String, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, java.util.Date, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, java.util.Calendar, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, java.sql.Date, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, java.sql.Time, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, java.sql.Timestamp, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>>>
			
			map() {
		
		throw new UnsupportedOperationException("TODO - map to result for classic");
	}
}
