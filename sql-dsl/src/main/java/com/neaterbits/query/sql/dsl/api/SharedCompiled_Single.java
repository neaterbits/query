package com.neaterbits.query.sql.dsl.api;


final class SharedCompiled_Single<RESULT>
		extends SharedQuery_Base<RESULT, RESULT>
		implements SingleCompiled<RESULT> {

	SharedCompiled_Single(CompiledQuery compiledQuery) {
		super(compiledQuery);
	}

	@Override
	public SinglePrepared<RESULT> prepare(QueryDataSource dataSource) {
		
		final QueryDataSource_Base<?> dataSourceBase = (QueryDataSource_Base<?>)dataSource;
		
		final PreparedQuery_DS<?> dsPrepared = dataSourceBase.prepareSingleQuery(q, getCompiledQuery());
		
		return new Shared_PreparedQuery_Single<RESULT>(dataSourceBase, dsPrepared);
	}
}
