package com.neaterbits.query.sql.dsl.api;

abstract class Collector_Base<MODEL> extends CollectedItem {

	private final QueryCollectorImpl queryCollector;
	private final ModelCompiler<MODEL> modelCompiler;

	Collector_Base(Collector_Base<MODEL> last) {
		this(last.queryCollector, last.modelCompiler);
	}

	Collector_Base(QueryCollectorImpl queryCollector, ModelCompiler<MODEL> modelCompiler) {
		
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
