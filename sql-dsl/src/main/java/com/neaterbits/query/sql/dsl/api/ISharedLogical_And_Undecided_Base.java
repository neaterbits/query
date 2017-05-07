package com.neaterbits.query.sql.dsl.api;

public interface ISharedLogical_And_Undecided_Base<
	MODEL,
	RESULT,
	
	NAMED_AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, NAMED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES>,
	NAMED_NESTED_OR_CLAUSES extends ISharedLogical_Or_Named<MODEL, RESULT>,
	
	ALIAS_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, ALIAS_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES>,
	ALIAS_NESTED_OR_CLAUSES extends ISharedLogical_Or_Alias<MODEL, RESULT>>
		
	extends
		ISharedLogical_And_Named_All<MODEL, RESULT, NAMED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES>,
		ISharedLogical_And_Alias_Base<MODEL, RESULT, ALIAS_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES>,
		ISharedLogical_And_Undecided<MODEL, RESULT> {
	

}
