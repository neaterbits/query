package com.neaterbits.query.sql.dsl.api;

public interface IClassicSingleMapToResultAlias<MODEL, RESULT>
	extends ISharedSingleSelectSourceBuilder<MODEL, RESULT>,
		    IClassicSelectSourceBuilderAlias<MODEL, RESULT>,
			ISharedResultMapperFromAlias<MODEL, RESULT, IClassicSingleMapToResultAlias<MODEL, RESULT>> {

}
