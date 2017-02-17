package com.neaterbits.query.sql.dsl.api;

public class Classic_Collector_Or_MultiEntity_Named<MODEL, RESULT>

	extends Classic_Collector_Or_Named<MODEL, RESULT, IClassicLogical_Or_MultiEntity_Named<MODEL, RESULT>>
	
	implements IClassicLogical_Or_MultiEntity_Named<MODEL, RESULT> {
	
	
	Classic_Collector_Or_MultiEntity_Named(Collector_Base<MODEL> qe) {
		super(qe);
	}
}
