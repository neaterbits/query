package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

interface ExecuteQueryPOJOsInput {

	Collection<?> getPOJOs(int idx);
	
}
