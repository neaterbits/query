package com.neaterbits.query.sql.dsl.api;

public interface ISQLJoin_Alias<
			MODEL,
			RESULT,
			JOIN_CONDITION extends ISQLJoin_Condition_Alias_Base<MODEL, RESULT, JOIN_CONDITION>>

		extends IShared_Join {

	JOIN_CONDITION innerJoin(Object left, Object right);
	
	JOIN_CONDITION leftJoin(Object left, Object right);
	
}
