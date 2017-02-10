package com.neaterbits.query.sql.dsl.api;

public interface IClassicJoin_Condition_MultiEntity_Named<MODEL, RESULT, LEFT, RIGHT>

		extends IClassicJoin_Condition_Named_Base<MODEL, RESULT, LEFT, RIGHT, IClassicJoin_Condition_MultiEntity_Named<MODEL, RESULT, LEFT, RIGHT>>,
		
		
		IClassicLogical_Where_MultiEntity_Named<MODEL, RESULT> {
		
}
