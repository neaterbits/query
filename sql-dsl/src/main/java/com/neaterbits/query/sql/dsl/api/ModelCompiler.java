package com.neaterbits.query.sql.dsl.api;

interface ModelCompiler<MODEL> {

	MODEL compile(CompiledQuery compiledQuery);
	
}
