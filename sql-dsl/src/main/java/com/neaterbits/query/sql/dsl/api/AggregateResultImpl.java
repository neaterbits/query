package com.neaterbits.query.sql.dsl.api;

abstract class AggregateResultImpl<MODEL, RESULT> extends SelectSourceBuilderImpl<MODEL, RESULT> {

	AggregateResultImpl(QueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(result, modelCompiler);
	}
}

