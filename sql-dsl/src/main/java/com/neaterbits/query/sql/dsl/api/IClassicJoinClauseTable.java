package com.neaterbits.query.sql.dsl.api;

public interface IClassicJoinClauseTable<MODEL, RESULT> {

	<LEFT, RIGHT> JoinConditionTable<MODEL, RESULT, LEFT, RIGHT> innerJoin(Class<LEFT> leftType, Class<RIGHT> rightType);
	
	<LEFT, RIGHT> JoinConditionTable<MODEL, RESULT, LEFT, RIGHT> leftJoin(Class<LEFT> leftType, Class<RIGHT> rightType);
	
}
