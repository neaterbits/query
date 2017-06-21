package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedMap_Initial_NoParam_Alias<
		MODEL, 
		RESULT,
		RET extends ISharedMap_Initial_NoParam_Alias<MODEL, RESULT, RET>>
		
	extends 
		ISharedMap_Initial_Alias<MODEL, RESULT, RET>,
		ISharedSelectSourceBuilder<MODEL, RESULT> { // TODO: really select-source? needed for type-checking{
		
		ISharedMapFunctions_All_Alias<
			MODEL,
			RESULT,
			
			RET,

			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, 	   RET>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, 	   RET>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Integer,    RET>,
			
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Byte, 	   RET>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Short, 	   RET>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Integer,    RET>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, 	   RET>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, BigInteger, RET>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Float, 	   RET>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Double,     RET>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, BigDecimal, RET>,
			ISharedMap_OpsAndTo_String_Alias<MODEL, RESULT, RET>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, java.util.Date, 	   RET>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, java.util.Calendar, RET>,
			ISharedMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Date, RET>,
			ISharedMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Time, RET>,
			ISharedMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Timestamp, RET>,
			
			ISharedMap_To<MODEL, RESULT, Long, 		 RET>,
			ISharedMap_To<MODEL, RESULT, Long, 		 RET>,
			ISharedMap_To<MODEL, RESULT, Integer, 	 RET>,
			
			ISharedMap_To<MODEL, RESULT, Byte, 	 RET>,
			ISharedMap_To<MODEL, RESULT, Short, 	 RET>,
			ISharedMap_To<MODEL, RESULT, Integer, 	 RET>,
			ISharedMap_To<MODEL, RESULT, Long, 		 RET>,
			ISharedMap_To<MODEL, RESULT, BigInteger, RET>,
			ISharedMap_To<MODEL, RESULT, Float, 	 RET>,
			ISharedMap_To<MODEL, RESULT, Double, 	 RET>,
			ISharedMap_To<MODEL, RESULT, BigDecimal, RET>,
			ISharedMap_To<MODEL, RESULT, String, 	 RET>,
			ISharedMap_To<MODEL, RESULT, java.util.Date, RET>,
			ISharedMap_To<MODEL, RESULT, java.util.Calendar, RET>,
			ISharedMap_To<MODEL, RESULT, java.sql.Date, RET>,
			ISharedMap_To<MODEL, RESULT, java.sql.Time, RET>,
			ISharedMap_To<MODEL, RESULT, java.sql.Timestamp, RET>
		> 
		
		map();

}
