package com.neaterbits.query.sql.dsl.api;

public interface IClassicLogical_And_MultiMapped_Alias<MODEL, RESULT>
	extends ISharedLogical_And_Alias_Base<
			MODEL,
			RESULT,
			IClassicLogical_And_MultiMapped_Alias<MODEL, RESULT>,
			IClassicLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>,

		ISharedCompileEndClause<MODEL> {

}
