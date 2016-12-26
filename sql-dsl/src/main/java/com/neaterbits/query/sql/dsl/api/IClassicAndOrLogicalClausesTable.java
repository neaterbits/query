package com.neaterbits.query.sql.dsl.api;

public interface IClassicAndOrLogicalClausesTable<MODEL, RESULT>

		extends ISharedAndOrLogicalClausesTableAll<
							MODEL,
							RESULT,
							IClassicAndClausesTable<MODEL, RESULT>,
							IClassicOrClausesTable<MODEL, RESULT>>,
						
							
						ISharedCompileEndClause<MODEL> {

}
