package com.neaterbits.query.sql.dsl.api;

final class QueryResultGetterParamsBuilderImpl<RESULT_TYPE>

		extends QueryResultGetterImpl<RESULT_TYPE>

		implements QueryResultGetterParamsInitialBuilder   <RESULT_TYPE>,
				   QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE>,
				   QueryParamsValueBuilder<Object, QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE>>,
				   QueryResultGetter<RESULT_TYPE> {

							   
	private final QueryParamCollector queryParamCollector;
	private final QueryDataSource_Base dataSource;
	private final DSPreparedQuery dsQuery;
	private final BasePreparedQueryImpl<RESULT_TYPE> query; 
	private Param<?> lastParam;

	QueryResultGetterParamsBuilderImpl(
			QueryParamCollector queryParamCollector,
			QueryDataSource_Base dataSource,
			DSPreparedQuery dsQuery,
			BasePreparedQueryImpl<RESULT_TYPE> query) {

		if (queryParamCollector == null) {
			throw new IllegalArgumentException("queryParamCollector == null");
		}
		
		if (dataSource == null) {
			throw new IllegalArgumentException("dataSource == null");
		}
		
		if (dsQuery == null) {
			throw new IllegalArgumentException("dsQuery == null");
		}

		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}

		this.queryParamCollector = queryParamCollector;
		this.dataSource = dataSource;
		this.dsQuery = dsQuery;
		this.query = query;
	}

	@Override
	public <T> QueryParamsValueBuilder<T, QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE>> with(Param<T> param) {
		return addParam(param);
	}

	@Override
	public QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE> setTo(Object value) {

		queryParamCollector.add(lastParam, value);

		this.lastParam = null;

		return this;
	}

	@Override
	public <T> QueryParamsValueBuilder<T, QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE>> and(Param<T> param) {
		return addParam(param);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	<T> QueryParamsValueBuilder<T, QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE>> addParam(Param<?> param) {
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
		return query.executeOn(dsQuery, queryParamCollector);
	}
}
