package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class Shared_PreparedQuery_Multi<RESULT> extends Shared_PreparedQuery_Base<List<RESULT>>
	implements MultiPrepared<RESULT> {

	Shared_PreparedQuery_Multi(QueryDataSource_Base<?> dataSource, PreparedQuery_DS<?> preparedQuery) {
		super(dataSource, preparedQuery);
	}

	@Override
	List<RESULT> executeOn(PreparedQuery_DS<?> query, QueryParamCollector collectedParams) {
		throw new UnsupportedOperationException("TODO");
	}
}
