package com.neaterbits.query.sql.dsl.api;

public interface ISharedLogical_Or_Undecided_Base<
	MODEL,
	RESULT,
	
	NAMED_OR_CLAUSES extends ISharedLogical_Or_Named_All<MODEL, RESULT, NAMED_OR_CLAUSES,NAMED_NESTED_AND_CLAUSES>,
	NAMED_NESTED_AND_CLAUSES extends ISharedLogical_And_Named<MODEL, RESULT>,
	
	ALIAS_OR_CLAUSES extends ISharedLogical_Or_Alias_Base<MODEL, RESULT, ALIAS_OR_CLAUSES, ALIAS_NESTED_AND_CLAUSES>,
	ALIAS_NESTED_AND_CLAUSES extends ISharedLogical_And_Alias<MODEL, RESULT>> 

	
	extends
		ISharedLogical_Or_Named_All<MODEL, RESULT, NAMED_OR_CLAUSES, NAMED_NESTED_AND_CLAUSES>,
		ISharedLogical_Or_Alias_Base<MODEL, RESULT, ALIAS_OR_CLAUSES, ALIAS_NESTED_AND_CLAUSES>,
		
		ISharedLogical_Or_Undecided<MODEL, RESULT>
	
{
	
	
}
