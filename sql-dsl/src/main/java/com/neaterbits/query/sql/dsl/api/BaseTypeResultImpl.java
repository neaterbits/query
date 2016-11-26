package com.neaterbits.query.sql.dsl.api;

abstract class BaseTypeResultImpl<MODEL, RESULT> extends SelectSourceBuilderImpl<MODEL, RESULT> {

	BaseTypeResultImpl(Class<?> resultType, ModelCompiler<MODEL> modelCompiler) {
		super(resultType, modelCompiler);
	}
}
