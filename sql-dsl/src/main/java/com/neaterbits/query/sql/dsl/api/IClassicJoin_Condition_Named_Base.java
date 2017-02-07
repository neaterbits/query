package com.neaterbits.query.sql.dsl.api;

public interface IClassicJoin_Condition_Named_Base<

				MODEL,
				RESULT,
				LEFT,
				RIGHT,
				JOIN_CONDITION extends IClassicJoin_Condition_Named_Base<MODEL, RESULT, LEFT, RIGHT, JOIN_CONDITION>>

		extends IClassicJoin_Condition,
				ISharedJoin_Condition_Named<MODEL, RESULT, LEFT, RIGHT, JOIN_CONDITION> {
	
}