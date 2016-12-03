package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;
import java.util.function.Function;

final class CollectedMappingTable extends CollectedMapping {
	private final Function<?, ?> getter;
	
	CollectedMappingTable(QueryBuilderItem original, Function<?, ?> getter, BiConsumer<?, ?> setter) {
		super(original, setter);
		
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		this.getter = getter;
	}

	Function<?, ?> getGetter() {
		return getter;
	}
}
