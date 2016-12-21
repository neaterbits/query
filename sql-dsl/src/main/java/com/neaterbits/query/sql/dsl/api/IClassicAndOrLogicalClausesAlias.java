package com.neaterbits.query.sql.dsl.api;

public interface IClassicAndOrLogicalClausesAlias<MODEL, RESULT>
		extends IClassicAndClausesAlias<MODEL, RESULT>, IClassicOrClausesAlias<MODEL, RESULT>, ISharedLogicalClauses<MODEL, RESULT> {

}
