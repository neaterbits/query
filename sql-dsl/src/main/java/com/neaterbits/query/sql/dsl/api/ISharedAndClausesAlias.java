package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;

public interface ISharedAndClausesAlias<
		MODEL,
		RESULT,
		AND_CLAUSES extends ISharedAndClausesAlias<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
		NESTED_OR_CLAUSES extends ISharedOrClauses<MODEL, RESULT>>
		
		extends ISharedAndClauses<MODEL, RESULT> {

	ISharedConditionClauseAlias<MODEL, RESULT, Integer, AND_CLAUSES> and(ISupplierInteger getter);

	ISharedConditionClauseAlias<MODEL, RESULT, Long, AND_CLAUSES> and(ISupplierLong getter);

    ISharedClauseComparableStringAll<MODEL, RESULT, AND_CLAUSES> and(ISupplierString getter);

	AND_CLAUSES andNest(Consumer<NESTED_OR_CLAUSES> orBuilder);
    
}
