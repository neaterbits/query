package com.neaterbits.query.sql.dsl.api;


abstract class SQL_Collector_And_Alias<
				MODEL,
				RESULT,
				
				AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL,RESULT, AND_CLAUSES, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>>
				
		extends Collector_And_Alias<
				MODEL,
				RESULT,
				AND_CLAUSES,
				ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
				ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> {

	SQL_Collector_And_Alias(Collector_Conditions_Initial<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> last) {
		super(last);
	}
	
	SQL_Collector_And_Alias(Collector_Conditions_Base<MODEL, RESULT> qe, Void disambiguate) {
		super(qe, disambiguate);
	}


	@Override
	final Collector_Or_Alias<
				MODEL,
				RESULT,
				ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
				ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
				ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> 
	
	
		createNestedOrCollector(
				Collector_And_Alias<
					MODEL,
					RESULT,
					AND_CLAUSES,
					ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
					ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
					ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> andClauses) {
		
		
		return new SQL_Collector_Or_NonProcessResult_Alias<MODEL, RESULT>(andClauses, null);
	}
}
