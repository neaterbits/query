package com.neaterbits.query.sql.dsl.api;

public interface MultiMapToResult<RESULT>
	extends MultiSelectSourceBuilder<RESULT>, ResultMapperFrom<RESULT, MultiMapToResult<RESULT>> {

}
