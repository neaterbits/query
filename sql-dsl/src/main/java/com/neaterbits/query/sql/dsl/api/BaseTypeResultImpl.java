package com.neaterbits.query.sql.dsl.api;

abstract class BaseTypeResultImpl<MODEL, RESULT> extends SelectSourceBuilderImpl<MODEL, RESULT> {

	BaseTypeResultImpl(boolean singleResult, Class<?> resultType, ModelCompiler<MODEL> modelCompiler) {
		super(singleResult, resultType, modelCompiler);
	}
}
