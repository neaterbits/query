package com.neaterbits.query.sql.dsl.api;

final class Classic_Collector_WhereOrJoin_NonProcessResult_Alias<MODEL, RESULT>

		extends Classic_Collector_WhereOrJoin_Alias_Base<
			MODEL,
			RESULT,
			IClassicJoin_Condition_NonProcessResult_Alias<MODEL, RESULT>> 

	implements IClassicLogical_WhereOrJoin_NonProcessResult_Alias<MODEL, RESULT> {

	Classic_Collector_WhereOrJoin_NonProcessResult_Alias(BaseQueryEntity<MODEL> last) {
		super(last);
	}
}
