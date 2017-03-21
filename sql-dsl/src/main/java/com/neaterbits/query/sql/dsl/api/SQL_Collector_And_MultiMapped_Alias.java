package com.neaterbits.query.sql.dsl.api;

final class SQL_Collector_And_MultiMapped_Alias<MODEL, RESULT>

	extends SQL_Collector_And_Alias<MODEL, RESULT, ISQLLogical_And_MultiMapped_Alias<MODEL, RESULT>>
	
	implements ISQLLogical_And_MultiMapped_Alias<MODEL, RESULT> {
	
	SQL_Collector_And_MultiMapped_Alias(Collector_Conditions_Initial<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> last) {
		super(last);
	}

	SQL_Collector_And_MultiMapped_Alias(Collector_Conditions_Intermediate<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> qe) {
		super(qe);
	}
}
