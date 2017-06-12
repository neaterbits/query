package com.neaterbits.query.sql.dsl.api;



public interface BuiltQueryOps<RESULT, RESULT_TYPE, PREPARED_QUERY_TYPE extends ISharedPreparedQuery_Ops<RESULT_TYPE>>
					extends Query<RESULT> {

	PREPARED_QUERY_TYPE prepare(DataConfig dataConfig);
	
}

