package com.neaterbits.query.sql.dsl.api;

abstract class SQL_Collector_WhereOrJoin_MultiEntity_Alias<
			MODEL,
			RESULT,
			JOIN_CONDITION extends ISQLJoin_Condition_MultiEntity_Alias<MODEL, RESULT, JOIN_CONDITION>>

		extends
		SQL_Collector_WhereOrJoin_Alias_Base<
				MODEL,
				RESULT,
				
				
				ISQLJoin_Condition_MultiEntity_Named<MODEL, RESULT, Object, Object>,
				
				JOIN_CONDITION,

				ISQLLogical_And_MultiEntity_Alias<MODEL, RESULT>,
				ISQLLogical_Or_MultiEntity_Alias<MODEL, RESULT>,
				ISQLLogical_AndOr_MultiEntity_Alias<MODEL, RESULT>,
				
				ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>>

		implements ISQLLogical_WhereOrJoin_MultiEntity_Alias<MODEL, RESULT, JOIN_CONDITION> {

	SQL_Collector_WhereOrJoin_MultiEntity_Alias(Collector_Conditions_Initial<MODEL, RESULT, Void> last) {
		super(last.getThisInitial());
	}

	@Override
	Collector_Or_Alias<
		MODEL,
		RESULT,
		ISQLLogical_Or_MultiEntity_Alias<MODEL, RESULT>,
		ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
		ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
		ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>>
	
		createAliasOrCollector() {
		
		return new SQL_Collector_Or_MultiEntity_Alias<>(this);
	}

	@Override
	Collector_And_Alias<
		MODEL,
		RESULT,
		ISQLLogical_And_MultiEntity_Alias<MODEL, RESULT>,
		ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
		ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
		ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>>
	
		createAliasAndCollector() {
		
		return new SQL_Collector_And_MultiEntity_Alias<>(this);
	}
}
