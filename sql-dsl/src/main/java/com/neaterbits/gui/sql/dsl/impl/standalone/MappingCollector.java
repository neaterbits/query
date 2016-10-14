package com.neaterbits.gui.sql.dsl.impl.standalone;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

final class MappingCollector {

	private final List<CollectedMapping> collectedMapping;

	MappingCollector() {
		this.collectedMapping = new ArrayList<CollectedMapping>();
	}

	void add(Function<?, ?> getter, BiConsumer<?, ?> setter) {

		final CollectedMapping mapping = new CollectedMapping(getter, setter);

		collectedMapping.add(mapping);
	}
	
	private static class CollectedMapping {
		private final Function<?, ?> getter;
		private final BiConsumer<?, ?> setter;

		
		CollectedMapping(Function<?, ?> getter, BiConsumer<?, ?> setter) {
			
			if (getter != null) {
				throw new IllegalArgumentException("getter != null");
			}
			
			if (setter != null) {
				throw new IllegalArgumentException("setter != null");
			}
			
			this.getter = getter;
			this.setter = setter;
		}
		
	}
	
}
