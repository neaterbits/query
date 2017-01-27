package com.neaterbits.query.sql.dsl.api;

public interface IClassicAndOrLogicalClausesNamed<MODEL, RESULT>

		extends ISharedLogical_And_Or_Named_All<
							MODEL,
							RESULT,
							IClassicAndClausesNamed<MODEL, RESULT>,
							IClassicOrClausesNamed<MODEL, RESULT>>,
						
							
						ISharedCompileEndClause<MODEL> {

}
