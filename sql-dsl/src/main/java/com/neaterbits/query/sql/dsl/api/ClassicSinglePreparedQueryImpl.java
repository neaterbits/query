package com.neaterbits.query.sql.dsl.api;

final class ClassicSinglePreparedQueryImpl<RESULT> extends BasePreparedQueryImpl<RESULT>
	implements ISharedSinglePreparedQuery<RESULT> {

	ClassicSinglePreparedQueryImpl(QueryDataSource_Base dataSource, DSPreparedQuery preparedQuery) {
		super(dataSource, preparedQuery);
	}

	@Override
	@SuppressWarnings("unchecked")
	RESULT executeOn(DSPreparedQuery query, QueryParamCollector collectedParams) {
		return (RESULT)query.execute(collectedParams);
	}
}
