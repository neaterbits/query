package com.neaterbits.query.sql.dsl.api;

public interface ISharedLogical_Or_Named_All_And_Function<
			MODEL,
			RESULT,
			OR_CLAUSES extends ISharedLogical_Or<MODEL, RESULT>,
			NESTED_AND_CLAUSES extends ISharedLogical_And_Named<MODEL, RESULT>>

	extends ISharedLogical_Or_Named_All<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES>,
		    ISharedLogical_Or_Named_Function<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES>

{

}
