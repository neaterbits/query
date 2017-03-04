package com.neaterbits.query.sql.dsl.api;

public abstract class DataStore<ENTITIES> {

	protected abstract QueryDataSource createDataSource(ENTITIES entities);

}
