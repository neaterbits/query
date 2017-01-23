package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

abstract class Builder_PreparedQuery_Base<RESULT_TYPE> implements ISharedPreparedQueryOps<RESULT_TYPE> {

	private final QueryDataSource_Base<?> dataSource;
	private final PreparedQuery_DS<?> preparedQuery;

	Builder_PreparedQuery_Base(QueryDataSource_Base<?> dataSource, PreparedQuery_DS<?> preparedQuery) {

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
						(QueryDataSource_Base<?>)dataSource,
						preparedQuery,
						this); 

		return paramsBuilder.addParam(param);
	}

	@Override
	public final RESULT_TYPE execute(Function<QueryParamsInitialBuilder, QueryParamsEnd> builder) {
		throw new UnsupportedOperationException("TODO");
	}

	abstract RESULT_TYPE executeOn(PreparedQuery_DS query, QueryParamCollector collectedParams);
	
}
