package com.neaterbits.query.sql.dsl.api;

public interface ISharedOrClausesTableBase<
	MODEL,
	RESULT,
	OR_CLAUSES extends ISharedOrClauses<MODEL, RESULT>,
	
	INTEGER_CLAUSE extends ISharedClauseComparableCommonBase<MODEL, RESULT, Integer, OR_CLAUSES>,
	LONG_CLAUSE extends ISharedClauseComparableCommonBase<MODEL, RESULT, Long, OR_CLAUSES>,
	STRING_CLAUSE extends ISharedClauseComparableStringBase<MODEL, RESULT, OR_CLAUSES>>

	extends ISharedOrClauses<MODEL, RESULT> {

    <T> INTEGER_CLAUSE or(IFunctionInteger<T> getter);
    
    <T> LONG_CLAUSE or(IFunctionLong<T> getter);

    <T> STRING_CLAUSE or(StringFunction<T> getter);
}
