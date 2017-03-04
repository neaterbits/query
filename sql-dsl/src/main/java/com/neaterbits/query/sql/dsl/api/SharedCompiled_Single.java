package com.neaterbits.query.sql.dsl.api;


final class SharedCompiled_Single<RESULT>
		extends SharedCompiled_Base<RESULT, RESULT>
		implements SingleBuilt<RESULT> {

	SharedCompiled_Single(CompiledQuery compiledQuery) {
		super(compiledQuery);
	}

	static <R> Shared_PreparedQuery_Single<R> prepare(DataConfig dataConfig, CompiledQuery compiledQuery) {
		@SuppressWarnings("rawtypes")
		final QueryDataSource_Base<?> dataSourceBase = (QueryDataSource_Base<?>)
		
			((DataConfigBase)dataConfig).getDataSource();
		
		final PreparedQuery_DS<?> dsPrepared = dataSourceBase.prepareSingleQuery(CompiledQuery.q, compiledQuery);
		
		return new Shared_PreparedQuery_Single<R>(dataSourceBase, dsPrepared);
	}
	
	@Override
	public SinglePrepared<RESULT> prepare(DataConfig dataConfig) {
		return prepare(dataConfig, getCompiledQuery());
	}
}
