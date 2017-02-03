package com.neaterbits.query.sql.dsl.api;

public interface IClassicJoin_Alias<
			MODEL,
			RESULT,
			JOIN_CONDITION extends IClassicJoin_Condition_Alias_Base<MODEL, RESULT, JOIN_CONDITION>>

		extends IShared_Join {

	JOIN_CONDITION innerJoin(Object left, Object right);
	
	JOIN_CONDITION leftJoin(Object left, Object right);
	
}
