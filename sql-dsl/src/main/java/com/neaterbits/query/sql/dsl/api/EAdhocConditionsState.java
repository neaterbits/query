package com.neaterbits.query.sql.dsl.api;

public enum EAdhocConditionsState {
	
	NONE,
	
	WHERE_FROM_JOIN, // Single coming from a JOIN which means additional ones merged in later must always be AND - or we would have to split in sub-query  
	
	AND_MERGED_FROM_JOIN,
	
	AND_IN_JOIN,
	OR_IN_JOIN,
	
	WHERE_FROM_OUTER, // Single coming from outer where-clause, may need to be merged with previous conditions from Joins
	
	AND_IN_OUTER,
	OR_IN_OUTER,

	AND_FROM_JOIN_AND_OUTER,
	
	WHERE_FROM_JOIN_AND_WHERE_FROM_OUTER
}
