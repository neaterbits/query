package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class MultiQueryImpl<RESULT> extends BaseQueryImpl<List<RESULT>, RESULT>
			implements MultiQuery<RESULT> {

	MultiQueryImpl(CompiledQuery compiledQuery) {
		super(compiledQuery);
	}

	@Override
	public MultiPreparedQuery<RESULT> prepare(QueryDataSource dataSource) {

		final QueryDataSourceBase dataSourceBase = (QueryDataSourceBase)dataSource;
		
		final DSPreparedQuery dsPrepared = dataSourceBase.prepareMultiQuery(getCompiledQuery());
		
		return new MultiPreparedQueryImpl<>(dataSourceBase, dsPrepared);
	}
}
