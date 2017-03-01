package com.neaterbits.query.sql.dsl.api;

final class SQL_Collector_SingleTypeResult<MODEL, RESULT>

	extends Collector_EntityResult_Base<
		MODEL,
		RESULT,
		ISQLLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT>,
		ISQLLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT>
	>

	implements IClassicResult_Entity_Single<MODEL, RESULT> {

	SQL_Collector_SingleTypeResult(SharedSelectSource selectSource, ModelCompiler<MODEL> modelCompiler) {
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
}
