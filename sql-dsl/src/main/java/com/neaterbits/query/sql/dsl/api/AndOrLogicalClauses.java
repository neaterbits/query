package com.neaterbits.query.sql.dsl.api;

public interface AndOrLogicalClauses<MODEL, RESULT> extends AndClauses<MODEL, RESULT>, OrClauses<MODEL, RESULT>, LogicalClauses<MODEL, RESULT> {

}
