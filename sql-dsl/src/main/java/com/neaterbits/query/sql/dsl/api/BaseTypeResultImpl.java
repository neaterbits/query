package com.neaterbits.query.sql.dsl.api;

abstract class BaseTypeResultImpl<MODEL, RESULT> extends SelectSourceBuilderImpl<MODEL, RESULT> {

	BaseTypeResultImpl(QueryResultMode queryResultMode, Class<?> resultType, ModelCompiler<MODEL> modelCompiler) {
		super(queryResultMode, resultType, modelCompiler);
	}
}
