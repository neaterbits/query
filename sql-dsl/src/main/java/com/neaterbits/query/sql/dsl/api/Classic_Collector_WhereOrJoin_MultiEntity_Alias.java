package com.neaterbits.query.sql.dsl.api;

final class Classic_Collector_WhereOrJoin_MultiEntity_Alias<MODEL, RESULT> 

	extends SQL_Collector_WhereOrJoin_MultiEntity_Alias<MODEL, RESULT>

	implements IClassicJoin_Alias<MODEL, RESULT, ISQLJoin_Condition_MultiEntity_Alias<MODEL,RESULT>>{

	Classic_Collector_WhereOrJoin_MultiEntity_Alias(Collector_Base<MODEL> last) {
		super(last);
	}
}
