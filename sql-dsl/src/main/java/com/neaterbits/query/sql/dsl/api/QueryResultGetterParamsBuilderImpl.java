package com.neaterbits.query.sql.dsl.api;

final class QueryResultGetterParamsBuilderImpl<RESULT_TYPE>

		extends QueryResultGetterImpl<RESULT_TYPE>

		implements QueryResultGetterParamsInitialBuilder   <RESULT_TYPE>,
				   QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE>,
				   QueryParamsValueBuilder<Object, QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE>>,
				   QueryResultGetter<RESULT_TYPE> {

							   
	private final QueryParamCollector queryParamCollector;
	private final QueryDataSourceBase dataSource;
	private final BaseQueryImpl<RESULT_TYPE, ?> query; 
	private Param<?> lastParam;

	QueryResultGetterParamsBuilderImpl(
			QueryParamCollector queryParamCollector,
			QueryDataSourceBase dataSource,
			BaseQueryImpl<RESULT_TYPE, ?> query) {

		if (queryParamCollector == null) {
			throw new IllegalArgumentException("queryParamCollector == null");
		}
		
		if (dataSource == null) {
			throw new IllegalArgumentException("dataSource == null");
		}

		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}

		this.queryParamCollector = queryParamCollector;
		this.dataSource = dataSource;
		this.query = query;
	}

	@Override
	public <T> QueryParamsValueBuilder<T, QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE>> with(Param<T> param) {
		return getParamsValueBuilder(param);
	}

	@Override
	public QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE> setTo(Object value) {

		queryParamCollector.add(lastParam, value);

		this.lastParam = null;

		return this;
	}

	@Override
	public <T> QueryParamsValueBuilder<T, QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE>> and(Param<T> param) {
		return getParamsValueBuilder(param);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private <T> QueryParamsValueBuilder<T, QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE>> getParamsValueBuilder(Param<?> param) {
		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}

		if (this.lastParam != null) {
			throw new IllegalStateException("lastParam already set");
		}
		
		this.lastParam = param;

		return (QueryParamsValueBuilder)this;
	}

	@Override
	public final RESULT_TYPE get() {
		return query.executeOn(dataSource);
	}
}
