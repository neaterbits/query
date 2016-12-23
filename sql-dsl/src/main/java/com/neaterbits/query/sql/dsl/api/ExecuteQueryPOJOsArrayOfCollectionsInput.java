package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

final class ExecuteQueryPOJOsArrayOfCollectionsInput implements ExecuteQueryPOJOsInput {
	private final Collection<?> [] dataCollections;

	
	ExecuteQueryPOJOsArrayOfCollectionsInput(Collection<?> ... dataCollections) {
		
		if (dataCollections == null) {
			throw new IllegalArgumentException("dataCollections == null");
		}
		
		this.dataCollections = dataCollections;
	}

	@Override
	public Collection<?> getPOJOs(int idx) {
		return dataCollections[idx];
	}
}
