package com.neaterbits.query.sql.dsl.api;

final class ClassicSingleTypeResult<MODEL, RESULT> extends BaseTypeResultImpl<MODEL, RESULT> 
	implements IClassicSingleTypeResult<MODEL, RESULT> {

	ClassicSingleTypeResult(SharedSelectSource selectSource, ModelCompiler<MODEL> modelCompiler) {
		super(new CollectedQueryResult_Entity_Single(selectSource), modelCompiler);
	}
}
