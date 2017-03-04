package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class SharedCompiled_Multi<RESULT> extends SharedQuery_Base<List<RESULT>, RESULT>
			implements MultiCompiled<RESULT> {
	
	SharedCompiled_Multi(CompiledQuery compiledQuery) {
		super(compiledQuery);
	}

	@Override
	public MultiPrepared<RESULT> prepare(DataConfig dataConfig) {

		@SuppressWarnings("rawtypes")
		final QueryDataSource_Base<?> dataSourceBase = (QueryDataSource_Base<?>)
				
					((DataConfigBase)dataConfig).getDataSource();
		
		final PreparedQuery_DS<?> dsPrepared = dataSourceBase.prepareMultiQuery(q, getCompiledQuery());
		
		return new Shared_PreparedQuery_Multi<>(dataSourceBase, dsPrepared);
	}
}
