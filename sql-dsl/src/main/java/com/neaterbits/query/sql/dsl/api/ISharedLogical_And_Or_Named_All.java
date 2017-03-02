package com.neaterbits.query.sql.dsl.api;

public interface ISharedLogical_And_Or_Named_All<
										MODEL,
										RESULT,
										AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
										OR_CLAUSES extends ISharedLogical_Or_Named_All<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES>,
										
										NESTED_AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES>,
										NESTED_OR_CLAUSES  extends ISharedLogical_Or_Named_All<MODEL, RESULT, NESTED_OR_CLAUSES,  NESTED_AND_CLAUSES>> 
										
		extends
			ISharedLogical_And_Named_All_And_Function<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
			ISharedLogical_Or_Named_All_And_Function<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES>,
			ISharedLogical_Base<MODEL, RESULT> {

}
