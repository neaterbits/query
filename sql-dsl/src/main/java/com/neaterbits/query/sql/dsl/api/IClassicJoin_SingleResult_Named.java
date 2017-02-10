package com.neaterbits.query.sql.dsl.api;

public interface IClassicJoin_SingleResult_Named<MODEL, RESULT> extends IClassicJoin_Named_Base<MODEL, RESULT> {

	<LEFT, RIGHT> IClassicJoin_Condition_SingleResult_Named<MODEL, RESULT, LEFT, RIGHT> innerJoin(Class<LEFT> leftType, Class<RIGHT> rightType);
	
	<LEFT, RIGHT> IClassicJoin_Condition_SingleResult_Named<MODEL, RESULT, LEFT, RIGHT> leftJoin(Class<LEFT> leftType, Class<RIGHT> rightType);
}
