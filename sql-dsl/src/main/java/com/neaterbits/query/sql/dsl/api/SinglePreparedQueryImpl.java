package com.neaterbits.query.sql.dsl.api;

final class SinglePreparedQueryImpl<RESULT> extends BasePreparedQueryImpl<RESULT>
	implements SinglePreparedQuery<RESULT> {

	SinglePreparedQueryImpl(QueryDataSourceBase dataSource, DSPreparedQuery preparedQuery) {
		super(dataSource, preparedQuery);
	}

	@Override
	RESULT executeOn(DSPreparedQuery query, QueryParamCollector collectedParams) {
		throw new UnsupportedOperationException("TODO");
	}
}
