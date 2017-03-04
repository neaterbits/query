package com.neaterbits.query.sql.dsl.api;

@Deprecated
final class Classic_Collector_SingleTypeResult<MODEL, RESULT>

	extends Classic_Collector_EntityResult_Base<
		MODEL,
		RESULT,
		ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL, RESULT>,
		ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>
	>

	implements IClassicResult_Entity_Single<MODEL, RESULT> {

	Classic_Collector_SingleTypeResult(ClassicSelect select, SharedSelectSource selectSource, ModelCompiler<MODEL> modelCompiler) {
		super(select, new CollectedQueryResult_Entity_Single(selectSource), modelCompiler);
	}

	@Override
	ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL, RESULT> createWhereOrJoinForNamed() {
		return new SQL_Collector_WhereOrJoin_SingleResult_Named<>(this);
	}

	@Override
	ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT> createWhereOrJoinForAlias() {
		return new SQL_Collector_WhereOrJoin_SingleResult_Alias<>(this);
	}
	
	@Override
	CollectedQueryResult getCollectedQueryResult() {
		throw new UnsupportedOperationException("Should already have passed query-result to constructor");
	}	
}
