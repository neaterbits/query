package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

final class QueryTestDSBuilderPersistentImpl<CTX> implements QueryTestDSBuilder {
	private final CTX ctx;
	private final BiFunction<CTX, Object, Object> getPK;
	private final List<TestInstance> instances;

	QueryTestDSBuilderPersistentImpl(CTX ctx, BiFunction<CTX, Object, Object> getPK) {
		this.ctx = ctx;
		this.instances = new ArrayList<>();
		this.getPK = getPK;
	}

	@Override
	public QueryTestDSBuilder add(Object instance) {
		
		if (instance == null) {
			throw new IllegalArgumentException("instance == null");
		}

		final Object pk = getPK != null ? getPK.apply(ctx, instance) : null;
		
		instances.add(new TestInstance(instance, pk));

		return this;
	}

	List<TestInstance> getInstances() {
		return instances;
	}
}
