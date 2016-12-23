package com.neaterbits.query.sql.dsl.api;

public interface IClassicAndClausesTable<MODEL, RESULT>
		extends ISharedAndClausesTable<MODEL, RESULT, IClassicAndClausesTable<MODEL, RESULT>>,
				ISharedCompileEndClause<MODEL> {

}
