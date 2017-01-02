package com.neaterbits.query.sql.dsl.api;

import java.util.List;

abstract class QueryDataSourceDB extends QueryDataSourceBase<QueryDataSourceDB> {

	abstract <QUERY> List<Object> mapMultipleEntitities(ExecutableQuery<QUERY> q, QUERY query, List<?> input);

	abstract <QUERY> Object mapSingleEntity(ExecutableQuery<QUERY> q, QUERY query, Object input);
	
}
