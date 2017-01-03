package com.neaterbits.query.sql.dsl.api;

abstract class AggregateResultImpl<MODEL, RESULT> extends Collector_SelectSource<MODEL, RESULT> {

	AggregateResultImpl(CollectedQueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(result, modelCompiler);
	}
}

