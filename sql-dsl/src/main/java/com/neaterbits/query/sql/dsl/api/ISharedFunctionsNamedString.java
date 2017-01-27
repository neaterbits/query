package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctionsNamedString<
		MODEL,
		RESULT,
		RET extends ISharedLogicalClauses<MODEL, RESULT>,
		
		STRING_CLAUSE extends ISharedClauseComparableStringBase<MODEL, RESULT, RET>> {

    <T> STRING_CLAUSE lower(StringFunction<T> getter);
    ISharedFunctionsNamedString<MODEL, RESULT, RET, STRING_CLAUSE> lower();

    <T> STRING_CLAUSE upper(StringFunction<T> getter);
    ISharedFunctionsNamedString<MODEL, RESULT, RET, STRING_CLAUSE> upper();

    <T> STRING_CLAUSE trim(StringFunction<T> getter);
    ISharedFunctionsNamedString<MODEL, RESULT, RET, STRING_CLAUSE> trim();
	
}
