package com.neaterbits.query.sql.dsl.api;

public interface MultiMapToResult<MODEL, RESULT>
	extends MultiSelectSourceBuilder<MODEL, RESULT>, ResultMapperFrom<MODEL, RESULT, MultiMapToResult<MODEL, RESULT>> {

}
