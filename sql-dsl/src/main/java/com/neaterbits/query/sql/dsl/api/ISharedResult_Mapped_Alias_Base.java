package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedResult_Mapped_Alias_Base<
		MODEL, 
		RESULT,
		RET extends ISharedResult_Mapped_Alias_Base<MODEL, RESULT, RET>>
		
	extends 
		ISharedResultMapper_Alias<MODEL, RESULT, RET>,
		ISharedSelectSourceBuilder<MODEL, RESULT> { // TODO: really select-source? needed for type-checking{
		
		ISharedMapFunctions_All_Alias<
			MODEL,
			RESULT,
			
			RET,

			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, 	   RET>,
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, 	   RET>,
		
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Byte, 	   RET>,
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Short, 	   RET>,
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Integer,    RET>,
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, 	   RET>,
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, BigInteger, RET>,
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Float, 	   RET>,
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Double,     RET>,
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, BigDecimal, RET>,
			ISharedResultOps_String_Alias<MODEL, RESULT, RET>,
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, java.util.Date, 	   RET>,
			ISharedResultMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, java.util.Calendar, RET>,
			ISharedResultMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Date, RET>,
			ISharedResultMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Time, RET>,
			ISharedResultMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Timestamp, RET>,
			
			ISharedResultMapperTo<MODEL, RESULT, Long, 		 RET>,
			ISharedResultMapperTo<MODEL, RESULT, Long, 		 RET>,
		
			ISharedResultMapperTo<MODEL, RESULT, Byte, 	 RET>,
			ISharedResultMapperTo<MODEL, RESULT, Short, 	 RET>,
			ISharedResultMapperTo<MODEL, RESULT, Integer, 	 RET>,
			ISharedResultMapperTo<MODEL, RESULT, Long, 		 RET>,
			ISharedResultMapperTo<MODEL, RESULT, BigInteger, RET>,
			ISharedResultMapperTo<MODEL, RESULT, Float, 	 RET>,
			ISharedResultMapperTo<MODEL, RESULT, Double, 	 RET>,
			ISharedResultMapperTo<MODEL, RESULT, BigDecimal, RET>,
			ISharedResultMapperTo<MODEL, RESULT, String, 	 RET>,
			ISharedResultMapperTo<MODEL, RESULT, java.util.Date, RET>,
			ISharedResultMapperTo<MODEL, RESULT, java.util.Calendar, RET>,
			ISharedResultMapperTo<MODEL, RESULT, java.sql.Date, RET>,
			ISharedResultMapperTo<MODEL, RESULT, java.sql.Time, RET>,
			ISharedResultMapperTo<MODEL, RESULT, java.sql.Timestamp, RET>
		> 
		
		map();

}
