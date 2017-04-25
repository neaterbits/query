package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_String_NoParam_Base<MODEL, RESULT, LENGTH_RET, STRING_RET> 
	extends ISharedFunctions_String_NumericResult_NoParam_Base<MODEL, RESULT, LENGTH_RET> {
	
	STRING_RET lower();
    STRING_RET upper();
    STRING_RET trim();

}
