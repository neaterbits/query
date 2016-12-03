package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

final class CollectedMappingAlias extends CollectedMapping {
	private final Supplier<?> getter;
	
	CollectedMappingAlias(QueryBuilderItem original, Supplier<?> getter, BiConsumer<?, ?> setter) {
		super(original, setter);
		
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		this.getter = getter;
	}

	Supplier<?> getGetter() {
		return getter;
	}
}
