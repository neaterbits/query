package com.neaterbits.query.sql.dsl.api;

final class Classic_Collector_WhereOrJoin_MultiEntity_Alias<MODEL, RESULT> 

	extends SQL_Collector_WhereOrJoin_MultiEntity_Alias<MODEL, RESULT, IClassicJoin_Condition_MultiEntity_Alias<MODEL, RESULT>>

	implements IClassicLogical_WhereOrJoin_MultiEntity_Alias<MODEL, RESULT>{

	Classic_Collector_WhereOrJoin_MultiEntity_Alias(Collector_Conditions_Initial<MODEL, RESULT, Void> last) {
		super(last);
	}
}
