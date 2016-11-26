package com.neaterbits.query.sql.dsl.api;

final class MultiTypeResultImpl<MODEL, RESULT> extends BaseTypeResultImpl<MODEL, RESULT>
	implements MultiTypeResult<MODEL, RESULT> {

	MultiTypeResultImpl(Class<?> resultType, ModelCompiler<MODEL> modelCompiler) {
		super(resultType, modelCompiler);
	}
}
