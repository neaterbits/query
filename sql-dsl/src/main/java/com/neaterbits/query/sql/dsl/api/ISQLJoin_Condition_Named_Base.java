package com.neaterbits.query.sql.dsl.api;

public interface ISQLJoin_Condition_Named_Base<

				MODEL,
				RESULT,
				LEFT,
				RIGHT,
				JOIN_CONDITION extends ISQLJoin_Condition_Named_Base<MODEL, RESULT, LEFT, RIGHT, JOIN_CONDITION>>

		extends ISQLJoin_Condition,
				ISharedJoin_Condition_Named<MODEL, RESULT, LEFT, RIGHT, JOIN_CONDITION> {
	
}