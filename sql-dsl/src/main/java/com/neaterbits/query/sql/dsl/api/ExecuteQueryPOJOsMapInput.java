package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;
import java.util.Map;

final class ExecuteQueryPOJOsMapInput extends ExecuteQueryPOJOsInput {
	private final Map<String, Collection<?>> dataCollections;

	
	ExecuteQueryPOJOsMapInput(Map<String, Collection<?>> dataCollections) {
		
		if (dataCollections == null) {
			throw new IllegalArgumentException("dataCollections == null");
		}
		
		this.dataCollections = dataCollections;
	}

	@Override
	Collection<?> getPOJOsForName(String name) {
		
		if (name == null) {
			throw new IllegalArgumentException("name == null");
		}
		
		return dataCollections.get(name);
	}
}
