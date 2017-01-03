package com.neaterbits.query.sql.dsl.api;

abstract class Collector_EntityResult_Base<MODEL, RESULT> extends Collector_SelectSource<MODEL, RESULT> {

	Collector_EntityResult_Base(CollectedQueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(result, modelCompiler);
	}
}
