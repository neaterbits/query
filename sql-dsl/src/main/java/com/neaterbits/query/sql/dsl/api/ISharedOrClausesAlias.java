package com.neaterbits.query.sql.dsl.api;


public interface ISharedOrClausesAlias<MODEL, RESULT, OR_CLAUSES extends ISharedOrClausesAlias<MODEL, RESULT, OR_CLAUSES>> extends ISharedOrClauses<MODEL, RESULT> {
	
    ISharedConditionClause<MODEL, RESULT, Integer, OR_CLAUSES> or(IntegerSupplier getter);

    ISharedConditionClause<MODEL, RESULT, Long, OR_CLAUSES> or(LongSupplier getter);
    
    ISharedStringClause<MODEL, RESULT, OR_CLAUSES> or(StringSupplier getter);

}
