package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.Date;

public interface ISharedResult_Mapped_Named_Base<
		MODEL, 
		RESULT,
		RET extends ISharedResult_Mapped_Named_Base<MODEL, RESULT, RET>>

	extends 
			ISharedResultMapper_Named<MODEL, RESULT, RET>,
			
			ISharedSelectSourceBuilder<MODEL, RESULT> { // TODO: really select-source? needed for type-checking{

	ISharedMapFunctions_All_Named<
		MODEL,
		RESULT,
		RET,
		
		ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Long, 	   RET>,
		ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Long, 	   RET>,
	
		ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Short, 	   RET>,
		ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Integer,    RET>,
		ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Long, 	   RET>,
		ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Double,     RET>,
		ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, BigDecimal, RET>,
		ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Date, 	   RET>,
		ISharedResultOps_String_Named<MODEL, RESULT, RET>,
		
		ISharedResultMapperTo<MODEL, RESULT, Long, 		 RET>,
		ISharedResultMapperTo<MODEL, RESULT, Long, 		 RET>,
	
		ISharedResultMapperTo<MODEL, RESULT, Short, 	 RET>,
		ISharedResultMapperTo<MODEL, RESULT, Integer, 	 RET>,
		ISharedResultMapperTo<MODEL, RESULT, Long, 		 RET>,
		ISharedResultMapperTo<MODEL, RESULT, Double,     RET>,
		ISharedResultMapperTo<MODEL, RESULT, BigDecimal, RET>,
		ISharedResultMapperTo<MODEL, RESULT, Date, 	     RET>,
		ISharedResultMapperTo<MODEL, RESULT, String, 	 RET>
	> map();
	
}
