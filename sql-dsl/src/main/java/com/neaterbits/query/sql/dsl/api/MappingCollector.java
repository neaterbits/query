package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

final class MappingCollector {

	private final List<CollectedMapping> collectedMapping;

	MappingCollector() {
		this.collectedMapping = new ArrayList<CollectedMapping>();
	}

	void add(Function<?, ?> getter, BiConsumer<?, ?> setter) {

		final CollectedMapping mapping = new CollectedMappingTable(getter, setter);

		checkSameType(mapping);

		collectedMapping.add(mapping);
	}

	void add(Supplier<?> getter, BiConsumer<?, ?> setter) {

		final CollectedMapping mapping = new CollectedMappingAlias(getter, setter);

		checkSameType(mapping);

		collectedMapping.add(mapping);
	}
	
	private void checkSameType(CollectedMapping mapping) {
		if (!collectedMapping.isEmpty() && !collectedMapping.get(0).getClass().equals(mapping.getClass())) {
			throw new IllegalStateException("Mix of table and alias mappings");
		}
	}
	
	
	public List<CollectedMapping> getCollectedMappings() {
		return collectedMapping;
	}
}
