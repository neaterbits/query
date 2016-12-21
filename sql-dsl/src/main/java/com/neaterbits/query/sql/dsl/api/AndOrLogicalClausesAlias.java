package com.neaterbits.query.sql.dsl.api;

public interface AndOrLogicalClausesAlias<MODEL, RESULT>
		extends IClassicAndClausesAlias<MODEL, RESULT>, OrClausesAlias<MODEL, RESULT>, ISharedLogicalClauses<MODEL, RESULT> {

}
