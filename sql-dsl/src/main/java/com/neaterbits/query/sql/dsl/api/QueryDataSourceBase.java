package com.neaterbits.query.sql.dsl.api;

abstract class QueryDataSourceBase implements QueryDataSource {

	abstract <QUERY> DSPreparedQuery prepareSingleQuery(ExecutableQuery<QUERY> q, QUERY query);

	abstract <QUERY> DSPreparedQuery prepareMultiQuery(ExecutableQuery<QUERY> q, QUERY query);
}
