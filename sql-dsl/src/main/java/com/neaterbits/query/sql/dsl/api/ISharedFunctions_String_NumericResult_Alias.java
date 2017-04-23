package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_String_NumericResult_Alias<
		MODEL,
		RESULT,
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		LENGTH_RET_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, RET>

	> {

	LENGTH_RET_CLAUSE length(ISupplierString getter);

}
