package com.neaterbits.query.sql.dsl.api;

public interface ResultMapperFromAll<MODEL, RESULT, 
		TABLE_SOURCE extends SelectSourceBuilder<MODEL, RESULT>,
		ALIAS_SOURCE extends SelectSourceBuilder<MODEL, RESULT>> 

	extends ResultMapperFromTable<MODEL, RESULT, TABLE_SOURCE>,
			ResultMapperFromAlias<MODEL, RESULT, ALIAS_SOURCE> {

	
}
