package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

abstract class BasePreparedQueryImpl<RESULT_TYPE> implements ISharedPreparedQueryOps<RESULT_TYPE> {

	private final QueryDataSourceBase dataSource;
	private final DSPreparedQuery preparedQuery;

	BasePreparedQueryImpl(QueryDataSourceBase dataSource, DSPreparedQuery preparedQuery) {

		if (dataSource == null) {
			throw new IllegalArgumentException("dataSource == null");
		}

		if (preparedQuery == null) {
			throw new IllegalArgumentException("preparedQuery == null");
		}

		this.dataSource = dataSource;
		this.preparedQuery = preparedQuery;
	}

	@SuppressWarnings("unchecked")
	public final RESULT_TYPE execute() {
		// Execute directly
		return (RESULT_TYPE)preparedQuery.execute(null);
	}


	@Override
	public final <T> QueryParamsValueBuilder<T, QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE>> executeWith(Param<T> param) {

		final QueryParamCollector queryParamCollector = new QueryParamCollector();
		final QueryResultGetterParamsBuilderImpl<RESULT_TYPE> paramsBuilder

				= new QueryResultGetterParamsBuilderImpl<>(
						queryParamCollector,
						(QueryDataSourceBase)dataSource,
						preparedQuery,
						this); 

		return paramsBuilder.addParam(param);
	}

	@Override
	public final RESULT_TYPE execute(Function<QueryParamsInitialBuilder, QueryParamsEnd> builder) {
		throw new UnsupportedOperationException("TODO");
	}

	abstract RESULT_TYPE executeOn(DSPreparedQuery query, QueryParamCollector collectedParams);
	
}
