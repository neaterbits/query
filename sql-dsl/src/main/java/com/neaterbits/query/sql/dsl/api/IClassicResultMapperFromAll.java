package com.neaterbits.query.sql.dsl.api;

public interface IClassicResultMapperFromAll<MODEL, RESULT, 
		TABLE_SOURCE extends SelectSourceBuilder<MODEL, RESULT>,
		ALIAS_SOURCE extends SelectSourceBuilder<MODEL, RESULT>> 

	extends ISharedResultMapperFromTable<MODEL, RESULT, TABLE_SOURCE>,
			ISharedResultMapperFromAlias<MODEL, RESULT, ALIAS_SOURCE> {

	
}
