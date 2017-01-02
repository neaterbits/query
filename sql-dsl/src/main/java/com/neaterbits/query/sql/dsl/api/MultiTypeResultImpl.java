package com.neaterbits.query.sql.dsl.api;

final class MultiTypeResultImpl<MODEL, RESULT> extends BaseTypeResultImpl<MODEL, RESULT>
	implements IClassicMultiTypeResult<MODEL, RESULT> {

	MultiTypeResultImpl(SelectSource selectSource, ECollectionType collectionType, ModelCompiler<MODEL> modelCompiler) {
		super(new QueryResultEntityMulti(selectSource, collectionType), modelCompiler);
	}
}
