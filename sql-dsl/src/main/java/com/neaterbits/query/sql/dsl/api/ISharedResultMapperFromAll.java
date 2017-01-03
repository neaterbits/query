package com.neaterbits.query.sql.dsl.api;

public interface ISharedResultMapperFromAll<MODEL, RESULT, 
		TABLE_SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		ALIAS_SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>> 

	extends ISharedResultMapperFromNamed<MODEL, RESULT, TABLE_SOURCE>,
			ISharedResultMapperFromAlias<MODEL, RESULT, ALIAS_SOURCE> {

	
}
