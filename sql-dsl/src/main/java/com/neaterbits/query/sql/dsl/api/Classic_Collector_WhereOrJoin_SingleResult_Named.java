package com.neaterbits.query.sql.dsl.api;

final class Classic_Collector_WhereOrJoin_SingleResult_Named<MODEL, RESULT>

	extends SQL_Collector_WhereOrJoin_SingleResult_Named<MODEL, RESULT, IClassicJoin_Condition_SingleResult_Alias<MODEL,RESULT>> {

	Classic_Collector_WhereOrJoin_SingleResult_Named(Collector_Base<MODEL> last) {
		super(last);
	}
}
