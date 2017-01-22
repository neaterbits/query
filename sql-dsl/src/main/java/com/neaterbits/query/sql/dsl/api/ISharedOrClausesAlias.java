package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;

public interface ISharedOrClausesAlias<
		MODEL,
		RESULT,
		OR_CLAUSES extends ISharedOrClausesAlias<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES>,
		NESTED_AND_CLAUSES extends ISharedAndClauses<MODEL, RESULT>
		> extends ISharedOrClauses<MODEL, RESULT> {
	
    ISharedClauseConditionAll<MODEL, RESULT, Integer, OR_CLAUSES> or(ISupplierInteger getter);

    ISharedClauseConditionAll<MODEL, RESULT, Long, OR_CLAUSES> or(ISupplierLong getter);
    
    ISharedClauseComparableStringAll<MODEL, RESULT, OR_CLAUSES> or(ISupplierString getter);

	OR_CLAUSES andNest(Consumer<NESTED_AND_CLAUSES> orBuilder);
    
}
