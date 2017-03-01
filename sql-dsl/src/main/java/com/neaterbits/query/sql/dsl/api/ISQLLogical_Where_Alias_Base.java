package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_Where_Alias_Base<
		MODEL,
		RESULT,
		AND_OR extends ISharedLogical_And_Or_Alias<MODEL, RESULT, ?, ?, ?, ?>>

	extends ISharedLogical_Where_Alias<
		MODEL,
		RESULT,
		AND_OR> {

}
