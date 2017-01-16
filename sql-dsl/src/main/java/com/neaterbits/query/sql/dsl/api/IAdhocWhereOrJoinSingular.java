package com.neaterbits.query.sql.dsl.api;

public interface IAdhocWhereOrJoinSingular<MODEL, RESULT, ENTITY> 
	extends IAdhocWhereOrJoin<MODEL, RESULT, ENTITY, IAdhocAndOrLogicalClausesSingular<MODEL, RESULT, ENTITY>, IAdhocWhereOrJoinSingular<MODEL, RESULT, ENTITY>>,

			IAdhocEndClauseSingular<MODEL, RESULT> {

	
}


