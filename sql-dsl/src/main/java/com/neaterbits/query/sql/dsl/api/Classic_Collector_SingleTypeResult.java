package com.neaterbits.query.sql.dsl.api;

@Deprecated
final class Classic_Collector_SingleTypeResult<MODEL, RESULT>

	extends Classic_Collector_EntityResult_Base<
		MODEL,
		RESULT,
		ISQLLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT>,
		ISQLLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT>
	>

	implements IClassicResult_Entity_Single<MODEL, RESULT> {

	Classic_Collector_SingleTypeResult(SharedSelectSource selectSource, ModelCompiler<MODEL> modelCompiler) {
		super(new CollectedQueryResult_Entity_Single(selectSource), modelCompiler);
	}

	@Override
	ISQLLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT> createWhereOrJoinForNamed() {
		return new SQL_Collector_WhereOrJoin_SingleResult_Named<>(this);
	}

	@Override
	ISQLLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT> createWhereOrJoinForAlias() {
		return new SQL_Collector_WhereOrJoin_SingleResult_Alias<>(this);
	}
	
	@Override
	CollectedQueryResult getCollectedQueryResult() {
		throw new UnsupportedOperationException("Should already have passed query-result to constructor");
	}	
}
