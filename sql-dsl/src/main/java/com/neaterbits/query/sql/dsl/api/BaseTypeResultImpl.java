package com.neaterbits.query.sql.dsl.api;

abstract class BaseTypeResultImpl<MODEL, RESULT> extends SelectSourceBuilderImpl<MODEL, RESULT> {

	BaseTypeResultImpl(QueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(result, modelCompiler);
	}
}
