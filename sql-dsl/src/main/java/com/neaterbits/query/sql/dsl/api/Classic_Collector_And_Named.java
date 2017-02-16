package com.neaterbits.query.sql.dsl.api;

abstract class Classic_Collector_And_Named<
				MODEL,
				RESULT,
				AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL,RESULT, AND_CLAUSES, IClassicLogical_Or_NonProcessResult_Named<MODEL, RESULT>>>

	extends Collector_And_Named<
				MODEL,
				RESULT,
				AND_CLAUSES,
				IClassicLogical_And_NonProcessResult_Named<MODEL, RESULT>,
				IClassicLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
				ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> {

	Classic_Collector_And_Named(BaseQueryEntity<MODEL> qe) {
		super(qe);
	}

	Classic_Collector_And_Named(Classic_Collector_WhereOrJoin_Named_Base<MODEL, RESULT, ?, ?, ?, ?> last) {
		super(last);
	}

	
	@Override
	final Collector_Or_Named<
				MODEL,
				RESULT,
				IClassicLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
				IClassicLogical_And_NonProcessResult_Named<MODEL, RESULT>,
				IClassicLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
				ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> 
	
	
		createNestedOrCollector(
				Collector_And_Named<
					MODEL,
					RESULT,
					AND_CLAUSES,
					IClassicLogical_And_NonProcessResult_Named<MODEL, RESULT>,
					IClassicLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
					ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> andClauses) {
		
		return new Classic_Collector_Or_NonProcessResult_Named<MODEL, RESULT>(andClauses);
	}
}
