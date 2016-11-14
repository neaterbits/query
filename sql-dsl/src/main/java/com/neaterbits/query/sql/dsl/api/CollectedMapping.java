package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;
import java.util.function.Function;

final class CollectedMapping {
	private final Function<?, ?> getter;
	private final BiConsumer<?, ?> setter;
	
	CollectedMapping(Function<?, ?> getter, BiConsumer<?, ?> setter) {
		
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		if (setter == null) {
			throw new IllegalArgumentException("setter == null");
		}
		
		this.getter = getter;
		this.setter = setter;
	}

	Function<?, ?> getGetter() {
		return getter;
	}

	BiConsumer<?, ?> getSetter() {
		return setter;
	}
}
