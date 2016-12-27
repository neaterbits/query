package com.neaterbits.query.sql.dsl.api;

public interface IAdhocJoinSubOrCondition<MODEL, RESULT, LEFT, RIGHT>
		extends IAdhocJoinSub<MODEL, RESULT, LEFT, RIGHT>,

				IAdhocWhereOrJoin<MODEL, RESULT, RIGHT, IAdhocAndOrLogicalClauses<MODEL,RESULT>,
				IAdhocWhereOrJoinSub<MODEL,RESULT, RIGHT>> {
	
	

}
