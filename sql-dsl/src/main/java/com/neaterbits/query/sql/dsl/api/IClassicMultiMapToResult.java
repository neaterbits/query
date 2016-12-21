package com.neaterbits.query.sql.dsl.api;

public interface IClassicMultiMapToResult<MODEL, RESULT>
	extends 
		IClassicMultiSelectSourceBuilder<MODEL, RESULT>,
		ISharedResultMapperFromAll<MODEL, RESULT, IClassicMultiMapToResultTable<MODEL, RESULT>, IClassicMultiMapToResultAlias<MODEL, RESULT>> {

}