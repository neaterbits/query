package com.neaterbits.query.sql.dsl.api;

public interface AndOrLogicalClausesAlias<MODEL, RESULT>
		extends AndClausesAlias<MODEL, RESULT>, OrClausesAlias<MODEL, RESULT>, LogicalClauses<MODEL, RESULT> {

}
