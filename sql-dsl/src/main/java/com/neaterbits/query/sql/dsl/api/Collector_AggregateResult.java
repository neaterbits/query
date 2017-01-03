package com.neaterbits.query.sql.dsl.api;

abstract class Collector_AggregateResult<MODEL, RESULT> extends Collector_SelectSource<MODEL, RESULT> {

	Collector_AggregateResult(CollectedQueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(result, modelCompiler);
	}
}

