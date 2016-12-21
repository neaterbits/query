package com.neaterbits.query.sql.dsl.api;

public interface AndOrLogicalClausesTableSingle<MODEL, RESULT> 
	extends IClassicSingleAndClausesTable<MODEL, RESULT>, IClassicSingleOrClausesTable<MODEL, RESULT>, ISharedLogicalClauses<MODEL, RESULT> {

}
