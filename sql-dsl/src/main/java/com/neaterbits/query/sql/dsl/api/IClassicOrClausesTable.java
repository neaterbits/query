package com.neaterbits.query.sql.dsl.api;

public interface IClassicOrClausesTable<MODEL, RESULT> 
		extends ISharedOrClausesTable<MODEL, RESULT, IClassicOrClausesTable<MODEL, RESULT>>,
				ISharedCompileEndClause<MODEL> {

}
