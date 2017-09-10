package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public interface ISharedMap_Initial_NoParam_Named<
		MODEL, 
		RESULT,
		RET extends ISharedMap_Initial_NoParam_Named<MODEL, RESULT, RET>>

	extends 
			ISharedMap_Initial_Named<MODEL, RESULT, RET>,
			
			ISharedSelectSourceBuilder<MODEL, RESULT> { // TODO: really select-source? needed for type-checking{

	ISharedMap_Functions_All_Named<
		MODEL,
		RESULT,
		RET,
		
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, 	   RET>,
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, 	   RET>,
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer,    RET>,
		
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Byte, 	   RET>,
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Short, 	   RET>,
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer,    RET>,
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, 	   RET>,
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigInteger, RET>,
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Float, 	   RET>,
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Double,     RET>,
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigDecimal, RET>,
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Date, 	   RET>,
		ISharedMap_OpsAndTo_String_Named<MODEL, RESULT, RET>,
		
		ISharedMap_To<MODEL, RESULT, Long, 		 RET>,
		ISharedMap_To<MODEL, RESULT, Long, 		 RET>,
		ISharedMap_To<MODEL, RESULT, Integer, 	 RET>,
		
		ISharedMap_To<MODEL, RESULT, Byte, 	 	RET>,
		ISharedMap_To<MODEL, RESULT, Short, 	 RET>,
		ISharedMap_To<MODEL, RESULT, Integer, 	 RET>,
		ISharedMap_To<MODEL, RESULT, Long, 		 RET>,
		ISharedMap_To<MODEL, RESULT, BigInteger, RET>,
		ISharedMap_To<MODEL, RESULT, Float, 	 RET>,
		ISharedMap_To<MODEL, RESULT, Double,     RET>,
		ISharedMap_To<MODEL, RESULT, BigDecimal, RET>,
		ISharedMap_To<MODEL, RESULT, Date, 	     RET>,
		ISharedMap_To<MODEL, RESULT, String, 	 RET>
	> map();
	
}
