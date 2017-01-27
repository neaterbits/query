package com.neaterbits.query.sql.dsl.api;

public interface ISharedLogical_And_Or_Alias<
			MODEL,
			RESULT,
			AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, AND_CLAUSES, OR_CLAUSES>,
			OR_CLAUSES  extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, OR_CLAUSES,  AND_CLAUSES>> 

		extends
			ISharedLogical_And_Alias_Base<MODEL, RESULT, AND_CLAUSES, OR_CLAUSES>,
			ISharedLogical_Or_Alias_Base<MODEL, RESULT, OR_CLAUSES, AND_CLAUSES>,
			ISharedLogical_Base<MODEL, RESULT> {

}
