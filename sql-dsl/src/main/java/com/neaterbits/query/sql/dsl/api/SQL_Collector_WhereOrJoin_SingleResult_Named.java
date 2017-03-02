package com.neaterbits.query.sql.dsl.api;

final class SQL_Collector_WhereOrJoin_SingleResult_Named<MODEL, RESULT>

	extends SQL_Collector_WhereOrJoin_Named_Base<
				MODEL,
				RESULT,
				ISQLJoin_Condition_SingleResult_Named<MODEL, RESULT, Object, Object>,
				ISQLJoin_Condition_SingleResult_Alias<MODEL, RESULT>,
				
	
				ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
				ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>
				> 

		implements 
				ISQLLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT>,
				ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>{

	SQL_Collector_WhereOrJoin_SingleResult_Named(Collector_Base<MODEL> last) {
		super(last);
	}

	@Override
	SQL_Collector_Or_Named<MODEL, RESULT, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>> createNamedOrCollector() {
		return new SQL_Collector_Or_NonProcessResult_Named<>(this);
	}

	@Override
	SQL_Collector_And_Named<MODEL, RESULT, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>> createNamedAndCollector() {
		return new SQL_Collector_And_NonProcessResult_Named<>(this);
	}

	@Override
	public ISharedFunctions_Named_Initial<
			MODEL,
			RESULT,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>, 
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_String_All<MODEL, RESULT, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>>
	
			and() {

		return andNamed();
	}
}


