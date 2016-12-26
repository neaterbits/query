package com.neaterbits.query.sql.dsl.api;

public interface IAdhocOrClauses<MODEL, RESULT>
		extends ISharedOrClausesTableValues<MODEL, RESULT, IAdhocOrClauses<MODEL, RESULT>>,
				IAdhocEndClauseBase<MODEL, RESULT> {

}
