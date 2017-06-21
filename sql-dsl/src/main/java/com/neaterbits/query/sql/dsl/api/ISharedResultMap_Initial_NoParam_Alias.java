package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedResultMap_Initial_NoParam_Alias<
		MODEL, 
		RESULT,
		RET extends ISharedResultMap_Initial_NoParam_Alias<MODEL, RESULT, RET>>
		
	extends 
		ISharedResultMap_Initial_Alias<MODEL, RESULT, RET>,
		ISharedSelectSourceBuilder<MODEL, RESULT> { // TODO: really select-source? needed for type-checking{
		
		ISharedMapFunctions_All_Alias<
			MODEL,
			RESULT,
			
			RET,

			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, 	   RET>,
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, 	   RET>,
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Integer,    RET>,
			
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Byte, 	   RET>,
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Short, 	   RET>,
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Integer,    RET>,
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, 	   RET>,
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, BigInteger, RET>,
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Float, 	   RET>,
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Double,     RET>,
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, BigDecimal, RET>,
			ISharedResultMap_OpsAndTo_String_Alias<MODEL, RESULT, RET>,
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, java.util.Date, 	   RET>,
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, java.util.Calendar, RET>,
			ISharedResultMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Date, RET>,
			ISharedResultMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Time, RET>,
			ISharedResultMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Timestamp, RET>,
			
			ISharedResultMap_To<MODEL, RESULT, Long, 		 RET>,
			ISharedResultMap_To<MODEL, RESULT, Long, 		 RET>,
			ISharedResultMap_To<MODEL, RESULT, Integer, 	 RET>,
			
			ISharedResultMap_To<MODEL, RESULT, Byte, 	 RET>,
			ISharedResultMap_To<MODEL, RESULT, Short, 	 RET>,
			ISharedResultMap_To<MODEL, RESULT, Integer, 	 RET>,
			ISharedResultMap_To<MODEL, RESULT, Long, 		 RET>,
			ISharedResultMap_To<MODEL, RESULT, BigInteger, RET>,
			ISharedResultMap_To<MODEL, RESULT, Float, 	 RET>,
			ISharedResultMap_To<MODEL, RESULT, Double, 	 RET>,
			ISharedResultMap_To<MODEL, RESULT, BigDecimal, RET>,
			ISharedResultMap_To<MODEL, RESULT, String, 	 RET>,
			ISharedResultMap_To<MODEL, RESULT, java.util.Date, RET>,
			ISharedResultMap_To<MODEL, RESULT, java.util.Calendar, RET>,
			ISharedResultMap_To<MODEL, RESULT, java.sql.Date, RET>,
			ISharedResultMap_To<MODEL, RESULT, java.sql.Time, RET>,
			ISharedResultMap_To<MODEL, RESULT, java.sql.Timestamp, RET>
		> 
		
		map();

}
