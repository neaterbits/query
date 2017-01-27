package com.neaterbits.query.sql.dsl.api;

public interface IAdhocWhereOrJoinSub<MODEL, RESULT, ENTITY>
		extends IAdhoc_Where_Or_Join<MODEL, RESULT, ENTITY,
			IAdhocLogical_And_Or<MODEL, RESULT, ENTITY>,
			IAdhocWhereOrJoinSub<MODEL, RESULT, ENTITY>>{

}
