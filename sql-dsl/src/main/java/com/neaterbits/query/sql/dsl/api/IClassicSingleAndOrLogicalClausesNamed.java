package com.neaterbits.query.sql.dsl.api;

public interface IClassicSingleAndOrLogicalClausesNamed<MODEL, RESULT> 
	extends IClassicSingleAndClausesNamed<MODEL, RESULT>, IClassicSingleOrClausesNamed<MODEL, RESULT>, ISharedLogical_Base<MODEL, RESULT>,
		ISharedCompileEndClause<MODEL> {

}
