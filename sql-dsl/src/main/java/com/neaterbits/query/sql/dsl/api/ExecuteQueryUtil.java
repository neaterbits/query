package com.neaterbits.query.sql.dsl.api;


final class ExecuteQueryUtil {

	static <QUERY> Object mapToOneMappedInstance(ExecutableQuery<QUERY> q, QUERY query, ExecuteQueryScratch scratch) {
		
		final int mappingCount = q.getMappingCount(query);

		if (!scratch.numResultPartsIs(mappingCount)) {
			throw new IllegalStateException("Mismatch between sratch bug and mapping count");
		}
		
		final Object result;
		

		switch (mappingCount) {
			case 0:
				throw new IllegalStateException("empty mappings");

			case 1:
				result = mapSingleFromInstance(q, query, scratch.get(0));
				break;
				
			default:
				result = mapMultipleFromScratch(q, query, mappingCount, scratch);
				break;
		}
		
		return result;
	}
	
	private static <QUERY, T> Object mapMultipleFromScratch(ExecutableQuery<QUERY> q, QUERY query, int mappingCount, ExecuteQueryScratch scratch) {
		final Object result = q.createMappedInstance(query);

		// More than one
		for (int mappingIdx = 0; mappingIdx < mappingCount; ++ mappingIdx) {
			
			final int sourceIdx = q.getMappingSourceIdx(query, mappingIdx);
			
			// Must look up from scratch-buffer on source idx
			final Object instance = scratch.get(sourceIdx);
			
			try {
				final Object value = q.executeMappingGetter(query, mappingIdx, instance);

				q.executeMappingSetter(query, mappingIdx, result, value);
			}
			catch (RuntimeException ex) {
				throw new IllegalStateException("Expection while mapping scratch  " + mappingIdx + " of class " + instance.getClass().getSimpleName(), ex);
			}
		}
		
		return result;
	}
	

	static <QUERY, T> Object mapMultipleFromArray(ExecutableQuery<QUERY> q, QUERY query, int mappingCount, Object [] array) {
		final Object result = q.createMappedInstance(query);

		// More than one
		for (int mappingIdx = 0; mappingIdx < mappingCount; ++ mappingIdx) {
			
			
			try {
				q.executeMappingSetter(query, mappingIdx, result, array[mappingIdx]);
			}
			catch (RuntimeException ex) {
				throw new IllegalStateException("Expection while mapping scratch  " + mappingIdx + " of class " + array[mappingIdx], ex);
			}
		}
		
		return result;
	}
	
	
	private static <QUERY> Object mapSingleFromInstance(ExecutableQuery<QUERY> q, QUERY query, Object instance) {
		
		// Just one field
		// Get value
		final Object singleValue = q.executeMappingGetter(query, 0, instance);

		return mapSingleFromFieldValue(q, query, singleValue);
	}

	static <QUERY> Object mapSingleFromFieldValue(ExecutableQuery<QUERY> q, QUERY query, Object singleValue) {
		final Object result = q.createMappedInstance(query);

		q.executeMappingSetter(query, 0, result, singleValue);
		
		return result;
	}
	
}
