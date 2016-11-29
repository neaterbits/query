package com.neaterbits.query.sql.dsl.api;

/**
 * Query data source implementation for JPA
 * @author nhl
 *
 */

final class QueryDataSourceJPA extends QueryDataSourceBase {

	@Override
	DSPreparedQuery prepareSingleQuery(CompiledQuery query) {
		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}
		
		return null;
	}

	@Override
	DSPreparedQuery prepareMultiQuery(CompiledQuery query) {
		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}
		return null;
	}
}

