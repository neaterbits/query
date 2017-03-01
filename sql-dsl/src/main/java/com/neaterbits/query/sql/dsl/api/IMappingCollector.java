package com.neaterbits.query.sql.dsl.api;

interface IMappingCollector<MODEL, RESULT> extends ISharedSelectSourceBuilder<MODEL, RESULT> {

	MappingCollector getMappingCollector();
	
}
