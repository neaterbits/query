package com.neaterbits.query.sql.dsl.api;

public interface IAdhocWhereOrJoinSubJoin<MODEL, RESULT, TYPE>
		extends IAdhocWhereOrJoin<MODEL, RESULT, TYPE, IAdhocAndOrLogicalClauses<MODEL,RESULT, TYPE>,
				IAdhocWhereOrJoinSubJoin<MODEL, RESULT, TYPE>> {

}
