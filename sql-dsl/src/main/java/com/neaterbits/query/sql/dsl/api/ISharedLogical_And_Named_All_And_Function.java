package com.neaterbits.query.sql.dsl.api;

public interface ISharedLogical_And_Named_All_And_Function<
		MODEL,
		RESULT,
		AND_CLAUSES extends ISharedLogical_And<MODEL, RESULT>,
		NESTED_OR_CLAUSES extends ISharedLogical_Or_Named<MODEL, RESULT>>

	extends ISharedLogical_And_Named_All<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
			ISharedLogical_And_Named_Function<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>{

}
