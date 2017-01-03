package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;

abstract class CollectedMapping extends CollectedItem {
	private final CollectedItem original;
	private final Getter getter;
	private final BiConsumer<?, ?> setter;

	
	CollectedMapping(CollectedItem original, Getter getter, BiConsumer<?, ?> setter) {
		
		if (original == null) {
			throw new IllegalArgumentException("original == null");
		}
		
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		if (setter == null) {
			throw new IllegalArgumentException("setter == null");
		}

		this.original = original;
		this.getter = getter;
		this.setter = setter;
	}
	
	final Getter getGetter() {
		return getter;
	}

	final CollectedItem getOriginal() {
		return original;
	}

	final BiConsumer<?, ?> getSetter() {
		return setter;
	}
}
