package com.neaterbits.query.sql.dsl.api;

public interface IAdhocWhereOrJoinSub<MODEL, RESULT, ENTITY>
		extends IAdhocWhereOrJoin<MODEL, RESULT, ENTITY,
			IAdhocAndOrLogicalClauses<MODEL, RESULT, ENTITY>,
			IAdhocWhereOrJoinSub<MODEL, RESULT, ENTITY>>{

}
