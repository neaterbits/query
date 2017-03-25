package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.Date;

public interface ISharedResultMapper_All<MODEL, RESULT, 
		NAMED_SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		ALIAS_SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>> 

	extends ISharedResultMapper_Named<MODEL, RESULT, NAMED_SOURCE>,
			ISharedResultMapper_Alias<MODEL, RESULT, ALIAS_SOURCE>
	
	
		// Must map to all functions as well, arithmetic, string and aggregates
		// issue at this point is we do not know whether mapped or alias
	    // and map() would give signature collision if adding both for named and alias so must add both
	
		{

			
			
	// All functions - aggregate, string, datetime and aggregated
	ISharedMapFunctions_Initial<
				MODEL,
				RESULT,

				NAMED_SOURCE,
				ALIAS_SOURCE,
				
				// Named functions
				
				ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Long, NAMED_SOURCE>,
				ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Long, NAMED_SOURCE>,
				ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Short, NAMED_SOURCE>,
				ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Integer, NAMED_SOURCE>,
				ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Long, NAMED_SOURCE>,
				ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Double, NAMED_SOURCE>,
				ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, BigDecimal, NAMED_SOURCE>,
				ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Date, NAMED_SOURCE>,
				ISharedResultOps_String_Named<MODEL, RESULT, NAMED_SOURCE>,
				
				ISharedResultMapperTo<MODEL, RESULT, Long, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Long, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Short, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Integer, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Long, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Double, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, BigDecimal, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Date, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, String,  NAMED_SOURCE>,
				
				// Alias functions
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Long, ALIAS_SOURCE>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Long, ALIAS_SOURCE>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Short, ALIAS_SOURCE>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Integer, ALIAS_SOURCE>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Long, ALIAS_SOURCE>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Double, ALIAS_SOURCE>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, BigDecimal, ALIAS_SOURCE>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Date, ALIAS_SOURCE>,
				ISharedResultOps_String_Alias<MODEL, RESULT, ALIAS_SOURCE>,
			
				ISharedResultMapperTo<MODEL, RESULT, Long, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Long, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Short, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Integer, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Long, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Double, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, BigDecimal, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Date, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, String,  ALIAS_SOURCE>
	>
				
				map();
	
}
