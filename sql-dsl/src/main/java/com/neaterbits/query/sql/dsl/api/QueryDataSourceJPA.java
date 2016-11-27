package com.neaterbits.query.sql.dsl.api;

import java.util.List;

/**
 * Query data source implementation for JPA
 * @author nhl
 *
 */

final class QueryDataSourceJPA extends QueryDataSourceBase {

	@Override
	<RESULT_TYPE> RESULT_TYPE executeSingleQuery(CompiledQuery query) {
		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}
		
		return null;
	}

	@Override
	<RESULT_TYPE> List<RESULT_TYPE> executeMultiQuery(CompiledQuery query) {
		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}
		return null;
	}
}

