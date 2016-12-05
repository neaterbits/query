package com.neaterbits.query.sql.dsl.api;

public interface AndOrLogicalClausesTableSingle<MODEL, RESULT> 
	extends AndClausesTableSingle<MODEL, RESULT>, OrClausesTableSingle<MODEL, RESULT>, LogicalClauses<MODEL, RESULT> {

}
