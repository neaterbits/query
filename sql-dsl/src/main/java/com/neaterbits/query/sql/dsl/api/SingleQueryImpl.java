package com.neaterbits.query.sql.dsl.api;


final class SingleQueryImpl<RESULT> extends BaseQueryImpl<RESULT, RESULT> implements SingleQuery<RESULT> {

	SingleQueryImpl(CompiledQuery compiledQuery) {
		super(compiledQuery);
	}

	@Override
	public SinglePreparedQuery<RESULT> prepare(QueryDataSource dataSource) {
		
		final QueryDataSourceBase dataSourceBase = (QueryDataSourceBase)dataSource;
		
		final DSPreparedQuery dsPrepared = dataSourceBase.prepareSingleQuery(getCompiledQuery());
		
		return new SinglePreparedQueryImpl<RESULT>(dataSourceBase, dsPrepared);
	}
}
