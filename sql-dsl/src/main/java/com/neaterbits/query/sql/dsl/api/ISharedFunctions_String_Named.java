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

    <T> STRING_CLAUSE substring(IFunctionString<T> getter, int start, int length);
    
    @Deprecated // there is a concat operand for strings
    <T> STRING_CLAUSE concat(IFunctionString<T> getter1, IFunctionString<T> getter2);
	
    @Deprecated // there is a concat operand for strings
    <T> STRING_CLAUSE concat(IFunctionString<T> getter, String value);

    @Deprecated // there is a concat operand for strings
    <T> STRING_CLAUSE concat(String value, IFunctionString<T> getter);

    @Deprecated // there is a concat operand for strings
    <T> STRING_CLAUSE concat(IFunctionString<T> getter, Param<String> param);

    @Deprecated // there is a concat operand for strings
    <T> STRING_CLAUSE concat(Param<String> param, IFunctionString<T> getter);
}
