package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class SingleQueryImpl<RESULT> extends BaseQueryImpl<RESULT, RESULT> implements SingleQuery<RESULT> {

	SingleQueryImpl(CompiledQuery compiledQuery) {
		super(compiledQuery);
	}

	@Override
	public QueryResultGetterParamsInitialBuilder<RESULT> executeOn(QueryDataSource dataSource) {

		final QueryParamCollector queryParamCollector = new QueryParamCollector();
		final QueryResultGetterParamsBuilderImpl<RESULT> paramsBuilder

				= new QueryResultGetterParamsBuilderImpl<>(
						queryParamCollector,
						(QueryDataSourceBase)dataSource,
						this); 

		return paramsBuilder;
	}

	@Override
	public RESULT execute(Function<QueryParamsInitialBuilder, QueryParamsEnd> builder) {
		throw new UnsupportedOperationException("TODO");
	}

	
	@Override
	RESULT executeOn(QueryDataSourceBase dataSource) {
		return dataSource.executeSingleQuery(getCompiledQuery());
	}
}
