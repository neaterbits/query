package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.Arrays;

import com.neaterbits.query.util.java8.Coll8;

final class QueryResultGetterParamsBuilderImpl<RESULT_TYPE>

		extends QueryResultGetterImpl<RESULT_TYPE>

		implements QueryResultGetterParamsInitialBuilder   <RESULT_TYPE>,
				   QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE>,
				   QueryParamsValueBuilder<Object, QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE>>,
				   QueryParamsValueBuilderForCollection<Object, QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE>>,
				   QueryResultGetter<RESULT_TYPE> {

							   
	private final QueryParamCollector queryParamCollector;
	private final QueryDataSource_Base<?> dataSource;
	private final PreparedQuery_DS<?> dsQuery;
	private final Shared_PreparedQuery_Base<RESULT_TYPE> query; 
	private Param<?> lastParam;

	QueryResultGetterParamsBuilderImpl(
			QueryParamCollector queryParamCollector,
			QueryDataSource_Base<?> dataSource,
			PreparedQuery_DS<?> dsQuery,
			Shared_PreparedQuery_Base<RESULT_TYPE> query) {

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

	private QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE> intSetTo(Object value) {

		queryParamCollector.add(lastParam, value);

		this.lastParam = null;

		return this;
	}

	@Override
	public QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE> setTo(Object value) {
		return intSetTo(value);
	}

	@Override
	public QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE> setTo(Object... values) {
		return intSetTo(Arrays.asList(values));
	}

	@Override
	public QueryResultGetterParamsAdditionalBuilder<RESULT_TYPE> setTo(Iterable<Object> values) {
		return intSetTo(Coll8.fromIterable(new ArrayList<>(), values));
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
