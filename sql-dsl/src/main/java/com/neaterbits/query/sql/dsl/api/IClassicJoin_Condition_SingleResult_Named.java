package com.neaterbits.query.sql.dsl.api;

public interface IClassicJoin_Condition_SingleResult_Named<MODEL, RESULT, LEFT, RIGHT>

			extends IClassicJoin_Condition_Named_Base<MODEL, RESULT, LEFT, RIGHT, IClassicJoin_Condition_SingleResult_Named<MODEL, RESULT, LEFT, RIGHT>>,
			
			
			IClassicLogical_Where_SingleResult_Named<MODEL, RESULT> {

}
