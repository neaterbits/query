package com.neaterbits.query.sql.dsl.api;

final class SQL_Collector_Or_MultiMapped_Named<MODEL, RESULT>
	extends SQL_Collector_Or_Named<MODEL, RESULT, ISQLLogical_Or_MultiMapped_Named<MODEL, RESULT>>

	implements ISQLLogical_Or_MultiMapped_Named<MODEL, RESULT> {

	SQL_Collector_Or_MultiMapped_Named(SQL_Collector_WhereOrJoin_Named_Base<MODEL, RESULT, ?, ?, ?, ?, ?> last) {
		super(last);
	}
}
