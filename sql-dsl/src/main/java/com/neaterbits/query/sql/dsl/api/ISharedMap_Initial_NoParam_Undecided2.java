package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedMap_Initial_NoParam_Undecided2<
	MODEL, 
	RESULT,
	
	NAMED_RET extends ISharedSelectSourceBuilder<MODEL, RESULT>,
	ALIAS_RET extends ISharedSelectSourceBuilder<MODEL, RESULT>,
	UNDECIDED_RET extends ISharedSelectSourceBuilder<MODEL, RESULT>
	
	// RET extends ISharedResult_Mapped_Alias_Base<MODEL, RESULT, RET>
	>
	
	extends 
	ISharedMap_Initial_NoParam_Undecided1<MODEL, RESULT, NAMED_RET, ALIAS_RET, UNDECIDED_RET>,
	ISharedSelectSourceBuilder<MODEL, RESULT> { // TODO: really select-source? needed for type-checking{
	
	ISharedMapFunctions_All_Undecided<
		MODEL,
		RESULT,
		
		//RET,
		NAMED_RET,
		ALIAS_RET,
		UNDECIDED_RET,

		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, 	   NAMED_RET>,
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, 	   NAMED_RET>,
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer,    NAMED_RET>,
		
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Byte, 	   NAMED_RET>,
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Short, 	   NAMED_RET>,
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer,    NAMED_RET>,
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, 	   NAMED_RET>,
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigInteger, NAMED_RET>,
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Float, 	   NAMED_RET>,
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Double,     NAMED_RET>,
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigDecimal, NAMED_RET>,
		ISharedMap_OpsAndTo_String_Named<MODEL, RESULT, NAMED_RET>,
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Date, 	   NAMED_RET>,
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Calendar, NAMED_RET>,
		ISharedMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Date, NAMED_RET>,
		ISharedMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Time, NAMED_RET>,
		ISharedMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Timestamp, NAMED_RET>,
		
		ISharedMap_To<MODEL, RESULT, Long, 		 NAMED_RET>,
		ISharedMap_To<MODEL, RESULT, Long, 		 NAMED_RET>,
		ISharedMap_To<MODEL, RESULT, Integer, 	 NAMED_RET>,
		
		ISharedMap_To<MODEL, RESULT, Byte, 	 NAMED_RET>,
		ISharedMap_To<MODEL, RESULT, Short, 	 NAMED_RET>,
		ISharedMap_To<MODEL, RESULT, Integer, 	 NAMED_RET>,
		ISharedMap_To<MODEL, RESULT, Long, 		 NAMED_RET>,
		ISharedMap_To<MODEL, RESULT, BigInteger, NAMED_RET>,
		ISharedMap_To<MODEL, RESULT, Float, 	 NAMED_RET>,
		ISharedMap_To<MODEL, RESULT, Double, 	 NAMED_RET>,
		ISharedMap_To<MODEL, RESULT, BigDecimal, NAMED_RET>,
		ISharedMap_To<MODEL, RESULT, String, 	 NAMED_RET>,
		ISharedMap_To<MODEL, RESULT, java.util.Date, NAMED_RET>,
		ISharedMap_To<MODEL, RESULT, java.util.Calendar, NAMED_RET>,
		ISharedMap_To<MODEL, RESULT, java.sql.Date, NAMED_RET>,
		ISharedMap_To<MODEL, RESULT, java.sql.Time, NAMED_RET>,
		ISharedMap_To<MODEL, RESULT, java.sql.Timestamp, NAMED_RET>,
		
		ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, 	   ALIAS_RET>,
		ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, 	   ALIAS_RET>,
		ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Integer,    ALIAS_RET>,
		
		ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Byte, 	   ALIAS_RET>,
		ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Short, 	   ALIAS_RET>,
		ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Integer,    ALIAS_RET>,
		ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, 	   ALIAS_RET>,
		ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, BigInteger, ALIAS_RET>,
		ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Float, 	   ALIAS_RET>,
		ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Double,     ALIAS_RET>,
		ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, BigDecimal, ALIAS_RET>,
		ISharedMap_OpsAndTo_String_Alias<MODEL, RESULT, ALIAS_RET>,
		ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, java.util.Date, 	   ALIAS_RET>,
		ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, java.util.Calendar, ALIAS_RET>,
		ISharedMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Date, ALIAS_RET>,
		ISharedMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Time, ALIAS_RET>,
		ISharedMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Timestamp, ALIAS_RET>,
		
		ISharedMap_To<MODEL, RESULT, Long, 		 ALIAS_RET>,
		ISharedMap_To<MODEL, RESULT, Long, 		 ALIAS_RET>,
		ISharedMap_To<MODEL, RESULT, Integer, 	 ALIAS_RET>,
		
		ISharedMap_To<MODEL, RESULT, Byte, 	 ALIAS_RET>,
		ISharedMap_To<MODEL, RESULT, Short, 	 ALIAS_RET>,
		ISharedMap_To<MODEL, RESULT, Integer, 	 ALIAS_RET>,
		ISharedMap_To<MODEL, RESULT, Long, 		 ALIAS_RET>,
		ISharedMap_To<MODEL, RESULT, BigInteger, ALIAS_RET>,
		ISharedMap_To<MODEL, RESULT, Float, 	 ALIAS_RET>,
		ISharedMap_To<MODEL, RESULT, Double, 	 ALIAS_RET>,
		ISharedMap_To<MODEL, RESULT, BigDecimal, ALIAS_RET>,
		ISharedMap_To<MODEL, RESULT, String, 	 ALIAS_RET>,
		ISharedMap_To<MODEL, RESULT, java.util.Date, ALIAS_RET>,
		ISharedMap_To<MODEL, RESULT, java.util.Calendar, ALIAS_RET>,
		ISharedMap_To<MODEL, RESULT, java.sql.Date, ALIAS_RET>,
		ISharedMap_To<MODEL, RESULT, java.sql.Time, ALIAS_RET>,
		ISharedMap_To<MODEL, RESULT, java.sql.Timestamp, ALIAS_RET>,
		
		
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, 	   UNDECIDED_RET>,
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, 	   UNDECIDED_RET>,
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Integer,    UNDECIDED_RET>,
		
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Byte, 	   UNDECIDED_RET>,
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Short, 	   UNDECIDED_RET>,
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Integer,    UNDECIDED_RET>,
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, 	   UNDECIDED_RET>,
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, BigInteger, UNDECIDED_RET>,
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Float, 	   UNDECIDED_RET>,
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Double,     UNDECIDED_RET>,
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, BigDecimal, UNDECIDED_RET>,
		ISharedMap_OpsAndTo_String_Undecided<MODEL, RESULT, UNDECIDED_RET>,
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, java.util.Date, 	   UNDECIDED_RET>,
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, java.util.Calendar, UNDECIDED_RET>,
		ISharedMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Date, UNDECIDED_RET>,
		ISharedMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Time, UNDECIDED_RET>,
		ISharedMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Timestamp, UNDECIDED_RET>,
		
		ISharedMap_To<MODEL, RESULT, Long, 		 UNDECIDED_RET>,
		ISharedMap_To<MODEL, RESULT, Long, 		 UNDECIDED_RET>,
		ISharedMap_To<MODEL, RESULT, Integer, 	 UNDECIDED_RET>,
		
		ISharedMap_To<MODEL, RESULT, Byte, 	 UNDECIDED_RET>,
		ISharedMap_To<MODEL, RESULT, Short, 	 UNDECIDED_RET>,
		ISharedMap_To<MODEL, RESULT, Integer, 	 UNDECIDED_RET>,
		ISharedMap_To<MODEL, RESULT, Long, 		 UNDECIDED_RET>,
		ISharedMap_To<MODEL, RESULT, BigInteger, UNDECIDED_RET>,
		ISharedMap_To<MODEL, RESULT, Float, 	 UNDECIDED_RET>,
		ISharedMap_To<MODEL, RESULT, Double, 	 UNDECIDED_RET>,
		ISharedMap_To<MODEL, RESULT, BigDecimal, UNDECIDED_RET>,
		ISharedMap_To<MODEL, RESULT, String, 	 UNDECIDED_RET>,
		ISharedMap_To<MODEL, RESULT, java.util.Date, UNDECIDED_RET>,
		ISharedMap_To<MODEL, RESULT, java.util.Calendar, UNDECIDED_RET>,
		ISharedMap_To<MODEL, RESULT, java.sql.Date, UNDECIDED_RET>,
		ISharedMap_To<MODEL, RESULT, java.sql.Time, UNDECIDED_RET>,
		ISharedMap_To<MODEL, RESULT, java.sql.Timestamp, UNDECIDED_RET>
	> 

		map();
}