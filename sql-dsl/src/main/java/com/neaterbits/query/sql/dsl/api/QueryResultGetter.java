package com.neaterbits.query.sql.dsl.api;

public interface QueryResultGetter<RESULT_TYPE> {
	
	RESULT_TYPE get();
	
}