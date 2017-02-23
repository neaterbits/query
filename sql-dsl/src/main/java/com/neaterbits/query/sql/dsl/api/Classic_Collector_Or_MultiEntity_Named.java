package com.neaterbits.query.sql.dsl.api;

public class Classic_Collector_Or_MultiEntity_Named<MODEL, RESULT>

	extends Classic_Collector_Or_Named<MODEL, RESULT, IClassicLogical_Or_MultiEntity_Named<MODEL, RESULT>>
	
	implements IClassicLogical_Or_MultiEntity_Named<MODEL, RESULT> {
	
	Classic_Collector_Or_MultiEntity_Named(Classic_Collector_WhereOrJoin_Named_Base<MODEL, RESULT, ?, ?, ?, ?> last) {
		super(last);
	}
}
