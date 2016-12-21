package com.neaterbits.query.sql.dsl.api;


public interface ISharedOrClausesAlias<MODEL, RESULT, OR_CLAUSES extends ISharedOrClausesAlias<MODEL, RESULT, OR_CLAUSES>> extends ISharedOrClauses<MODEL, RESULT> {
	
    ISharedClauseConditionAll<MODEL, RESULT, Integer, OR_CLAUSES> or(ISupplierInteger getter);

    ISharedClauseConditionAll<MODEL, RESULT, Long, OR_CLAUSES> or(ISupplierLong getter);
    
    ISharedClauseComparableStringAll<MODEL, RESULT, OR_CLAUSES> or(ISupplierString getter);

}
