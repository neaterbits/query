package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface ISharedResult_Mapped_Named_Base<
		MODEL, 
		RESULT,
		RET extends ISharedResult_Mapped_Named_Base<MODEL, RESULT, RET>>

	extends 
			ISharedResultMapper_Named<MODEL, RESULT, RET>,
			
			ISharedSelectSourceBuilder<MODEL, RESULT> { // TODO: really select-source? needed for type-checking{

	ISharedMapFunctions_Named<
			MODEL,
			RESULT,
			
			RET,

			ISharedResultOps_Numeric_Named<MODEL, RESULT, Long, 	   RET>,
			ISharedResultOps_Numeric_Named<MODEL, RESULT, Long, 	   RET>,
		
			ISharedResultOps_Numeric_Named<MODEL, RESULT, Short, 	   RET>,
			ISharedResultOps_Numeric_Named<MODEL, RESULT, Integer,    RET>,
			ISharedResultOps_Numeric_Named<MODEL, RESULT, Long, 	   RET>,
			ISharedResultOps_Numeric_Named<MODEL, RESULT, Double,     RET>,
			ISharedResultOps_Numeric_Named<MODEL, RESULT, BigDecimal, RET>,
			ISharedResultOps_String_Named<MODEL, RESULT, RET>,
			
			ISharedResultMapperTo<MODEL, RESULT, Long, 		 RET>,
			ISharedResultMapperTo<MODEL, RESULT, Long, 		 RET>,
		
			ISharedResultMapperTo<MODEL, RESULT, Short, 	 RET>,
			ISharedResultMapperTo<MODEL, RESULT, Integer, 	 RET>,
			ISharedResultMapperTo<MODEL, RESULT, Long, 		 RET>,
			ISharedResultMapperTo<MODEL, RESULT, Double,     RET>,
			ISharedResultMapperTo<MODEL, RESULT, BigDecimal, RET>,
			ISharedResultMapperTo<MODEL, RESULT, String, 	 RET>
		> 

		map();
	
}
