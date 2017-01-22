package com.neaterbits.query.sql.dsl.api;


public interface ISharedAndClausesAliasBase<
		MODEL,
		RESULT,
		AND_CLAUSES extends ISharedAndClausesAliasBase<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
		NESTED_OR_CLAUSES extends ISharedOrClausesAlias<MODEL, RESULT>>
		
		extends ISharedAndClausesAlias<MODEL, RESULT> {

	ISharedConditionClauseAlias<MODEL, RESULT, Integer, AND_CLAUSES> and(ISupplierInteger getter);

	ISharedConditionClauseAlias<MODEL, RESULT, Long, AND_CLAUSES> and(ISupplierLong getter);

    ISharedClauseComparableStringAll<MODEL, RESULT, AND_CLAUSES> and(ISupplierString getter);

	AND_CLAUSES andNest(ISharedNestedOrConsumerAlias<MODEL, RESULT, NESTED_OR_CLAUSES> orBuilder);
    
}
