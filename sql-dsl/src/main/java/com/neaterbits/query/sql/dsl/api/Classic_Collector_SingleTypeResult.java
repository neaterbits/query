package com.neaterbits.query.sql.dsl.api;

final class Classic_Collector_SingleTypeResult<MODEL, RESULT>

	extends Collector_EntityResult_Base<
		MODEL,
		RESULT,
		IClassicLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT>,
		IClassicLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT>
	>

	implements IClassicResult_Entity_Single<MODEL, RESULT> {

	Classic_Collector_SingleTypeResult(SharedSelectSource selectSource, ModelCompiler<MODEL> modelCompiler) {
		super(new CollectedQueryResult_Entity_Single(selectSource), modelCompiler);
	}

	@Override
	IClassicLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT> createWhereOrJoinForNamed() {
		return new Classic_Collector_WhereOrJoin_SingleResult_Named<>(this);
	}

	@Override
	IClassicLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT> createWhereOrJoinForAlias() {
		return new Classic_Collector_WhereOrJoin_SingleResult_Alias<>(this);
	}
}
