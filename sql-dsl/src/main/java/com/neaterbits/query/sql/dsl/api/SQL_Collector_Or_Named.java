package com.neaterbits.query.sql.dsl.api;

abstract class SQL_Collector_Or_Named<
			MODEL,
			RESULT,
			OR_CLAUSES extends ISharedLogical_Or_Named_All<MODEL, RESULT, OR_CLAUSES, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>
			>
			extends Collector_Or_Named<
					MODEL,
					RESULT,
					OR_CLAUSES,
					ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
					ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
					ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> {


	SQL_Collector_Or_Named(Collector_Conditions_Base<MODEL, RESULT> qe) {
		super(qe);
	}

	SQL_Collector_Or_Named(SQL_Collector_WhereOrJoin_Named_Base<MODEL, RESULT, ?, ?, ?, ?> last) {
		super(last);
	}

	@Override
	final Collector_And_Named<MODEL, RESULT, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> createNestedAndCollector(
			Collector_Or_Named<MODEL, RESULT, OR_CLAUSES, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> andClauses) {

		return new SQL_Collector_And_NonProcessResult_Named<MODEL, RESULT>(andClauses);
	}
}
