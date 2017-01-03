package com.neaterbits.query.sql.dsl.api;

public interface IClassicAndOrLogicalClausesNamed<MODEL, RESULT>

		extends ISharedAndOrLogicalClausesNamedAll<
							MODEL,
							RESULT,
							IClassicAndClausesNamed<MODEL, RESULT>,
							IClassicOrClausesNamed<MODEL, RESULT>>,
						
							
						ISharedCompileEndClause<MODEL> {

}
