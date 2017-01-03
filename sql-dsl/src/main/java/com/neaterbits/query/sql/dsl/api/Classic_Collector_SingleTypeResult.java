package com.neaterbits.query.sql.dsl.api;

final class Classic_Collector_SingleTypeResult<MODEL, RESULT> extends Collector_EntityResult_Base<MODEL, RESULT> 
	implements IClassicSingleEntityResult<MODEL, RESULT> {

	Classic_Collector_SingleTypeResult(SharedSelectSource selectSource, ModelCompiler<MODEL> modelCompiler) {
		super(new CollectedQueryResult_Entity_Single(selectSource), modelCompiler);
	}
}
