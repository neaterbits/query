package com.neaterbits.query.sql.dsl.api.testhelper;

import java.util.ArrayList;
import java.util.List;

final class QueryTestDSBuilderInMemoryImpl implements QueryTestDSBuilder {

	private final List<Object> instances;

	public QueryTestDSBuilderInMemoryImpl() {
		this.instances = new ArrayList<>();
	}
	
	@Override
	public QueryTestDSBuilder add(Object instance) {
		
		if (instance == null) {
			throw new IllegalArgumentException("instance == null");
		}

		instances.add(instance);

		return this;
	}

	List<Object> getInstances() {
		return instances;
	}
}
