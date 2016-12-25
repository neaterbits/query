package com.neaterbits.query.sql.dsl.api;

import java.util.List;

public interface IAdhocAndOrLogicalClausesList<MODEL, T, RESULT extends List<T>>
		extends IAdhocAndOrLogicalClauses<MODEL, RESULT>,
				IAdhocEndClauseList<MODEL, T, RESULT> {

}
