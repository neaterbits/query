package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Alias_Initial_And_NoParam<
		MODEL,
		RESULT,

		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		INTEGER_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, RET>
		
		>
		
		extends 
			ISharedFunctions_Alias_Transform_Initial<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE, STRING_CLAUSE>,
			ISharedFunctions_Alias_Arithmetic_NoParam<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE>,
			ISharedFunctions_Alias_String_NoParam<MODEL, RESULT, RET, STRING_CLAUSE> 

{

}