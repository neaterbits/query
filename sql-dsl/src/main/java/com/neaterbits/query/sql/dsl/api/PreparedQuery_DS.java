package com.neaterbits.query.sql.dsl.api;

abstract class PreparedQuery_DS<DATASOURCE extends QueryDataSource> {

	private final DATASOURCE dataSource;
	private final PreparedQueryMetaData metaData;

	PreparedQuery_DS(DATASOURCE dataSource, PreparedQueryMetaData metaData) {

		if (dataSource == null) {
			throw new IllegalArgumentException("dataSource == null");
		}
		
		if (metaData == null) {
			throw new IllegalArgumentException("metaData == null");
		}

		this.dataSource = dataSource;
		this.metaData = metaData;
	}

	abstract Object execute(ParamValueResolver collectedParams);

	
	final DATASOURCE getDataSource() {
		return dataSource;
	}

	final PreparedQueryMetaData getMetaData() {
		return metaData;
	}
}
