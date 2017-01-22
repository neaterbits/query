package com.neaterbits.query.sql.dsl.api;

public interface ISharedOrClausesAliasBase<
		MODEL,
		RESULT,
		OR_CLAUSES extends ISharedOrClausesAliasBase<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES>,
		NESTED_AND_CLAUSES extends ISharedAndClausesAlias<MODEL, RESULT>
		> extends ISharedOrClausesAlias<MODEL, RESULT> {
	
    ISharedClauseConditionAll<MODEL, RESULT, Integer, OR_CLAUSES> or(ISupplierInteger getter);

    ISharedClauseConditionAll<MODEL, RESULT, Long, OR_CLAUSES> or(ISupplierLong getter);
    
    ISharedClauseComparableStringAll<MODEL, RESULT, OR_CLAUSES> or(ISupplierString getter);

	OR_CLAUSES orNest(ISharedNestedAndConsumerAlias<MODEL, RESULT, NESTED_AND_CLAUSES> orBuilder);
    
}
