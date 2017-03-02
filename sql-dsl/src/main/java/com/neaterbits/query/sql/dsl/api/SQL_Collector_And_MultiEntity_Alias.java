package com.neaterbits.query.sql.dsl.api;

final class SQL_Collector_And_MultiEntity_Alias<MODEL, RESULT>

		extends SQL_Collector_And_Alias<MODEL, RESULT, ISQLLogical_And_MultiEntity_Alias<MODEL, RESULT>>

		implements ISQLLogical_And_MultiEntity_Alias<MODEL, RESULT> {

	SQL_Collector_And_MultiEntity_Alias(SQL_Collector_WhereOrJoin_Alias_Base<MODEL, RESULT, ?, ?, ?, ?, ?> last) {
		super(last);
	}
}
