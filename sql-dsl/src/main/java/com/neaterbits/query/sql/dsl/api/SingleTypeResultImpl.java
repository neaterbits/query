package com.neaterbits.query.sql.dsl.api;

final class SingleTypeResultImpl<MODEL, RESULT> extends BaseTypeResultImpl<MODEL, RESULT> 
	implements IClassicSingleTypeResult<MODEL, RESULT> {

	SingleTypeResultImpl(Class<?> resultType, ModelCompiler<MODEL> modelCompiler) {
		super(new QueryResultEntitySingle(resultType), modelCompiler);
	}
}
