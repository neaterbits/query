package com.neaterbits.query.sql.dsl.api;


public interface ISharedAndClausesAlias<MODEL, RESULT, AND_CLAUSES extends ISharedAndClausesAlias<MODEL, RESULT, AND_CLAUSES>> extends ISharedAndClauses<MODEL, RESULT> {

	ISharedConditionClauseAlias<MODEL, RESULT, Integer, AND_CLAUSES> and(ISupplierInteger getter);

	ISharedConditionClauseAlias<MODEL, RESULT, Long, AND_CLAUSES> and(ISupplierLong getter);

    ISharedClauseComparativeStringAll<MODEL, RESULT, AND_CLAUSES> and(ISupplierString getter);

}
