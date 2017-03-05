package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;
import java.util.function.Function;

final class CollectedMapping_Named extends CollectedMapping {
	private final Function<?, ?> getter;
	
	CollectedMapping_Named(CollectedItem original, Function<?, ?> getter, BiConsumer<?, ?> setter, CollectedFunctions functions) {
		super(original, new FunctionGetter(getter), setter, functions);
		
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		this.getter = getter;
	}

	Function<?, ?> getFunctionGetter() {
		return getter;
	}
}
