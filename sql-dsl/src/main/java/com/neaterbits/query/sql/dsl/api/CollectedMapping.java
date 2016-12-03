package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;

abstract class CollectedMapping extends QueryBuilderItem {
	private final QueryBuilderItem original;
	private final BiConsumer<?, ?> setter;

	
	CollectedMapping(QueryBuilderItem original, BiConsumer<?, ?> setter) {
		
		if (original == null) {
			throw new IllegalArgumentException("original == null");
		}
		
		if (setter == null) {
			throw new IllegalArgumentException("setter == null");
		}

		this.original = original;
		this.setter = setter;
	}
	
	final QueryBuilderItem getOriginal() {
		return original;
	}

	final BiConsumer<?, ?> getSetter() {
		return setter;
	}
}
