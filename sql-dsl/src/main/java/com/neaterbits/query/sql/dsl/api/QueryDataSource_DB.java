package com.neaterbits.query.sql.dsl.api;

import java.util.List;

abstract class QueryDataSource_DB extends QueryDataSource_Base<QueryDataSource_DB> {

	abstract <QUERY> List<Object> mapMultipleEntitities(ExecutableQuery<QUERY> q, QUERY query, List<?> input);

	abstract <QUERY> Object mapSingleEntity(ExecutableQuery<QUERY> q, QUERY query, Object input);
	
}
