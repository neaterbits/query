package com.neaterbits.query.sql.dsl.api;


public interface SingleMapToResult<RESULT>
	extends SingleSelectSourceBuilder<RESULT>, ResultMapperFrom<RESULT, SingleMapToResult<RESULT>> {

}
