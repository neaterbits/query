package com.neaterbits.query.sql.dsl.api;

public interface IClassicJoinClauseTable<MODEL, RESULT> {

	<LEFT, RIGHT> IClassicJoinConditionTable<MODEL, RESULT, LEFT, RIGHT> innerJoin(Class<LEFT> leftType, Class<RIGHT> rightType);
	
	<LEFT, RIGHT> IClassicJoinConditionTable<MODEL, RESULT, LEFT, RIGHT> leftJoin(Class<LEFT> leftType, Class<RIGHT> rightType);
	
}
