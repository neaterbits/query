package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Deprecated
public interface ISharedMapFunctions_All_Named_Concrete<MODEL, RESULT, RET extends ISharedFunction_After<MODEL, RESULT>>
	extends ISharedMapFunctions_All_Named<
		MODEL,
		RESULT,
		
		RET,
	
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, 	   RET>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, 	   RET>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer,    RET>,
	
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Byte, 	   RET>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Short, 	   RET>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer,    RET>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, 	   RET>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigInteger, 	   RET>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Float, 	   RET>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Double,     RET>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigDecimal, RET>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Date, 	   RET>,
		ISharedResultOps_String_Named<MODEL, RESULT, RET>,
		
		ISharedResultMapperTo<MODEL, RESULT, Long, 		 RET>,
		ISharedResultMapperTo<MODEL, RESULT, Long, 		 RET>,
		ISharedResultMapperTo<MODEL, RESULT, Integer, 	 RET>,
	
		ISharedResultMapperTo<MODEL, RESULT, Byte, 	 	RET>,
		ISharedResultMapperTo<MODEL, RESULT, Short, 	 RET>,
		ISharedResultMapperTo<MODEL, RESULT, Integer, 	 RET>,
		ISharedResultMapperTo<MODEL, RESULT, Long, 		 RET>,
		ISharedResultMapperTo<MODEL, RESULT, BigInteger, RET>,
		ISharedResultMapperTo<MODEL, RESULT, Float, 	 RET>,
		ISharedResultMapperTo<MODEL, RESULT, Double,     RET>,
		ISharedResultMapperTo<MODEL, RESULT, BigDecimal, RET>,
		ISharedResultMapperTo<MODEL, RESULT, Date, 		 RET>,
		ISharedResultMapperTo<MODEL, RESULT, String, 	 RET>> {

}
