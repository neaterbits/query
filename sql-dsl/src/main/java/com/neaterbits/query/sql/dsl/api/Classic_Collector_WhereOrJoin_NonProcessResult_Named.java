package com.neaterbits.query.sql.dsl.api;

final class Classic_Collector_WhereOrJoin_NonProcessResult_Named<MODEL, RESULT>

	extends Classic_Collector_WhereOrJoin_Named<
				MODEL,
				RESULT,
				IClassicJoin_Condition_NonProcessResult_Named<MODEL, RESULT, Object, Object>> {

	Classic_Collector_WhereOrJoin_NonProcessResult_Named(BaseQueryEntity<MODEL> last) {
		super(last);
	}
}


