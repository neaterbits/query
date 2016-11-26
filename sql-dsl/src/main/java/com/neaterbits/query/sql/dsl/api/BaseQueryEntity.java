package com.neaterbits.query.sql.dsl.api;

abstract class BaseQueryEntity<MODEL> {

	private final QueryCollectorImpl queryCollector;
	private final ModelCompiler<MODEL> modelCompiler;

	BaseQueryEntity(BaseQueryEntity<MODEL> last) {
		this(last.queryCollector, last.modelCompiler);
	}

	BaseQueryEntity(QueryCollectorImpl queryCollector, ModelCompiler<MODEL> modelCompiler) {
		
		if (queryCollector == null) {
			throw new IllegalArgumentException("queryCollector == null");
		}
		
		if (modelCompiler == null) {
			throw new IllegalArgumentException("modelCompiler == null");
		}
		
		this.queryCollector = queryCollector;
		this.modelCompiler = modelCompiler;
	}

	final QueryCollectorImpl getQueryCollector() {
		return queryCollector;
	}

	final ModelCompiler<MODEL> getModelCompiler() {
		return modelCompiler;
	}
}
