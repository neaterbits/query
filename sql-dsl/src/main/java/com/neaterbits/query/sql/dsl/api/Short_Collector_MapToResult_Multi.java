package com.neaterbits.query.sql.dsl.api;

final class Short_Collector_MapToResult_Multi<MODEL, RESULT>
	extends Short_Collector_MapToResult_Base<
	MODEL,
	RESULT,
	ISQLLogical_WhereOrJoin_MultiMapped_Named<MODEL,RESULT>,
	ISQLLogical_WhereOrJoin_MultiMapped_Alias<MODEL,RESULT>> {

	Short_Collector_MapToResult_Multi(CollectedQueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(result, modelCompiler);
	}
}
