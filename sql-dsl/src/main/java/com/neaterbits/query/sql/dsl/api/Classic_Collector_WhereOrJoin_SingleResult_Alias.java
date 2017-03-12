package com.neaterbits.query.sql.dsl.api;

final class Classic_Collector_WhereOrJoin_SingleResult_Alias<MODEL,  RESULT> 

	extends SQL_Collector_WhereOrJoin_SingleResult_Alias<MODEL, RESULT, IClassicJoin_Condition_SingleResult_Alias<MODEL,RESULT>>

	implements IClassicLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT> {

	Classic_Collector_WhereOrJoin_SingleResult_Alias(Collector_Base<MODEL> last) {
		super(last);
	}
}
