package com.neaterbits.query.sql.dsl.api;


public interface SingleMapToResult<MODEL, RESULT>
	extends SingleSelectSourceBuilder<MODEL, RESULT>, ResultMapperFrom<MODEL, RESULT, SingleMapToResult<MODEL, RESULT>> {

}
