package com.neaterbits.query.sql.dsl.api;

final class Short_Collector_SingleResult<MODEL, RESULT>
	extends Short_Collector_Result<
			MODEL,
			RESULT,

			ISQLLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT>,
			ISQLLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT>,
						
			IShortResult_Mapped_Single_Named<MODEL, RESULT>,
			IShortResult_Mapped_Single_Alias<MODEL, RESULT>>

	implements IShortResult_Single<MODEL, RESULT> {

	Short_Collector_SingleResult(SharedSelectSource selectSource, ModelCompiler<MODEL> modelCompiler) {
		super(selectSource, modelCompiler);
	}

	@Override
	Short_Collector_MapToResult_Base<MODEL, RESULT, ISQLLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT>, ISQLLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT>> createMapToResult() {
		
		final CollectedQueryResult_Mapped_Single collected = new CollectedQueryResult_Mapped_Single(getResultType());
		
		return new Short_Collector_MapToResult_Single<>(collected, getModelCompiler());
	}
}
