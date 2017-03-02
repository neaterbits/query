package com.neaterbits.query.sql.dsl.api;

final class Classic_Collector_SingleResult<MODEL, RESULT>

	extends Classic_Collector_Result<
		MODEL,
		RESULT,
		ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL, RESULT>,
		ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>,
	
		ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL, RESULT>,
		ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>,
		
		IClassicResult_Mapped_Single_Named<MODEL, RESULT>,
		IClassicResult_Mapped_Single_Alias<MODEL, RESULT>
		>

	implements IClassicResult_Single<MODEL, RESULT> {

	Classic_Collector_SingleResult(SharedSelectSource selectSource, ModelCompiler<MODEL> modelCompiler) {
		super(selectSource, modelCompiler);
	}

	@Override
	Classic_Collector_MapToResult_Base<MODEL, RESULT, ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL, RESULT>, ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>> createMapToResult() {
		return new Classic_Collector_MapToResult_Single<>(getResultType(), getModelCompiler());
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
		// Called directly on initial collector returned from list(), so return multi-entity since no map() calls occured
		return new CollectedQueryResult_Entity_Single(getSelectSource());
	}
}
