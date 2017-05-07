package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_Where_Undecided_Base<
		MODEL,
		RESULT,
		
		NAMED_CONDITION_CLAUSE extends ISharedLogical_And_Or_Named_All<MODEL, RESULT, ?, ?, ?, ?>,
		ALIAS_CONDITION_CLAUSE extends ISharedLogical_Base<MODEL, RESULT>>

		/*
		AND_OR extends ISharedLogical_And_Or_Undecided<
					MODEL,
					RESULT,
					?, ?, ?, ?,
					?, ?, ?, ?,
					?, ?, ?, ?>
					>
					*/
		
		extends ISharedLogical_Where_Undecided<
			MODEL,
			RESULT,
			NAMED_CONDITION_CLAUSE,
			ALIAS_CONDITION_CLAUSE> {

}
