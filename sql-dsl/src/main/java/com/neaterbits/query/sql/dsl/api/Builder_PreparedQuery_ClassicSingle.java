package com.neaterbits.query.sql.dsl.api;

final class Builder_PreparedQuery_ClassicSingle<RESULT> extends Builder_PreparedQuery_Base<RESULT>
	implements ISharedSinglePreparedQuery<RESULT> {

	Builder_PreparedQuery_ClassicSingle(QueryDataSource_Base<?> dataSource, PreparedQuery_DS<?> preparedQuery) {
		super(dataSource, preparedQuery);
	}

	@Override
	@SuppressWarnings("unchecked")
	RESULT executeOn(PreparedQuery_DS query, QueryParamCollector collectedParams) {
		return (RESULT)query.execute(collectedParams);
	}
}
