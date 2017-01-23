package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class SharedQuery_Multi<RESULT> extends SharedQuery_Base<List<RESULT>, RESULT>
			implements MultiQuery<RESULT> {
	
	SharedQuery_Multi(CompiledQuery compiledQuery) {
		super(compiledQuery);
	}

	@Override
	public ISharedMultiPreparedQuery<RESULT> prepare(QueryDataSource dataSource) {

		final QueryDataSource_Base<?> dataSourceBase = (QueryDataSource_Base<?>)dataSource;
		
		final PreparedQuery_DS<?> dsPrepared = dataSourceBase.prepareMultiQuery(q, getCompiledQuery());
		
		return new Builder_PreparedQuery_Multi<>(dataSourceBase, dsPrepared);
	}
}
