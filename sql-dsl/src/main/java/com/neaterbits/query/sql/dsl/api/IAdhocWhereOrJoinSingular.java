package com.neaterbits.query.sql.dsl.api;

public interface IAdhocWhereOrJoinSingular<MODEL, RESULT, TYPE> 
	extends IAdhocWhereOrJoin<MODEL, RESULT, TYPE, IAdhocAndOrLogicalClausesSingular<MODEL, RESULT>, IAdhocWhereOrJoinSingular<MODEL, RESULT, TYPE>>,

			IAdhocEndClauseSingular<MODEL, RESULT> {

	
}


