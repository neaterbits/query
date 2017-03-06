package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

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
				ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL, RESULT>,
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
	public ISharedFunctions_Transform_Initial_Named<
			MODEL,
			RESULT,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short,ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>, 
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer,ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>, 
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_String_All<MODEL, RESULT, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>>
	
			and() {

		return andNamed();
	}

	@Override
	public ISharedFunctions_Transform_Initial_Named<
			MODEL,
			RESULT,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			ISharedCondition_Comparable_String_All<MODEL, RESULT, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>>
	
			or() {

		return orNamed();
	}
}


