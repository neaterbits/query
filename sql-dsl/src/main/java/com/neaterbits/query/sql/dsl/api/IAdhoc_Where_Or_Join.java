package com.neaterbits.query.sql.dsl.api;

public interface IAdhoc_Where_Or_Join<
					MODEL,
					RESULT,
					ENTITY,
					AND_OR_CLAUSES extends IAdhocLogical_And_Or<MODEL,RESULT, ENTITY>,
					WHERE_OR_JOIN extends IAdhoc_Where_Or_Join<MODEL, RESULT, ENTITY, AND_OR_CLAUSES, WHERE_OR_JOIN>>

			extends IAdhocWhere<MODEL, RESULT, ENTITY, AND_OR_CLAUSES>,
			
					IAdhocJoin<MODEL, RESULT, ENTITY, WHERE_OR_JOIN> {

}
