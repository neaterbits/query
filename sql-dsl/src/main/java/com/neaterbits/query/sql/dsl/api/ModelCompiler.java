package com.neaterbits.query.sql.dsl.api;

interface ModelCompiler<MODEL> {

	MODEL compile(Collector_Query<MODEL> query);
	
}
