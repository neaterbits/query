package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedLogical_And_Undecided_Function<
	MODEL,
	RESULT,
	
	NAMED_AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, NAMED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES>,
	NAMED_NESTED_OR_CLAUSES extends ISharedLogical_Or_Named<MODEL, RESULT>,
	
	ALIAS_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, ALIAS_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES>,
	ALIAS_NESTED_OR_CLAUSES extends ISharedLogical_Or_Alias<MODEL, RESULT>,
	
	UNDECIDED_AND_CLAUSES extends ISharedLogical_And_Undecided_Base<MODEL, RESULT, NAMED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, ALIAS_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES>,
	UNDECIDED_NESTED_OR_CLAUSES extends ISharedLogical_Or_Undecided<MODEL, RESULT>>
	
{		
	
	ISharedFunctions_Transform_Initial_Undecided<
		MODEL,
		RESULT,
		
		NAMED_AND_CLAUSES,
		ALIAS_AND_CLAUSES,
		UNDECIDED_AND_CLAUSES,
		
		
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, NAMED_AND_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, ALIAS_AND_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, UNDECIDED_AND_CLAUSES>,
		
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Byte, NAMED_AND_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Short, NAMED_AND_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, NAMED_AND_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Long, NAMED_AND_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigInteger, NAMED_AND_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Float, NAMED_AND_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Double, NAMED_AND_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigDecimal, NAMED_AND_CLAUSES>,
		ISharedComparison_Comparable_String_All<MODEL, RESULT, NAMED_AND_CLAUSES>,
		
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Byte, ALIAS_AND_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Short, ALIAS_AND_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, ALIAS_AND_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Long, ALIAS_AND_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigInteger, ALIAS_AND_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Float, ALIAS_AND_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Double, ALIAS_AND_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigDecimal, ALIAS_AND_CLAUSES>,
		ISharedComparison_Comparable_String_All<MODEL, RESULT, ALIAS_AND_CLAUSES>,
	
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Byte, UNDECIDED_AND_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Short, UNDECIDED_AND_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, UNDECIDED_AND_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Long, UNDECIDED_AND_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigInteger, UNDECIDED_AND_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Float, UNDECIDED_AND_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Double, UNDECIDED_AND_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigDecimal, UNDECIDED_AND_CLAUSES>,
		ISharedComparison_Comparable_String_All<MODEL, RESULT, UNDECIDED_AND_CLAUSES>
	
		>
		
		and();

}
