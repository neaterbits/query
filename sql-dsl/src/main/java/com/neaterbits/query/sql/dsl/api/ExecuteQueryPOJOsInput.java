package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

abstract class ExecuteQueryPOJOsInput {

	abstract Collection<?> getPOJOsForName(String name);
	
}
