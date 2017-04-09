package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface ISharedLogical_Or_Named_Base<
	MODEL,
	RESULT,
	OR_CLAUSES extends ISharedLogical_Or<MODEL, RESULT>,
	NESTED_AND_CLAUSES extends ISharedLogical_And_Named<MODEL, RESULT>,
	
	INTEGER_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, OR_CLAUSES>,
	LONG_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Long, OR_CLAUSES>,
	BIGDECIMAL_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, BigDecimal, OR_CLAUSES>,
	STRING_CLAUSE extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, OR_CLAUSES>>

	extends ISharedLogical_Or_Named<MODEL, RESULT> {

    <T> INTEGER_CLAUSE or(IFunctionInteger<T> getter);
    
    <T> LONG_CLAUSE or(IFunctionLong<T> getter);

    <T> BIGDECIMAL_CLAUSE or(IFunctionBigDecimal<T> getter);

    <T> STRING_CLAUSE or(StringFunction<T> getter);
    
	OR_CLAUSES orNest(ISharedNestedAndConsumerNamed<MODEL, RESULT, NESTED_AND_CLAUSES> andBuilder);
    
}
