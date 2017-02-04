package com.neaterbits.query.sql.dsl.api;

public interface ISharedLogical_And_Or_Alias<
			MODEL,
			RESULT,
			AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
			OR_CLAUSES  extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, OR_CLAUSES,  NESTED_AND_CLAUSES>,
			
			NESTED_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES>,
			NESTED_OR_CLAUSES  extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, NESTED_OR_CLAUSES,  NESTED_AND_CLAUSES>			
			> 

		extends
			ISharedLogical_And_Alias_Base<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
			ISharedLogical_Or_Alias_Base<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES>,
			ISharedLogical_Base<MODEL, RESULT> {

}
