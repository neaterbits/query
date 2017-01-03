package com.neaterbits.query.sql.dsl.api;


public interface IClassicSingleMapToResult<MODEL, RESULT>
	extends SingleSelectSourceBuilder<MODEL, RESULT>,
			ISharedResultMapperFromAll<MODEL, RESULT, IClassicSingleMapToResultNamed<MODEL, RESULT>, IClassicSingleMapToResultAlias<MODEL, RESULT>> {

}
