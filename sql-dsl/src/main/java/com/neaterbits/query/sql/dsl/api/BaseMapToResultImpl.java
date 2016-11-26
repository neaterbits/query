package com.neaterbits.query.sql.dsl.api;

abstract class BaseMapToResultImpl<MODEL, RESULT> 
	extends SelectSourceBuilderImpl<MODEL, RESULT> {

	private final MappingCollector mappingCollector;

	BaseMapToResultImpl(Class<?> resultType, ModelCompiler<MODEL> modelCompiler) {
		super(resultType, modelCompiler);

		this.mappingCollector = new MappingCollector();

		// Collect mappings, should ever only create one of these
		getQueryCollector().setMappings(mappingCollector);
	}

	final MappingCollector getMappingCollector() {
		return mappingCollector;
	}
}
