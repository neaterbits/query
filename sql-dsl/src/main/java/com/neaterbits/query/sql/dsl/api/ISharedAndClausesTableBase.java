package com.neaterbits.query.sql.dsl.api;

public interface ISharedAndClausesTableBase<
		MODEL,
		RESULT,
		AND_CLAUSES extends ISharedAndClauses<MODEL, RESULT>,

		INTEGER_CLAUSE extends ISharedClauseComparableCommonBase<MODEL, RESULT, Integer, AND_CLAUSES>,
		LONG_CLAUSE extends ISharedClauseComparableCommonBase<MODEL, RESULT, Long, AND_CLAUSES>,
		STRING_CLAUSE extends ISharedClauseComparableStringBase<MODEL, RESULT, AND_CLAUSES>>

		extends ISharedAndClauses<MODEL, RESULT> {
	
    <T> INTEGER_CLAUSE and(IFunctionInteger<T> getter);

    <T> LONG_CLAUSE and(IFunctionLong<T> getter);

    <T> STRING_CLAUSE and(StringFunction<T> getter);
}