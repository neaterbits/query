package com.neaterbits.query.sql.dsl.api;

public interface MultiMapToResult<MODEL, RESULT>
	extends 
		MultiSelectSourceBuilder<MODEL, RESULT>,
		ResultMapperFromAll<MODEL, RESULT, MultiMapToResultTable<MODEL, RESULT>, MultiMapToResultAlias<MODEL, RESULT>> {

}
