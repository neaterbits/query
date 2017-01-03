package com.neaterbits.query.sql.dsl.api;

abstract class BaseTypeResultImpl<MODEL, RESULT> extends Collector_SelectSource<MODEL, RESULT> {

	BaseTypeResultImpl(CollectedQueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(result, modelCompiler);
	}
}
