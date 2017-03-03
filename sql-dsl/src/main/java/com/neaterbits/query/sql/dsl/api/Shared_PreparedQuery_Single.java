package com.neaterbits.query.sql.dsl.api;

final class Shared_PreparedQuery_Single<RESULT> extends Shared_PreparedQuery_Base<RESULT>
	implements SinglePrepared<RESULT> {

	Shared_PreparedQuery_Single(QueryDataSource_Base<?> dataSource, PreparedQuery_DS<?> preparedQuery) {
		super(dataSource, preparedQuery);
	}

	@Override
	@SuppressWarnings("unchecked")
	RESULT executeOn(PreparedQuery_DS<?> query, QueryParamCollector collectedParams) {
		return (RESULT)query.execute(collectedParams);
	}
}
