package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedLogical_Or_Named_Function<
	MODEL,
	RESULT,
	OR_CLAUSES extends ISharedLogical_Or<MODEL, RESULT>,
	NESTED_AND_CLAUSES extends ISharedLogical_And_Named<MODEL, RESULT>

	/* ,
	
	
	INTEGER_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, OR_CLAUSES>,
	LONG_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Long, OR_CLAUSES>,
	STRING_CLAUSE extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, OR_CLAUSES>
	*/> {

    ISharedFunctions_Transform_Initial_Named<
    		MODEL,
    		RESULT,
    		OR_CLAUSES,
    		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES>,
    		
    		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Byte, OR_CLAUSES>,
    		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Short, OR_CLAUSES>,
    		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigInteger, OR_CLAUSES>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Float, OR_CLAUSES>,
			ISharedComparison_Comparable_Common_All<MODEL, RESULT, Double, OR_CLAUSES>,
    		ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigDecimal, OR_CLAUSES>,
			ISharedComparison_Comparable_String_All<MODEL, RESULT, OR_CLAUSES> /*    		
    		INTEGER_CLAUSE,
    		LONG_CLAUSE,
    		STRING_CLAUSE
    		*/
    		> or();
	    
}
