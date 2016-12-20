package com.neaterbits.query.sql.dsl.api;


final class ExecuteQueryUtil {


	static <QUERY> Object mapToOneMappedInstance(ExecutableQuery<QUERY> q, QUERY query, Object [] scratch) {
		
		final int mappingCount = q.getMappingCount(query);
		
		if (scratch.length != mappingCount) {
			throw new IllegalStateException("Mismatch between sratch bug and mapping count");
		}
		
		final Object result = q.createMappedInstance(query);
		
		
		switch (mappingCount) {
			case 0:
				throw new IllegalStateException("empty mappings");

			case 1:
				// Just one field
				// Get value
				final Object singleValue = q.executeMappingGetter(query, 0, scratch[0]);
				
				// Set value into result
				q.executeMappingSetter(query, 0, result, singleValue);
				break;
				
			default:
				// More than one
				for (int i = 0; i < scratch.length; ++ i) {
					final Object value = q.executeMappingGetter(query, i, scratch[i]);

					q.executeMappingSetter(query, i, result, value);
				}
				break;
		}
		
		return result;
	}
}
