package com.neaterbits.query.sql.dsl.api;

public interface IAdhocWhereOrJoin<
					MODEL,
					RESULT,
					ENTITY,
					AND_OR_CLAUSES extends IAdhocAndOrLogicalClauses<MODEL,RESULT, ENTITY>,
					WHERE_OR_JOIN extends IAdhocWhereOrJoin<MODEL, RESULT, ENTITY, AND_OR_CLAUSES, WHERE_OR_JOIN>>

			extends IAdhocWhere<MODEL, RESULT, ENTITY, AND_OR_CLAUSES>,
			
					IAdhocJoin<MODEL, RESULT, ENTITY, WHERE_OR_JOIN> {

}
