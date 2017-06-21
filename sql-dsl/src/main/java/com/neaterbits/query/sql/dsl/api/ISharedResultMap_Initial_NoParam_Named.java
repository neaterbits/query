package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public interface ISharedResultMap_Initial_NoParam_Named<
		MODEL, 
		RESULT,
		RET extends ISharedResultMap_Initial_NoParam_Named<MODEL, RESULT, RET>>

	extends 
			ISharedResultMap_Initial_Named<MODEL, RESULT, RET>,
			
			ISharedSelectSourceBuilder<MODEL, RESULT> { // TODO: really select-source? needed for type-checking{

	ISharedMapFunctions_All_Named<
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
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigInteger, RET>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Float, 	   RET>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Double,     RET>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigDecimal, RET>,
		ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Date, 	   RET>,
		ISharedResultMap_OpsAndTo_String_Named<MODEL, RESULT, RET>,
		
		ISharedResultMap_To<MODEL, RESULT, Long, 		 RET>,
		ISharedResultMap_To<MODEL, RESULT, Long, 		 RET>,
		ISharedResultMap_To<MODEL, RESULT, Integer, 	 RET>,
		
		ISharedResultMap_To<MODEL, RESULT, Byte, 	 	RET>,
		ISharedResultMap_To<MODEL, RESULT, Short, 	 RET>,
		ISharedResultMap_To<MODEL, RESULT, Integer, 	 RET>,
		ISharedResultMap_To<MODEL, RESULT, Long, 		 RET>,
		ISharedResultMap_To<MODEL, RESULT, BigInteger, RET>,
		ISharedResultMap_To<MODEL, RESULT, Float, 	 RET>,
		ISharedResultMap_To<MODEL, RESULT, Double,     RET>,
		ISharedResultMap_To<MODEL, RESULT, BigDecimal, RET>,
		ISharedResultMap_To<MODEL, RESULT, Date, 	     RET>,
		ISharedResultMap_To<MODEL, RESULT, String, 	 RET>
	> map();
	
}
