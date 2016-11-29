package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;

abstract class CollectedMapping {
	private final BiConsumer<?, ?> setter;
	
	CollectedMapping(BiConsumer<?, ?> setter) {
		
		if (setter == null) {
			throw new IllegalArgumentException("setter == null");
		}
		
		this.setter = setter;
	}

	final BiConsumer<?, ?> getSetter() {
		return setter;
	}
}
