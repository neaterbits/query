package com.neaterbits.query.sql.dsl.api;

abstract class DSPreparedQuery<DATASOURCE extends QueryDataSource> {

	private final DATASOURCE dataSource;

	DSPreparedQuery(DATASOURCE dataSource) {

		if (dataSource == null) {
			throw new IllegalArgumentException("dataSource == null");
		}

		this.dataSource = dataSource;
	}

	abstract Object execute(ParamValueResolver collectedParams);

	final DATASOURCE getDataSource() {
		return dataSource;
	}
}
