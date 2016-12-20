package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

import com.neaterbits.query.sql.dsl.api.QueryDataSourcePojoBase;

public class QueryDataSourcePojoWithList extends QueryDataSourcePojoBase {

	private final Collection<?> instances;

	public QueryDataSourcePojoWithList(Collection<?> instances) {

		if (instances == null) {
			throw new IllegalArgumentException("instances == null");
		}

		this.instances = instances;
	}

	@Override
	DSPreparedQuery prepareSingleQuery(CompiledQuery compiled) {
		return prepare(compiled);
	}

	@Override
	DSPreparedQuery prepareMultiQuery(CompiledQuery compiled) {
		return prepare(compiled);
	}
	
	private PojoPreparedQuery prepare(CompiledQuery compiled) {

		final ExecuteQueryPOJOsFromListInput input = new ExecuteQueryPOJOsFromListInput(instances, compiled.getSelectSourceClasses());

		return new PojoPreparedQuery(compiled, input);
	}
}
