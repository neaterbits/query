package com.neaterbits.query.sql.dsl.api;

abstract class Classic_Collector_Or_Alias<
				MODEL,
				RESULT,
				OR_CLAUSES extends ISharedLogical_Or_Alias_Base<MODEL, RESULT, OR_CLAUSES, IClassicLogical_And_NonProcessResult_Alias<MODEL, RESULT>>>

		extends Collector_Or_Alias<
				MODEL,
				RESULT,
				OR_CLAUSES,
				IClassicLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
				IClassicLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
				ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> {

	Classic_Collector_Or_Alias(Collector_Base<MODEL> qe, EConditionsClause conditionsClause) {
		super(qe, conditionsClause);
	}

	Classic_Collector_Or_Alias(Classic_Collector_WhereOrJoin_Alias_Base<MODEL, RESULT, ?, ?, ?, ?> last) {
		super(last);
	}

	@Override
	final Collector_And_Alias<MODEL, RESULT, IClassicLogical_And_NonProcessResult_Alias<MODEL, RESULT>, IClassicLogical_And_NonProcessResult_Alias<MODEL, RESULT>, IClassicLogical_Or_NonProcessResult_Alias<MODEL, RESULT>, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> createNestedAndCollector(
			Collector_Or_Alias<MODEL, RESULT, OR_CLAUSES, IClassicLogical_And_NonProcessResult_Alias<MODEL, RESULT>, IClassicLogical_Or_NonProcessResult_Alias<MODEL, RESULT>, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> orClauses) {

		return new Classic_Collector_And_NonProcessResult_Alias<>(orClauses, orClauses.getConditionsClause());
	}
}
