package com.neaterbits.query.sql.dsl.api;


public interface SingleMapToResult<MODEL, RESULT>
	extends SingleSelectSourceBuilder<MODEL, RESULT>,
			ResultMapperFromAll<MODEL, RESULT, SingleMapToResultTable<MODEL, RESULT>, SingleMapToResultAlias<MODEL, RESULT>> {

}
