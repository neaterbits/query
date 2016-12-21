package com.neaterbits.query.sql.dsl.api;

public interface IClassicMultiMapToResultAlias<MODEL, RESULT>
	extends IClassicMultiSelectSourceBuilder<MODEL, RESULT>,
	
			IClassicSelectSourceBuilderAlias<MODEL, RESULT>,
	
		    ISharedResultMapperFromAlias<MODEL, RESULT, IClassicMultiMapToResultAlias<MODEL, RESULT>> {

}
