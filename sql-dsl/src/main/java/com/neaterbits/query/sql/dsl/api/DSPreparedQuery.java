package com.neaterbits.query.sql.dsl.api;

abstract class DSPreparedQuery {

	abstract Object execute(ParamValueResolver collectedParams);
	
}
