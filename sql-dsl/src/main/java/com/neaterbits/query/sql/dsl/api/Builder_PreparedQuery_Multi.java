package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class Builder_PreparedQuery_Multi<RESULT> extends Builder_PreparedQuery_Base<List<RESULT>>
	implements ISharedMultiPreparedQuery<RESULT> {

	Builder_PreparedQuery_Multi(QueryDataSource_Base<?> dataSource, PreparedQuery_DS<?> preparedQuery) {
		super(dataSource, preparedQuery);
	}

	@Override
	List<RESULT> executeOn(PreparedQuery_DS query, QueryParamCollector collectedParams) {
		throw new UnsupportedOperationException("TODO");
	}
}
