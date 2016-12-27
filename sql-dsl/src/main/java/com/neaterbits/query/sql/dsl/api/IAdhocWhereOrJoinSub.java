package com.neaterbits.query.sql.dsl.api;

public interface IAdhocWhereOrJoinSub<MODEL, RESULT, TYPE>
		extends IAdhocWhereOrJoin<MODEL, RESULT, TYPE,
			IAdhocAndOrLogicalClauses<MODEL, RESULT>,
			IAdhocWhereOrJoinSub<MODEL, RESULT, TYPE>>{

}
