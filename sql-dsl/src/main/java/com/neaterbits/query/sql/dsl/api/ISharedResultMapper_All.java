package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

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
				ISharedResultMapperTo<MODEL, RESULT, Long, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Long, NAMED_SOURCE>,

				ISharedResultMapperTo<MODEL, RESULT, Short, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Integer, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Long, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Double, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, BigDecimal, NAMED_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, String,  NAMED_SOURCE>,
				
				// Alias functions
				ISharedResultMapperTo<MODEL, RESULT, Long, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Long, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Short, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Integer, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Long, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, Double, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, BigDecimal, ALIAS_SOURCE>,
				ISharedResultMapperTo<MODEL, RESULT, String,  ALIAS_SOURCE>
	>
				
				map();
	
}
