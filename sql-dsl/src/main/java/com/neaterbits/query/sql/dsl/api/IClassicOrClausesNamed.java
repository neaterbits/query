package com.neaterbits.query.sql.dsl.api;

public interface IClassicOrClausesNamed<MODEL, RESULT> 
		extends ISharedLogical_Or_Named_All<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>, IClassicAndClausesNamed<MODEL, RESULT>>,
				ISharedCompileEndClause<MODEL> {

}
