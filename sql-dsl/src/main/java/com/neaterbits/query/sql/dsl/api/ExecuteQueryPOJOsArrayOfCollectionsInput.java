package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

final class ExecuteQueryPOJOsArrayOfCollectionsInput extends ExecuteQueryPOJOsInput {
	private final Collection<?> [] dataCollections;

	
	ExecuteQueryPOJOsArrayOfCollectionsInput(Collection<?> ... dataCollections) {
		
		if (dataCollections == null) {
			throw new IllegalArgumentException("dataCollections == null");
		}
		
		this.dataCollections = dataCollections;
	}

	@Override
	Collection<?> getPOJOs(int idx) {
		return dataCollections[idx];
	}
}
