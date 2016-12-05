package com.neaterbits.query.sql.dsl.api;

final class SinglePreparedQueryImpl<RESULT> extends BasePreparedQueryImpl<RESULT>
	implements SinglePreparedQuery<RESULT> {

	SinglePreparedQueryImpl(QueryDataSourceBase dataSource, DSPreparedQuery preparedQuery) {
		super(dataSource, preparedQuery);
	}

	@Override
	@SuppressWarnings("unchecked")
	RESULT executeOn(DSPreparedQuery query, QueryParamCollector collectedParams) {
		return (RESULT)query.execute(collectedParams);
	}
}
