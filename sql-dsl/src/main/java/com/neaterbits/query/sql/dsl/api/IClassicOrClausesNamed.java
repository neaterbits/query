package com.neaterbits.query.sql.dsl.api;

public interface IClassicOrClausesNamed<MODEL, RESULT> 
		extends ISharedOrClausesNamedAll<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>, IClassicAndClausesNamed<MODEL, RESULT>>,
				ISharedCompileEndClause<MODEL> {

}
