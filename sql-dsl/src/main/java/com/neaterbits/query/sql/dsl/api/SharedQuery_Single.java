package com.neaterbits.query.sql.dsl.api;


final class SharedQuery_Single<RESULT> extends SharedQuery_Base<RESULT, RESULT> implements SingleQuery<RESULT> {

	SharedQuery_Single(CompiledQuery compiledQuery) {
		super(compiledQuery);
	}

	@Override
	public ISharedSinglePreparedQuery<RESULT> prepare(QueryDataSource dataSource) {
		
		final QueryDataSource_Base<?> dataSourceBase = (QueryDataSource_Base<?>)dataSource;
		
		final DSPreparedQuery<?> dsPrepared = dataSourceBase.prepareSingleQuery(q, getCompiledQuery());
		
		return new ClassicSinglePreparedQueryImpl<RESULT>(dataSourceBase, dsPrepared);
	}
}
