package com.neaterbits.query.sql.dsl.api;

public interface IAdhocWhere<MODEL, RESULT> 
		extends ISharedWhereClausesTableValue<
				MODEL,
				RESULT,
				IAdhocAndOrLogicalClauses<MODEL,RESULT>> {

}
