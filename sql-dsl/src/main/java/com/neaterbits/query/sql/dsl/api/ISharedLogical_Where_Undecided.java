package com.neaterbits.query.sql.dsl.api;

public interface ISharedLogical_Where_Undecided<
		MODEL,
		RESULT,
		
		NAMED_CONDITION_CLAUSE extends ISharedLogical_And_Or_Named_All<MODEL, RESULT, ?, ?, ?, ?>,
		ALIAS_CONDITION_CLAUSE extends ISharedLogical_Base<MODEL, RESULT>
		>
	extends ISQLLogical_Where_Named_Base<MODEL, RESULT, NAMED_CONDITION_CLAUSE>, // TODO IShared inherits from ISQLLogical, ISQL ISHared is meant to be for adhoc queries. Undecided will probably always be SQL though
			ISharedLogical_Where_Alias<MODEL, RESULT, ALIAS_CONDITION_CLAUSE> {

}
