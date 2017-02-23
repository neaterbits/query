package com.neaterbits.query.sql.dsl.api;


abstract class Classic_Collector_And_Alias<
				MODEL,
				RESULT,
				
				AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL,RESULT, AND_CLAUSES, IClassicLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>>
				
		extends Collector_And_Alias<
				MODEL,
				RESULT,
				AND_CLAUSES,
				IClassicLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
				IClassicLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
				ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> {

	Classic_Collector_And_Alias(Classic_Collector_WhereOrJoin_Alias_Base<MODEL, RESULT, ?, ?, ?, ?> last) {
		super(last);
	}
	
	Classic_Collector_And_Alias(Collector_Base<MODEL> qe, EConditionsClause conditionsClause) {
		super(qe, conditionsClause);
	}


	@Override
	final Collector_Or_Alias<
				MODEL,
				RESULT,
				IClassicLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
				IClassicLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
				IClassicLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
				ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> 
	
	
		createNestedOrCollector(
				Collector_And_Alias<
					MODEL,
					RESULT,
					AND_CLAUSES,
					IClassicLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
					IClassicLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
					ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> andClauses) {
		
		return new Classic_Collector_Or_NonProcessResult_Alias<MODEL, RESULT>(andClauses, andClauses.getConditionsClause());
	}
}
