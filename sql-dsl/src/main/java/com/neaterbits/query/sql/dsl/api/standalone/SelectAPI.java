package com.neaterbits.query.sql.dsl.api.standalone;

public interface SelectAPI {

	<RESULT> SingleResultToResult<RESULT> selectSingle(Class<RESULT> cl);

    <TABLE> Alias<TABLE> alias(Class<TABLE> alias);
    
}
