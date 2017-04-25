package com.neaterbits.query.sql.dsl.api;

@Deprecated // not in use it seems?
public interface ISharedFunctions_String_NoParam_Named<
		MODEL,
		RESULT,

		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		LENGTH_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, RET>
		> 

		extends ISharedFunctions_String_NoParam_Base<
				MODEL,
				RESULT,
				ISharedFunctions_String_Named<MODEL, RESULT, RET, LENGTH_CLAUSE, LENGTH_CLAUSE>,
				ISharedFunctions_String_Named<MODEL, RESULT, RET, LENGTH_CLAUSE, STRING_CLAUSE>
		> {

}
