package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class MultiPreparedQueryImpl<RESULT> extends BasePreparedQueryImpl<List<RESULT>>
	implements MultiPreparedQuery<RESULT> {

	MultiPreparedQueryImpl(QueryDataSourceBase dataSource, DSPreparedQuery preparedQuery) {
		super(dataSource, preparedQuery);
	}

	@Override
	List<RESULT> executeOn(DSPreparedQuery query, QueryParamCollector collectedParams) {
		throw new UnsupportedOperationException("TODO");
	}
}
