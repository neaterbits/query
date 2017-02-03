package com.neaterbits.query.sql.dsl.api;

public interface IClassicJoin_Condition_Named<MODEL, RESULT, LEFT, RIGHT>
		extends IClassicJoin_Condition,
				ISharedJoin_Condition_Named<MODEL, RESULT, LEFT, RIGHT, IClassicJoin_Condition_Named<MODEL, RESULT, LEFT, RIGHT>>,
				IClassicLogical_Where_NonProcessResult_Named<MODEL, RESULT> {
	
}