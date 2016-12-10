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
				q.executeMappingSetter(query, 0, result, scratch[0]);
				break;
				
			default:
				// More tha one
				for (int i = 0; i < scratch.length; ++ i) {
					q.executeMappingSetter(query, 0, result, scratch[i]);
				}
				break;
		}
		
		return result;
	}
	
}
