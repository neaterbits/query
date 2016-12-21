package com.neaterbits.query.sql.dsl.api;

public interface IClassicJoinClauseAlias<MODEL, RESULT> extends JoinClause {

	IClassicJoinConditionAlias<MODEL, RESULT> innerJoin(Object left, Object right);
	
	IClassicJoinConditionAlias<MODEL, RESULT> leftJoin(Object left, Object right);
	
}
