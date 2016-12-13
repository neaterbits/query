package com.neaterbits.query.sql.dsl.api;

public interface MultiMapToResultAlias<MODEL, RESULT>
	extends MultiSelectSourceBuilder<MODEL, RESULT>,
	
			SelectSourceBuilderAlias<MODEL, RESULT>,
	
		    ResultMapperFromAlias<MODEL, RESULT, MultiMapToResultAlias<MODEL, RESULT>> {

}
