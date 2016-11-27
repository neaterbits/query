package com.neaterbits.query.sql.dsl.api;

import java.util.List;
import java.util.function.Function;

final class MultiQueryImpl<RESULT> extends BaseQueryImpl<List<RESULT>, RESULT> implements MultiQuery<RESULT> {

	MultiQueryImpl(CompiledQuery compiledQuery) {
		super(compiledQuery);
	}

	@Override
	public QueryResultGetterParamsInitialBuilder<List<RESULT>> executeOn(QueryDataSource dataSource) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public List<RESULT> execute(Function<QueryParamsInitialBuilder, QueryParamsEnd> builder) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	List<RESULT> executeOn(QueryDataSourceBase dataSource) {
		return dataSource.executeMultiQuery(getCompiledQuery());
	}
}
