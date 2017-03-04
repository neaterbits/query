package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class SharedCompiled_Multi<RESULT> extends SharedCompiled_Base<List<RESULT>, RESULT>
			implements MultiCompiled<RESULT> {
	
	SharedCompiled_Multi(CompiledQuery compiledQuery) {
		super(compiledQuery);
	}

	
	static <R> Shared_PreparedQuery_Multi<R> prepare(DataConfig dataConfig, CompiledQuery compiledQuery) {
		
		@SuppressWarnings("rawtypes")
		final QueryDataSource_Base<?> dataSourceBase = (QueryDataSource_Base<?>)
				
					((DataConfigBase)dataConfig).getDataSource();
		
		final PreparedQuery_DS<?> dsPrepared = dataSourceBase.prepareMultiQuery(CompiledQuery.q, compiledQuery);
		
		return new Shared_PreparedQuery_Multi<>(dataSourceBase, dsPrepared);
	}

	@Override
	public MultiPrepared<RESULT> prepare(DataConfig dataConfig) {
		return prepare(dataConfig, getCompiledQuery());
	}
}
