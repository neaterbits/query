package com.neaterbits.query.sql.dsl.api;

public interface JoinClauseTable<MODEL, RESULT>
		
		extends	IClassicWhereClauseBuilderTable<MODEL, RESULT> {


	<LEFT, RIGHT> JoinConditionTable<MODEL, RESULT, LEFT, RIGHT> innerJoin(Class<LEFT> leftType, Class<RIGHT> rightType);
	
	<LEFT, RIGHT> JoinConditionTable<MODEL, RESULT, LEFT, RIGHT> leftJoin(Class<LEFT> leftType, Class<RIGHT> rightType);
	
}
