package com.neaterbits.query.sql.dsl.api;

final class Classic_Collector_Or_NonProcessResult_Named<MODEL, RESULT>

	extends Classic_Collector_Or_Named<MODEL, RESULT, IClassicLogical_Or_NonProcessResult_Named<MODEL, RESULT>>

	implements IClassicLogical_Or_NonProcessResult_Named<MODEL, RESULT> {


	Classic_Collector_Or_NonProcessResult_Named(BaseQueryEntity<MODEL> qe) {
		super(qe);
	}
}
