package com.neaterbits.query.sql.dsl.api;

public interface ISharedLogical_And_Or_Named_Values<
										MODEL,
										RESULT,
										AND_CLAUSES extends ISharedLogical_And_Named_Values<MODEL, RESULT, AND_CLAUSES, OR_CLAUSES>,
										OR_CLAUSES extends ISharedLogical_Or_Named_Values<MODEL, RESULT, OR_CLAUSES, AND_CLAUSES>>

		extends
			ISharedLogical_And_Named_Values<MODEL, RESULT, AND_CLAUSES, OR_CLAUSES>,
			ISharedLogical_Or_Named_Values<MODEL, RESULT, OR_CLAUSES, AND_CLAUSES>,
			ISharedLogical_Base<MODEL, RESULT> {

}
