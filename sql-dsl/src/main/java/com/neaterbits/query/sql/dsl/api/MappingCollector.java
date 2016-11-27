package com.neaterbits.query.sql.dsl.api;

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

	public List<CollectedMapping> getCollectedMappings() {
		return collectedMapping;
	}
}
