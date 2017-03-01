package com.neaterbits.query.sql.dsl.api;

final class SQL_Collector_Or_NonProcessResult_Named<MODEL, RESULT>

	extends SQL_Collector_Or_Named<MODEL, RESULT, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>

	implements ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT> {


	SQL_Collector_Or_NonProcessResult_Named(Collector_Conditions_Base<MODEL, RESULT> qe) {
		super(qe);
	}
}
