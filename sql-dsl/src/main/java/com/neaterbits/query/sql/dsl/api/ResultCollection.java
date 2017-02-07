package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

interface ResultCollection {

	void addResult(Object o);
	
	Collection<Object> asCollection();
	
}
