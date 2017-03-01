package com.neaterbits.query.sql.dsl.api;

public interface ISQLJoin_Condition_SingleResult_Named<MODEL, RESULT, LEFT, RIGHT>

			extends ISQLJoin_Condition_Named_Base<MODEL, RESULT, LEFT, RIGHT, ISQLJoin_Condition_SingleResult_Named<MODEL, RESULT, LEFT, RIGHT>>,
			
			
			ISQLLogical_Where_SingleResult_Named<MODEL, RESULT> {

}
