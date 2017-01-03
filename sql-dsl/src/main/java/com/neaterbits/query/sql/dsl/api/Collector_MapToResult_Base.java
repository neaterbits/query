package com.neaterbits.query.sql.dsl.api;

abstract class Collector_MapToResult_Base<MODEL, RESULT> 
	extends Collector_SelectSource<MODEL, RESULT> {

	private final MappingCollector mappingCollector;

	Collector_MapToResult_Base(CollectedQueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(result, modelCompiler);

		this.mappingCollector = new MappingCollector();

		// Collect mappings, should ever only create one of these
		getQueryCollector().setMappings(mappingCollector);
	}

	final MappingCollector getMappingCollector() {
		return mappingCollector;
	}
}
