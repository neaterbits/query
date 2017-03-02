package com.neaterbits.query.sql.dsl.api;


public interface ISharedLogical_And_Named_Base<
		MODEL,
		RESULT,
		AND_CLAUSES extends ISharedLogical_And<MODEL, RESULT>,
		NESTED_OR_CLAUSES extends ISharedLogical_Or_Named<MODEL, RESULT>,

		INTEGER_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, AND_CLAUSES>,
		LONG_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Long, AND_CLAUSES>,
		STRING_CLAUSE extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, AND_CLAUSES>>

		extends ISharedLogical_And_Named<MODEL, RESULT> {
	
    <T> INTEGER_CLAUSE and(IFunctionInteger<T> getter);

    <T> LONG_CLAUSE and(IFunctionLong<T> getter);

    <T> STRING_CLAUSE and(StringFunction<T> getter);

	AND_CLAUSES andNest(ISharedNestedOrConsumerNamed<MODEL, RESULT, NESTED_OR_CLAUSES> orBuilder);
    
}