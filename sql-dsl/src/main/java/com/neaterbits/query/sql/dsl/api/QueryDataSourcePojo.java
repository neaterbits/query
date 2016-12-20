package com.neaterbits.query.sql.dsl.api;

import java.util.List;

public final class QueryDataSourcePojo extends QueryDataSourceBase {

	private final List<Object> instances;

	public QueryDataSourcePojo(List<Object> instances) {
		this.instances = instances;
	}

	@Override
	DSPreparedQuery prepareSingleQuery(CompiledQuery compiled) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	DSPreparedQuery prepareMultiQuery(CompiledQuery compiled) {
		// TODO Auto-generated method stub
		return null;
	}
}
