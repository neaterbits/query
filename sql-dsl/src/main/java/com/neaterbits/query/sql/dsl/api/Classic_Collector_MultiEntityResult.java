package com.neaterbits.query.sql.dsl.api;

final class Classic_Collector_MultiEntityResult<MODEL, RESULT> extends Collector_EntityResult_Base<MODEL, RESULT>
	implements IClassicMultiEntityResult<MODEL, RESULT> {

	Classic_Collector_MultiEntityResult(SharedSelectSource selectSource, ECollectionType collectionType, ModelCompiler<MODEL> modelCompiler) {
		super(new CollectedQueryResult_Entity_Multi(selectSource, collectionType), modelCompiler);
	}
}
