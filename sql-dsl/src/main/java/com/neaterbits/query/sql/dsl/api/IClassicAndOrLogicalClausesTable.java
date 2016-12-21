package com.neaterbits.query.sql.dsl.api;

public interface IClassicAndOrLogicalClausesTable<MODEL, RESULT>
	extends IClassicAndClausesTable<MODEL, RESULT>, IClassicOrClausesTable<MODEL, RESULT>, ISharedLogicalClauses<MODEL, RESULT> {

}
