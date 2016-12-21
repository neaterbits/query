package com.neaterbits.query.sql.dsl.api;


public interface ISharedOrClausesAlias<MODEL, RESULT, OR_CLAUSES extends ISharedOrClausesAlias<MODEL, RESULT, OR_CLAUSES>> extends ISharedOrClauses<MODEL, RESULT> {
	
    ISharedConditionClause<MODEL, RESULT, Integer, OR_CLAUSES> or(ISupplierInteger getter);

    ISharedConditionClause<MODEL, RESULT, Long, OR_CLAUSES> or(ISupplierLong getter);
    
    ISharedStringClause<MODEL, RESULT, OR_CLAUSES> or(ISupplierString getter);

}
