package com.neaterbits.query.sql.dsl.api;

final class SQL_Collector_And_MultiMapped_Named<MODEL, RESULT>

		extends SQL_Collector_And_Named<MODEL, RESULT, ISQLLogical_And_MultiMapped_Named<MODEL, RESULT>>

		implements ISQLLogical_And_MultiMapped_Named<MODEL, RESULT> {
		
	SQL_Collector_And_MultiMapped_Named(Collector_Conditions_Initial<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> last) {
		super(last);
	}

	SQL_Collector_And_MultiMapped_Named(Collector_Conditions_Intermediate<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> qe) {
		super(qe);
	}
}
