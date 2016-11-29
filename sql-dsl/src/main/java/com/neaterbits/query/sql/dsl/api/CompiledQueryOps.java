package com.neaterbits.query.sql.dsl.api;



public interface CompiledQueryOps<RESULT, RESULT_TYPE, PREPARED_QUERY_TYPE extends PreparedQueryOps<RESULT_TYPE>>
					extends Query<RESULT> {

	PREPARED_QUERY_TYPE prepare(QueryDataSource dataSource);
	
}

