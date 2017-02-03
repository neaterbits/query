package com.neaterbits.query.sql.dsl.api;

abstract class Collector_AggregateResult<
			MODEL,
			RESULT,

			// always non result processing for aggregate queries
			NAMED_WHERE_OR_JOIN extends IClassicLogical_WhereOrJoin_NonProcessResult_Named<MODEL, RESULT>,
			ALIAS_WHERE_OR_JOIN extends IClassicLogical_WhereOrJoin_NonProcessResult_Alias<MODEL, RESULT> 
		>

	extends Collector_SelectSource<
			MODEL,
			RESULT,
			NAMED_WHERE_OR_JOIN,
			ALIAS_WHERE_OR_JOIN
			> {

	Collector_AggregateResult(CollectedQueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(result, modelCompiler);
	}
}

