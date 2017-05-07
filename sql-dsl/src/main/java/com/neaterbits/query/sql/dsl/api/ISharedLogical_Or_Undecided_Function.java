package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedLogical_Or_Undecided_Function<
	MODEL,
	RESULT,
	
	NAMED_OR_CLAUSES extends ISharedLogical_Or_Named_All<MODEL, RESULT, NAMED_OR_CLAUSES, NAMED_NESTED_AND_CLAUSES>,
	NAMED_NESTED_AND_CLAUSES extends ISharedLogical_And_Named<MODEL, RESULT>,
	
	ALIAS_OR_CLAUSES extends ISharedLogical_Or_Alias_Base<MODEL, RESULT, ALIAS_OR_CLAUSES, ALIAS_NESTED_AND_CLAUSES>,
	ALIAS_NESTED_AND_CLAUSES extends ISharedLogical_And_Alias<MODEL, RESULT>,

	UNDECIDED_OR_CLAUSES extends ISharedLogical_Or_Undecided_Base<MODEL, RESULT, NAMED_OR_CLAUSES, NAMED_NESTED_AND_CLAUSES, ALIAS_OR_CLAUSES, ALIAS_NESTED_AND_CLAUSES>,
	UNDECIDED_NESTED_AND_CLAUSES extends ISharedLogical_And_Undecided<MODEL, RESULT>>
	
	{
	
	ISharedFunctions_Transform_Initial_Undecided<
		MODEL,
		RESULT,
		
		NAMED_OR_CLAUSES,
		ALIAS_OR_CLAUSES,
		UNDECIDED_OR_CLAUSES,
		
		
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, NAMED_OR_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, ALIAS_OR_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, UNDECIDED_OR_CLAUSES>,
		
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Byte, NAMED_OR_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Short, NAMED_OR_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, NAMED_OR_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Long, NAMED_OR_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigInteger, NAMED_OR_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Float, NAMED_OR_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Double, NAMED_OR_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigDecimal, NAMED_OR_CLAUSES>,
		ISharedComparison_Comparable_String_All<MODEL, RESULT, NAMED_OR_CLAUSES>,
		
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Byte, ALIAS_OR_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Short, ALIAS_OR_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, ALIAS_OR_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Long, ALIAS_OR_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigInteger, ALIAS_OR_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Float, ALIAS_OR_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Double, ALIAS_OR_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigDecimal, ALIAS_OR_CLAUSES>,
		ISharedComparison_Comparable_String_All<MODEL, RESULT, ALIAS_OR_CLAUSES>,

		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Byte, UNDECIDED_OR_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Short, UNDECIDED_OR_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, UNDECIDED_OR_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Long, UNDECIDED_OR_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigInteger, UNDECIDED_OR_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Float, UNDECIDED_OR_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, Double, UNDECIDED_OR_CLAUSES>,
		ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigDecimal, UNDECIDED_OR_CLAUSES>,
		ISharedComparison_Comparable_String_All<MODEL, RESULT, UNDECIDED_OR_CLAUSES>

		>
		
		or();

}
