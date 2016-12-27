package com.neaterbits.query.sql.dsl.api;

public interface IAdhocWhereOrJoin<
					MODEL,
					RESULT,
					TYPE,
					AND_OR_CLAUSES extends IAdhocAndOrLogicalClauses<MODEL,RESULT>,
					WHERE_OR_JOIN extends IAdhocWhereOrJoin<MODEL, RESULT, TYPE, AND_OR_CLAUSES, WHERE_OR_JOIN>>

			extends IAdhocWhere<MODEL, RESULT, TYPE, AND_OR_CLAUSES>,
			
					IAdhocJoin<MODEL, RESULT, TYPE, WHERE_OR_JOIN> {

}
