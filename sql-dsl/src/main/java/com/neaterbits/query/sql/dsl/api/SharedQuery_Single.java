package com.neaterbits.query.sql.dsl.api;


final class SharedQuery_Single<RESULT> extends SharedQuery_Base<RESULT, RESULT> implements SingleQuery<RESULT> {

	SharedQuery_Single(CompiledQuery compiledQuery) {
		super(compiledQuery);
	}

	@Override
	public ISharedSinglePreparedQuery<RESULT> prepare(QueryDataSource dataSource) {
		
		final QueryDataSource_Base<?> dataSourceBase = (QueryDataSource_Base<?>)dataSource;
		
		final PreparedQuery_DS<?> dsPrepared = dataSourceBase.prepareSingleQuery(q, getCompiledQuery());
		
		return new Builder_PreparedQuery_ClassicSingle<RESULT>(dataSourceBase, dsPrepared);
	}
}
