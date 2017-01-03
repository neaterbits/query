package com.neaterbits.query.sql.dsl.api;

public interface IClassicJoinClauseAlias<MODEL, RESULT> extends ISharedJoinClause {

	IClassicJoinConditionAlias<MODEL, RESULT> innerJoin(Object left, Object right);
	
	IClassicJoinConditionAlias<MODEL, RESULT> leftJoin(Object left, Object right);
	
}
