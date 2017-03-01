package com.neaterbits.query.sql.dsl.api;

@Deprecated
abstract class Classic_Collector_EntityResult_Base<MODEL, RESULT,
		NAMED_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
		ALIAS_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT> 

		> extends Classic_Collector_SelectSource<MODEL, RESULT, NAMED_WHERE_OR_JOIN, ALIAS_WHERE_OR_JOIN> {

	Classic_Collector_EntityResult_Base(CollectedQueryResult result, ModelCompiler<MODEL> modelCompiler) {
		super(result, modelCompiler);
	}
}
