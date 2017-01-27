package com.neaterbits.query.sql.dsl.api;

public interface IAdhocWhereOrJoinSubJoin<MODEL, RESULT, TYPE>
		extends IAdhoc_Where_Or_Join<MODEL, RESULT, TYPE, IAdhocLogical_And_Or<MODEL,RESULT, TYPE>,
				IAdhocWhereOrJoinSubJoin<MODEL, RESULT, TYPE>> {

}
