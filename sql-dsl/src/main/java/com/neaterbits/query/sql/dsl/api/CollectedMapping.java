package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;

abstract class CollectedMapping extends QueryBuilderItem {
	private final QueryBuilderItem original;
	private final Getter getter;
	private final BiConsumer<?, ?> setter;

	
	CollectedMapping(QueryBuilderItem original, Getter getter, BiConsumer<?, ?> setter) {
		
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

	final QueryBuilderItem getOriginal() {
		return original;
	}

	final BiConsumer<?, ?> getSetter() {
		return setter;
	}
}
