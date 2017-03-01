package com.neaterbits.query.sql.dsl.api;

abstract class SQL_Collector_Or_Alias<
				MODEL,
				RESULT,
				OR_CLAUSES extends ISharedLogical_Or_Alias_Base<MODEL, RESULT, OR_CLAUSES, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>>>

		extends Collector_Or_Alias<
				MODEL,
				RESULT,
				OR_CLAUSES,
				ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
				ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> {

	SQL_Collector_Or_Alias(Collector_Conditions_Base<MODEL, RESULT> qe) {
		super(qe);
	}

	SQL_Collector_Or_Alias(SQL_Collector_WhereOrJoin_Alias_Base<MODEL, RESULT, ?, ?, ?, ?> last) {
		super(last);
	}

	@Override
	final Collector_And_Alias<MODEL, RESULT, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> createNestedAndCollector(
			Collector_Or_Alias<MODEL, RESULT, OR_CLAUSES, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> orClauses) {

		return new SQL_Collector_And_NonProcessResult_Alias<>(orClauses);
	}
}
