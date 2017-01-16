package com.neaterbits.query.sql.dsl.api;

import java.util.List;

public interface IAdhocAndOrLogicalClausesList<MODEL, R, RESULT extends List<R>>
		extends IAdhocAndOrLogicalClauses<MODEL, RESULT, R>,
				IAdhocEndClauseList<MODEL, R, RESULT> {

}
