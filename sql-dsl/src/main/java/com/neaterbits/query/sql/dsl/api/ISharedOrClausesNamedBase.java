package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;

public interface ISharedOrClausesNamedBase<
	MODEL,
	RESULT,
	OR_CLAUSES extends ISharedOrClauses<MODEL, RESULT>,
	NESTED_AND_CLAUSES extends ISharedAndClauses<MODEL, RESULT>,
	
	INTEGER_CLAUSE extends ISharedClauseComparableCommonBase<MODEL, RESULT, Integer, OR_CLAUSES>,
	LONG_CLAUSE extends ISharedClauseComparableCommonBase<MODEL, RESULT, Long, OR_CLAUSES>,
	STRING_CLAUSE extends ISharedClauseComparableStringBase<MODEL, RESULT, OR_CLAUSES>>

	extends ISharedOrClauses<MODEL, RESULT> {

    <T> INTEGER_CLAUSE or(IFunctionInteger<T> getter);
    
    <T> LONG_CLAUSE or(IFunctionLong<T> getter);

    <T> STRING_CLAUSE or(StringFunction<T> getter);
    
	OR_CLAUSES orNest(Consumer<NESTED_AND_CLAUSES> orBuilder);
    
}
