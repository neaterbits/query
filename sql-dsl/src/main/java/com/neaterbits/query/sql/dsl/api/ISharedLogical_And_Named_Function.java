package com.neaterbits.query.sql.dsl.api;

/**
 * Causes name-crash with alias so must be in separate interface
 * @author nhl
 *
 */

public interface ISharedLogical_And_Named_Function
	<
	MODEL,
	RESULT,
	AND_CLAUSES extends ISharedLogical_And<MODEL, RESULT>,
	NESTED_OR_CLAUSES extends ISharedLogical_Or_Named<MODEL, RESULT>

	/*,

	
	INTEGER_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, AND_CLAUSES>,
	LONG_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Long, AND_CLAUSES>,
	STRING_CLAUSE extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, AND_CLAUSES>
	*/
	>
	
{

	ISharedFunctions_Named_Transform_Initial<
			MODEL,
			RESULT, 
			AND_CLAUSES,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_CLAUSES>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AND_CLAUSES>,
			ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES>			
			//INTEGER_CLAUSE, LONG_CLAUSE, STRING_CLAUSE
			
			>
			and();
    
}
