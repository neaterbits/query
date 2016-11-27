package com.neaterbits.query.sql.dsl.api;

import java.util.List;

abstract class QueryDataSourceBase implements QueryDataSource {

	
	abstract <RESULT_TYPE> RESULT_TYPE executeSingleQuery(CompiledQuery query);
	
	abstract <RESULT_TYPE> List<RESULT_TYPE> executeMultiQuery(CompiledQuery query);
	
}
