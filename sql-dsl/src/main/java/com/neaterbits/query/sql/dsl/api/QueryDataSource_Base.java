package com.neaterbits.query.sql.dsl.api;

abstract class QueryDataSource_Base<DATASOURCE extends QueryDataSource_Base<DATASOURCE>> implements QueryDataSource {

	abstract <QUERY> PreparedQuery_DS<DATASOURCE> prepareSingleQuery(ExecutableQuery<QUERY> q, QUERY query);

	abstract <QUERY> PreparedQuery_DS<DATASOURCE> prepareMultiQuery(ExecutableQuery<QUERY> q, QUERY query);
}
