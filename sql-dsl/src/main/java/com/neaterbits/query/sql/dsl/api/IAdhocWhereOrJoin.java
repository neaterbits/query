package com.neaterbits.query.sql.dsl.api;

public interface IAdhocWhereOrJoin<MODEL, RESULT, TYPE, AND_OR_CLAUSES extends IAdhocAndOrLogicalClauses<MODEL,RESULT>
		>
			extends IAdhocWhere<MODEL, RESULT, TYPE, AND_OR_CLAUSES> {

}
