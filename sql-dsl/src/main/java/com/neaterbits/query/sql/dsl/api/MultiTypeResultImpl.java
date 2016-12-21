package com.neaterbits.query.sql.dsl.api;

final class MultiTypeResultImpl<MODEL, RESULT> extends BaseTypeResultImpl<MODEL, RESULT>
	implements IClassicMultiTypeResult<MODEL, RESULT> {

	MultiTypeResultImpl(Class<?> resultType, ModelCompiler<MODEL> modelCompiler) {
		super(new QueryResultEntityMulti(resultType), modelCompiler);
	}
}
