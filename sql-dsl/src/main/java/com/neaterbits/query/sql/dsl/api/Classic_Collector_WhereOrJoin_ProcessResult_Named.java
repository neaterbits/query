package com.neaterbits.query.sql.dsl.api;

final class Classic_Collector_WhereOrJoin_ProcessResult_Named<MODEL, RESULT>

		extends Classic_Collector_WhereOrJoin_Named<
			MODEL,
			RESULT,
			IClassicJoin_Condition_ProcessResult_Named<MODEL,RESULT,Object,Object>>

		implements IClassicLogical_WhereOrJoin_ProcessResult_Named<MODEL, RESULT> {

	Classic_Collector_WhereOrJoin_ProcessResult_Named(BaseQueryEntity<MODEL> last) {
		super(last);
	}
}
