package com.neaterbits.query.sql.dsl.api;

final class Classic_Collector_And_NonProcessResult_Named<MODEL, RESULT>

		extends Classic_Collector_And_Named<MODEL, RESULT, IClassicLogical_And_NonProcessResult_Named<MODEL, RESULT>>
		
		implements IClassicLogical_And_NonProcessResult_Named<MODEL, RESULT> {
	
	Classic_Collector_And_NonProcessResult_Named(Classic_Collector_WhereOrJoin_Named_Base<MODEL, RESULT, ?, ?, ?, ?> last) {
		super(last);
	}
	
	Classic_Collector_And_NonProcessResult_Named(BaseQueryEntity<MODEL> qe) {
		super(qe);
	}
}
