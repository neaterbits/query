package com.neaterbits.gui.sql.dsl.api.standalone;

public interface SingleResultToResult<RESULT>
	extends SingleSelectSourceBuilder<RESULT>, ResultMapperFrom<RESULT, SingleResultToResult<RESULT>>{

}
