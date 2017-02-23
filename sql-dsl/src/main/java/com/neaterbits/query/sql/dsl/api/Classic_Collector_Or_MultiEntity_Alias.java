package com.neaterbits.query.sql.dsl.api;

final class Classic_Collector_Or_MultiEntity_Alias<MODEL, RESULT>
		extends Classic_Collector_Or_Alias<MODEL, RESULT, IClassicLogical_Or_MultiEntity_Alias<MODEL, RESULT>>

		implements IClassicLogical_Or_MultiEntity_Alias<MODEL, RESULT> {

	Classic_Collector_Or_MultiEntity_Alias(Collector_Conditions_Base<MODEL, RESULT> qe) {
		super(qe);
	}

}
