package com.neaterbits.query.sql.dsl.api;

public interface ISharedResultMapper_All<MODEL, RESULT, 
		TABLE_SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		ALIAS_SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>> 

	extends ISharedResultMapper_Named<MODEL, RESULT, TABLE_SOURCE>,
			ISharedResultMapper_Alias<MODEL, RESULT, ALIAS_SOURCE> {

	
}
