package com.neaterbits.query.sql.dsl.api;

abstract class QueryDataSourceBase<DATASOURCE extends QueryDataSourceBase<DATASOURCE>> implements QueryDataSource {

	abstract <QUERY> DSPreparedQuery<DATASOURCE> prepareSingleQuery(ExecutableQuery<QUERY> q, QUERY query);

	abstract <QUERY> DSPreparedQuery<DATASOURCE> prepareMultiQuery(ExecutableQuery<QUERY> q, QUERY query);
}
