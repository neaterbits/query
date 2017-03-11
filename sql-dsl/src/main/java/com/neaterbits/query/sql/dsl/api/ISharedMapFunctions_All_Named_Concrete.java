package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

@Deprecated
public interface ISharedMapFunctions_All_Named_Concrete<MODEL, RESULT, RET extends ISharedFunction_After<MODEL, RESULT>>
	extends ISharedMapFunctions_All_Named<
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
		ISharedResultOps_String_Named<MODEL, RESULT, RET>,
		
		ISharedResultMapperTo<MODEL, RESULT, Long, 		 RET>,
		ISharedResultMapperTo<MODEL, RESULT, Long, 		 RET>,
	
		ISharedResultMapperTo<MODEL, RESULT, Short, 	 RET>,
		ISharedResultMapperTo<MODEL, RESULT, Integer, 	 RET>,
		ISharedResultMapperTo<MODEL, RESULT, Long, 		 RET>,
		ISharedResultMapperTo<MODEL, RESULT, Double,     RET>,
		ISharedResultMapperTo<MODEL, RESULT, BigDecimal, RET>,
		ISharedResultMapperTo<MODEL, RESULT, String, 	 RET>> {

}
