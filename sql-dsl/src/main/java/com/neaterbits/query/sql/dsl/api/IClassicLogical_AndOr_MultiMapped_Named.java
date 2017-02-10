package com.neaterbits.query.sql.dsl.api;

public interface IClassicLogical_AndOr_MultiMapped_Named<MODEL, RESULT> 
	extends
	ISharedLogical_And_Or_Named_All<
			MODEL, 
			RESULT,
	
			IClassicLogical_And_MultiMapped_Named<MODEL, RESULT>,
			IClassicLogical_Or_MultiMapped_Named<MODEL, RESULT>,

	
			IClassicLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			IClassicLogical_Or_NonProcessResult_Named<MODEL, RESULT>
			>,
	
	ISharedProcessResult_All_Named<MODEL, RESULT>,			
	ISharedCompileEndClause<MODEL> {

}
