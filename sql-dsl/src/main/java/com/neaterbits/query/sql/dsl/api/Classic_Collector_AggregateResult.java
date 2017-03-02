package com.neaterbits.query.sql.dsl.api;

@Deprecated // for now
abstract class Classic_Collector_AggregateResult<
			MODEL,
			RESULT,

			// always non result processing for aggregate queries
			NAMED_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL, RESULT>,
			ALIAS_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT> 
		>

	extends Classic_Collector_SelectSource<
			MODEL,
			RESULT,
			NAMED_WHERE_OR_JOIN,
			ALIAS_WHERE_OR_JOIN
			> {

	Classic_Collector_AggregateResult(CollectedQueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(result, modelCompiler);
	}
}

