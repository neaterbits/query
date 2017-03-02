package com.neaterbits.query.sql.dsl.api;

final class SQL_Collector_And_NonProcessResult_Alias<MODEL, RESULT>

		extends SQL_Collector_And_Alias<MODEL, RESULT, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>>

		implements ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT> {

	SQL_Collector_And_NonProcessResult_Alias(
			SQL_Collector_WhereOrJoin_Alias_Base<MODEL, RESULT, ?, ?, ?, ?, ?> last) {
		super(last);
	}

	SQL_Collector_And_NonProcessResult_Alias(Collector_Conditions_Base<MODEL, RESULT> qe) {
		super(qe);
	}
}
