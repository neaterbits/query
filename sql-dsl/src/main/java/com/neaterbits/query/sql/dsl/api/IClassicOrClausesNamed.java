package com.neaterbits.query.sql.dsl.api;

public interface IClassicOrClausesNamed<MODEL, RESULT> 
		extends ISharedOrClausesNamed<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>>,
				ISharedCompileEndClause<MODEL> {

}
