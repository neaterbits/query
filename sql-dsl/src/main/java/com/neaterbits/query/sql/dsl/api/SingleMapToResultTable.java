package com.neaterbits.query.sql.dsl.api;

public interface SingleMapToResultTable<MODEL, RESULT>
	extends SingleSelectSourceBuilder<MODEL, RESULT>, SelectSourceBuilderTable<MODEL, RESULT>, 
			ResultMapperFromTable<MODEL, RESULT, SingleMapToResultTable<MODEL, RESULT>> {

}
