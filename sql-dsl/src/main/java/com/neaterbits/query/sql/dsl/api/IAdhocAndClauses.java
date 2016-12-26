package com.neaterbits.query.sql.dsl.api;

public interface IAdhocAndClauses<MODEL, RESULT>
		extends ISharedAndClausesTableValues<MODEL, RESULT, IAdhocAndClauses<MODEL, RESULT>>,
			IAdhocEndClauseBase<MODEL, RESULT> {

}
