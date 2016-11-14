package com.neaterbits.query.sql.dsl.api;

abstract class BaseMapToResultImpl<RESULT> 
	extends SelectSourceBuilderImpl<RESULT> {

	private final MappingCollector mappingCollector;

	BaseMapToResultImpl(Class<?> resultType) {
		super(resultType);

		this.mappingCollector = new MappingCollector();

		// Collect mappings, should ever only create one of these
		getQueryCollector().setMappings(mappingCollector);
	}

	final MappingCollector getMappingCollector() {
		return mappingCollector;
	}
}
