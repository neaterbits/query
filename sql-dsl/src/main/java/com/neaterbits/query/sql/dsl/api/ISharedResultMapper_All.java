package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedResultMapper_All<MODEL, RESULT, 
		NAMED_SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		ALIAS_SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>> 

	extends ISharedResultMapper_Named<MODEL, RESULT, NAMED_SOURCE>,
			ISharedResultMapper_Alias<MODEL, RESULT, ALIAS_SOURCE>
	
	
		// Must map to all functions as well, arithmetic, string and aggregates
		// issue at this point is we do not know whether mapped or alias
	    // and map() would give signature collision if adding both for named and alias so must add both
	
		{

			
			
	// All functions - aggregate, string, datetime and aggregated
	ISharedMapFunctions_Initial<
				MODEL,
				RESULT,

				NAMED_SOURCE,
				ALIAS_SOURCE,
				
				// Named functions
				
				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, NAMED_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, NAMED_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer, NAMED_SOURCE>,

				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Byte, NAMED_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Short, NAMED_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer, NAMED_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, NAMED_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigInteger, NAMED_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Float, NAMED_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Double, NAMED_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigDecimal, NAMED_SOURCE>,
				ISharedResultOps_String_Named<MODEL, RESULT, NAMED_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Date, NAMED_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Calendar, NAMED_SOURCE>,
				ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Date, NAMED_SOURCE>,
				ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Time, NAMED_SOURCE>,
				ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Timestamp, NAMED_SOURCE>,
				
				ISharedResultMapperTo<MODEL, RESULT, Long, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Long, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Integer, NAMED_SOURCE>,
				
				ISharedResultMapperTo<MODEL, RESULT, Byte, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Short, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Integer, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Long, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, BigInteger, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Float, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Double, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, BigDecimal, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, String,  NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, java.util.Date, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, java.util.Calendar, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, java.sql.Date, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, java.sql.Time, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, java.sql.Timestamp, NAMED_SOURCE>,
				
				// Alias functions
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Long, ALIAS_SOURCE>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Long, ALIAS_SOURCE>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Integer, ALIAS_SOURCE>,
				
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Byte, ALIAS_SOURCE>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Short, ALIAS_SOURCE>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Integer, ALIAS_SOURCE>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Long, ALIAS_SOURCE>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, BigInteger, ALIAS_SOURCE>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Float, ALIAS_SOURCE>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Double, ALIAS_SOURCE>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, BigDecimal, ALIAS_SOURCE>,
				ISharedResultOps_String_Alias<MODEL, RESULT, ALIAS_SOURCE>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, java.util.Date, ALIAS_SOURCE>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, java.util.Calendar, ALIAS_SOURCE>,
				ISharedResultOps_NumericSQLTimeType_Alias<MODEL, RESULT, java.sql.Date, ALIAS_SOURCE>,
				ISharedResultOps_NumericSQLTimeType_Alias<MODEL, RESULT, java.sql.Time, ALIAS_SOURCE>,
				ISharedResultOps_NumericSQLTimeType_Alias<MODEL, RESULT, java.sql.Timestamp, ALIAS_SOURCE>,
			
				ISharedResultMapperTo<MODEL, RESULT, Long, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Long, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Integer, ALIAS_SOURCE>,
				
				ISharedResultMapperTo<MODEL, RESULT, Byte, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Short, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Integer, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Long, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, BigInteger, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Float, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Double, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, BigDecimal, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, String,  ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, java.util.Date, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, java.util.Calendar, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, java.sql.Date, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, java.sql.Time, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, java.sql.Timestamp, ALIAS_SOURCE>
	>
				
				map();
	
}
