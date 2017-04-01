package com.neaterbits.query.sql.dsl.api;

abstract class SQL_Collector_WhereOrJoin_MultiMapped_Alias<
	MODEL,
	RESULT,
	ALIAS_JOIN_CONDITION extends ISQLJoin_Condition_MultiMapped_Alias<MODEL,RESULT, ALIAS_JOIN_CONDITION>
	>

		extends SQL_Collector_WhereOrJoin_Alias_Base<
			MODEL,
			RESULT,
			
			// Not really in use but required for moving code to baseclass
			ISQLJoin_Condition_MultiMapped_Named<MODEL, RESULT, Object, Object>,
			
			
			ALIAS_JOIN_CONDITION,
			
			ISQLLogical_And_MultiMapped_Alias<MODEL, RESULT>,
			ISQLLogical_Or_MultiMapped_Alias<MODEL, RESULT>,
			ISQLLogical_AndOr_MultiMapped_Alias<MODEL, RESULT>,

			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>>
		
		implements ISQLLogical_WhereOrJoin_MultiMapped_Alias<MODEL, RESULT, ALIAS_JOIN_CONDITION> {
		
	SQL_Collector_WhereOrJoin_MultiMapped_Alias(Collector_Conditions_Initial<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> last) {
		super(last);
	}

	@Override
	SQL_Collector_Or_Alias<MODEL, RESULT, ISQLLogical_Or_MultiMapped_Alias<MODEL, RESULT>> createAliasOrCollector() {
		return new SQL_Collector_Or_MultiMapped_Alias<>(this);
	}

	@Override
	SQL_Collector_And_Alias<MODEL, RESULT, ISQLLogical_And_MultiMapped_Alias<MODEL, RESULT>> createAliasAndCollector() {
		return new SQL_Collector_And_MultiMapped_Alias<>(this);
	}
}
