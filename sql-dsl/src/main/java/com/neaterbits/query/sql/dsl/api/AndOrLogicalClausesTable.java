package com.neaterbits.query.sql.dsl.api;

public interface AndOrLogicalClausesTable<MODEL, RESULT>
	extends AndClausesTable<MODEL, RESULT>, OrClausesTable<MODEL, RESULT>, LogicalClauses<MODEL, RESULT> {

}
