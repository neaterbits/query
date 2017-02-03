package com.neaterbits.query.sql.dsl.api;

public interface IClassicLogical_Join_Named<MODEL, RESULT> {

	<LEFT, RIGHT> IClassicJoinConditionNamed<MODEL, RESULT, LEFT, RIGHT> innerJoin(Class<LEFT> leftType, Class<RIGHT> rightType);
	
	<LEFT, RIGHT> IClassicJoinConditionNamed<MODEL, RESULT, LEFT, RIGHT> leftJoin(Class<LEFT> leftType, Class<RIGHT> rightType);
	
}
