package com.neaterbits.query.sql.dsl.api.helper;

import java.util.ArrayList;
import java.util.List;

public final class QueryTestDSBuilder {

	private final List<Object> instances;

	QueryTestDSBuilder() {
		this.instances = new ArrayList<>();
	}

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
