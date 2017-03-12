package com.neaterbits.query.sql.dsl.api;

final class Classic_Collector_WhereOrJoin_MultiMapped_Alias<MODEL, RESULT>
	extends SQL_Collector_WhereOrJoin_MultiMapped_Alias<MODEL, RESULT, IClassicJoin_Condition_MultiMapped_Alias<MODEL, RESULT>> 


	implements IClassicLogical_WhereOrJoin_MultiMapped_Alias<MODEL, RESULT>
{

	
	
	Classic_Collector_WhereOrJoin_MultiMapped_Alias(Collector_Base<MODEL> last) {
		super(last);
	}
}
