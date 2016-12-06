package com.neaterbits.query.sql.dsl.api;

abstract class BaseMapToResultImpl<MODEL, RESULT> 
	extends SelectSourceBuilderImpl<MODEL, RESULT> {

	private final MappingCollector mappingCollector;

	BaseMapToResultImpl(QueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(result, modelCompiler);

		this.mappingCollector = new MappingCollector();

		// Collect mappings, should ever only create one of these
		getQueryCollector().setMappings(mappingCollector);
	}

	final MappingCollector getMappingCollector() {
		return mappingCollector;
	}
}
