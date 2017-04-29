package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedResultMapper_All<MODEL, RESULT, 
		NAMED_SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		ALIAS_SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		UNDECIDED_SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>> 

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
				UNDECIDED_SOURCE,
				
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
				ISharedResultMap_OpsAndTo_String_Named<MODEL, RESULT, NAMED_SOURCE>,
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
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, ALIAS_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, ALIAS_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Integer, ALIAS_SOURCE>,
				
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Byte, ALIAS_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Short, ALIAS_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Integer, ALIAS_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, ALIAS_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, BigInteger, ALIAS_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Float, ALIAS_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Double, ALIAS_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, BigDecimal, ALIAS_SOURCE>,
				ISharedResultMap_OpsAndTo_String_Alias<MODEL, RESULT, ALIAS_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, java.util.Date, ALIAS_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, java.util.Calendar, ALIAS_SOURCE>,
				ISharedResultMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Date, ALIAS_SOURCE>,
				ISharedResultMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Time, ALIAS_SOURCE>,
				ISharedResultMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Timestamp, ALIAS_SOURCE>,
			
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
				ISharedResultMapperTo<MODEL, RESULT, java.sql.Timestamp, ALIAS_SOURCE>,
				
				// Undecided functions
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, UNDECIDED_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, UNDECIDED_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Integer, UNDECIDED_SOURCE>,
				
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Byte, UNDECIDED_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Short, UNDECIDED_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Integer, UNDECIDED_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, UNDECIDED_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, BigInteger, UNDECIDED_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Float, UNDECIDED_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Double, UNDECIDED_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, BigDecimal, UNDECIDED_SOURCE>,
				ISharedResultMap_OpsAndTo_String_Undecided<MODEL, RESULT, UNDECIDED_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, java.util.Date, UNDECIDED_SOURCE>,
				ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, java.util.Calendar, UNDECIDED_SOURCE>,
				ISharedResultMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Date, UNDECIDED_SOURCE>,
				ISharedResultMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Time, UNDECIDED_SOURCE>,
				ISharedResultMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Timestamp, UNDECIDED_SOURCE>,
			
				ISharedResultMapperTo<MODEL, RESULT, Long, UNDECIDED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Long, UNDECIDED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Integer, UNDECIDED_SOURCE>,
				
				ISharedResultMapperTo<MODEL, RESULT, Byte, UNDECIDED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Short, UNDECIDED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Integer, UNDECIDED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Long, UNDECIDED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, BigInteger, UNDECIDED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Float, UNDECIDED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Double, UNDECIDED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, BigDecimal, UNDECIDED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, String,  UNDECIDED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, java.util.Date, UNDECIDED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, java.util.Calendar, UNDECIDED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, java.sql.Date, UNDECIDED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, java.sql.Time, UNDECIDED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, java.sql.Timestamp, UNDECIDED_SOURCE>				
	>
				
				map();
	
}
