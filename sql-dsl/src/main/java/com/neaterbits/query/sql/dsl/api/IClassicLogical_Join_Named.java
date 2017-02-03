package com.neaterbits.query.sql.dsl.api;

public interface IClassicLogical_Join_Named<MODEL, RESULT> {

	<LEFT, RIGHT> IClassicJoin_Condition_Named<MODEL, RESULT, LEFT, RIGHT> innerJoin(Class<LEFT> leftType, Class<RIGHT> rightType);
	
	<LEFT, RIGHT> IClassicJoin_Condition_Named<MODEL, RESULT, LEFT, RIGHT> leftJoin(Class<LEFT> leftType, Class<RIGHT> rightType);
	
}
