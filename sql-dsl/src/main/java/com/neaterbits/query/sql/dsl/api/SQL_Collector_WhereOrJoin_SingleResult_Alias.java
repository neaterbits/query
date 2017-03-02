package com.neaterbits.query.sql.dsl.api;

final class SQL_Collector_WhereOrJoin_SingleResult_Alias<MODEL, RESULT>

		extends SQL_Collector_WhereOrJoin_Alias_Base<
			MODEL,
			RESULT,
			
			// For sharing code in baseclass
			ISQLJoin_Condition_SingleResult_Named<MODEL, RESULT, Object, Object>,
			
			ISQLJoin_Condition_SingleResult_Alias<MODEL, RESULT>,
			
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>
			> 

	implements
		ISQLLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT>,
		ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT> {

	SQL_Collector_WhereOrJoin_SingleResult_Alias(Collector_Base<MODEL> last) {
		super(last);
	}

	@Override
	SQL_Collector_Or_Alias<MODEL, RESULT, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>> createAliasOrCollector() {
		return new SQL_Collector_Or_NonProcessResult_Alias<>(this);
	}

	@Override
	SQL_Collector_And_Alias<MODEL, RESULT, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>> createAliasAndCollector() {
		return new SQL_Collector_And_NonProcessResult_Alias<>(this);
	}
}
