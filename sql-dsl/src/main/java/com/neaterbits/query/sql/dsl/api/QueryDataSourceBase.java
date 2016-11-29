package com.neaterbits.query.sql.dsl.api;

abstract class QueryDataSourceBase implements QueryDataSource {

	abstract DSPreparedQuery prepareSingleQuery(CompiledQuery compiled);

	abstract DSPreparedQuery prepareMultiQuery(CompiledQuery compiled);
}
