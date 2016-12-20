package com.neaterbits.query.sql.dsl.api.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class QueryTestDSBuilder {

	private final Function<Object, Object> getPK;
	private final List<TestInstance> instances;

	QueryTestDSBuilder(Function<Object, Object> getPK) {
		this.instances = new ArrayList<>();
		this.getPK = getPK;
	}

	public QueryTestDSBuilder add(Object instance) {
		
		if (instance == null) {
			throw new IllegalArgumentException("instance == null");
		}

		final Object pk = getPK != null ? getPK.apply(instance) : null;
		
		instances.add(new TestInstance(instance, pk));

		return this;
	}

	List<TestInstance> getInstances() {
		return instances;
	}
}
