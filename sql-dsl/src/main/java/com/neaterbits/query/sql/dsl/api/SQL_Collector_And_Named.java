package com.neaterbits.query.sql.dsl.api;

abstract class SQL_Collector_And_Named<
				MODEL,
				RESULT,
				AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL,RESULT, AND_CLAUSES, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>>

	extends Collector_And_Named<
				MODEL,
				RESULT,
				AND_CLAUSES,
				ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
				ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> {

	SQL_Collector_And_Named(Collector_Conditions_Initial<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> last) {
		super(last);
	}
	
	SQL_Collector_And_Named(Collector_Conditions_Intermediate<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> qe) {
		super(qe);
	}

	@Override
	final Collector_Or_Named<
				MODEL,
				RESULT,
				ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
				ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
				ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> 
	
	
		createNestedOrCollector(
				Collector_And_Named<
					MODEL,
					RESULT,
					AND_CLAUSES,
					ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
					ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
					ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> andClauses) {
		
		return new SQL_Collector_Or_NonProcessResult_Named<MODEL, RESULT>(andClauses, null);
	}
}
