package com.neaterbits.query.sql.dsl.api;


public interface IClassicSingleMapToResult<MODEL, RESULT>
	extends SingleSelectSourceBuilder<MODEL, RESULT>,
			IClassicResultMapperFromAll<MODEL, RESULT, IClassicSingleMapToResultTable<MODEL, RESULT>, IClassicSingleMapToResultAlias<MODEL, RESULT>> {

}
