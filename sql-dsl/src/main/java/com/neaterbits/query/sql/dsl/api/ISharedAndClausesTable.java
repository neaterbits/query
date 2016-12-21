package com.neaterbits.query.sql.dsl.api;


public interface ISharedAndClausesTable<MODEL, RESULT, AND_CLAUSES extends ISharedAndClausesTable<MODEL, RESULT, AND_CLAUSES>> extends ISharedAndClauses<MODEL, RESULT> {
	
    <T> ISharedConditionClauseTable<MODEL, RESULT, Integer, AND_CLAUSES> and(IFunctionInteger<T> getter);

    <T> ISharedConditionClauseTable<MODEL, RESULT, Long, AND_CLAUSES> and(IFunctionLong<T> getter);

    <T> ISharedStringClause<MODEL, RESULT, AND_CLAUSES> and(StringFunction<T> getter);

}
