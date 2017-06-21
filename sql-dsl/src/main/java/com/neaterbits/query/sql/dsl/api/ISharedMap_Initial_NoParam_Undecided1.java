package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedMap_Initial_NoParam_Undecided1<MODEL, RESULT, 
		NAMED_SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		ALIAS_SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		UNDECIDED_SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>> 

	extends ISharedMap_Initial_Named<MODEL, RESULT, NAMED_SOURCE>,
			ISharedMap_Initial_Alias<MODEL, RESULT, ALIAS_SOURCE>
	
	
		// Must map to all functions as well, arithmetic, string and aggregates
		// issue at this point is we do not know whether mapped or alias
	    // and map() would give signature collision if adding both for named and alias so must add both
	
		{

			
			
	// All functions - aggregate, string, datetime and aggregated
	ISharedMapFunctions_All_Undecided<
				MODEL,
				RESULT,

				NAMED_SOURCE,
				ALIAS_SOURCE,
				UNDECIDED_SOURCE,
				
				// Named functions
				
				ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, NAMED_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, NAMED_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer, NAMED_SOURCE>,

				ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Byte, NAMED_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Short, NAMED_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer, NAMED_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, NAMED_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigInteger, NAMED_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Float, NAMED_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Double, NAMED_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigDecimal, NAMED_SOURCE>,
				ISharedMap_OpsAndTo_String_Named<MODEL, RESULT, NAMED_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Date, NAMED_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Calendar, NAMED_SOURCE>,
				ISharedMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Date, NAMED_SOURCE>,
				ISharedMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Time, NAMED_SOURCE>,
				ISharedMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Timestamp, NAMED_SOURCE>,
				
				ISharedMap_To<MODEL, RESULT, Long, NAMED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, Long, NAMED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, Integer, NAMED_SOURCE>,
				
				ISharedMap_To<MODEL, RESULT, Byte, NAMED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, Short, NAMED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, Integer, NAMED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, Long, NAMED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, BigInteger, NAMED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, Float, NAMED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, Double, NAMED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, BigDecimal, NAMED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, String,  NAMED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, java.util.Date, NAMED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, java.util.Calendar, NAMED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, java.sql.Date, NAMED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, java.sql.Time, NAMED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, java.sql.Timestamp, NAMED_SOURCE>,
				
				// Alias functions
				ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, ALIAS_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, ALIAS_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Integer, ALIAS_SOURCE>,
				
				ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Byte, ALIAS_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Short, ALIAS_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Integer, ALIAS_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, ALIAS_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, BigInteger, ALIAS_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Float, ALIAS_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Double, ALIAS_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, BigDecimal, ALIAS_SOURCE>,
				ISharedMap_OpsAndTo_String_Alias<MODEL, RESULT, ALIAS_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, java.util.Date, ALIAS_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, java.util.Calendar, ALIAS_SOURCE>,
				ISharedMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Date, ALIAS_SOURCE>,
				ISharedMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Time, ALIAS_SOURCE>,
				ISharedMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Timestamp, ALIAS_SOURCE>,
			
				ISharedMap_To<MODEL, RESULT, Long, ALIAS_SOURCE>,
				ISharedMap_To<MODEL, RESULT, Long, ALIAS_SOURCE>,
				ISharedMap_To<MODEL, RESULT, Integer, ALIAS_SOURCE>,
				
				ISharedMap_To<MODEL, RESULT, Byte, ALIAS_SOURCE>,
				ISharedMap_To<MODEL, RESULT, Short, ALIAS_SOURCE>,
				ISharedMap_To<MODEL, RESULT, Integer, ALIAS_SOURCE>,
				ISharedMap_To<MODEL, RESULT, Long, ALIAS_SOURCE>,
				ISharedMap_To<MODEL, RESULT, BigInteger, ALIAS_SOURCE>,
				ISharedMap_To<MODEL, RESULT, Float, ALIAS_SOURCE>,
				ISharedMap_To<MODEL, RESULT, Double, ALIAS_SOURCE>,
				ISharedMap_To<MODEL, RESULT, BigDecimal, ALIAS_SOURCE>,
				ISharedMap_To<MODEL, RESULT, String,  ALIAS_SOURCE>,
				ISharedMap_To<MODEL, RESULT, java.util.Date, ALIAS_SOURCE>,
				ISharedMap_To<MODEL, RESULT, java.util.Calendar, ALIAS_SOURCE>,
				ISharedMap_To<MODEL, RESULT, java.sql.Date, ALIAS_SOURCE>,
				ISharedMap_To<MODEL, RESULT, java.sql.Time, ALIAS_SOURCE>,
				ISharedMap_To<MODEL, RESULT, java.sql.Timestamp, ALIAS_SOURCE>,
				
				// Undecided functions
				ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, UNDECIDED_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, UNDECIDED_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Integer, UNDECIDED_SOURCE>,
				
				ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Byte, UNDECIDED_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Short, UNDECIDED_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Integer, UNDECIDED_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, UNDECIDED_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, BigInteger, UNDECIDED_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Float, UNDECIDED_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Double, UNDECIDED_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, BigDecimal, UNDECIDED_SOURCE>,
				ISharedMap_OpsAndTo_String_Undecided<MODEL, RESULT, UNDECIDED_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, java.util.Date, UNDECIDED_SOURCE>,
				ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, java.util.Calendar, UNDECIDED_SOURCE>,
				ISharedMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Date, UNDECIDED_SOURCE>,
				ISharedMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Time, UNDECIDED_SOURCE>,
				ISharedMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Timestamp, UNDECIDED_SOURCE>,
			
				ISharedMap_To<MODEL, RESULT, Long, UNDECIDED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, Long, UNDECIDED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, Integer, UNDECIDED_SOURCE>,
				
				ISharedMap_To<MODEL, RESULT, Byte, UNDECIDED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, Short, UNDECIDED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, Integer, UNDECIDED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, Long, UNDECIDED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, BigInteger, UNDECIDED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, Float, UNDECIDED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, Double, UNDECIDED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, BigDecimal, UNDECIDED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, String,  UNDECIDED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, java.util.Date, UNDECIDED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, java.util.Calendar, UNDECIDED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, java.sql.Date, UNDECIDED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, java.sql.Time, UNDECIDED_SOURCE>,
				ISharedMap_To<MODEL, RESULT, java.sql.Timestamp, UNDECIDED_SOURCE>				
	>
				
				map();
	
}
