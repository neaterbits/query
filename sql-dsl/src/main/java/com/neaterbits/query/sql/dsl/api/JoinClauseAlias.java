package com.neaterbits.query.sql.dsl.api;

public interface JoinClauseAlias<MODEL, RESULT> extends JoinClause {

	JoinConditionAlias<MODEL, RESULT> innerJoin(Object left, Object right);
	
	JoinConditionAlias<MODEL, RESULT> leftJoin(Object left, Object right);
	
}
