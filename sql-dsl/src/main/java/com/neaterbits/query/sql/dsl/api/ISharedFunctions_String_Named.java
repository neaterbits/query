package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_String_Named<
		MODEL,
		RESULT,
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		STRING_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, RET>

> {

    <T> STRING_CLAUSE lower(IFunctionString<T> getter);

    <T> STRING_CLAUSE upper(IFunctionString<T> getter);

    <T> STRING_CLAUSE trim(IFunctionString<T> getter);
	
}
