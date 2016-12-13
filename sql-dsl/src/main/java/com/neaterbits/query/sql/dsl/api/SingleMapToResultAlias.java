package com.neaterbits.query.sql.dsl.api;

public interface SingleMapToResultAlias<MODEL, RESULT>
	extends SingleSelectSourceBuilder<MODEL, RESULT>,
		    SelectSourceBuilderAlias<MODEL, RESULT>,
			ResultMapperFromAlias<MODEL, RESULT, SingleMapToResultAlias<MODEL, RESULT>> {

}
