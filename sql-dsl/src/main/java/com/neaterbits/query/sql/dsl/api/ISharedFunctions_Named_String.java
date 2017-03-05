package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Named_String<
		MODEL,
		RESULT,
		RET, // can be used for mapping as well extends ISharedLogical_Base<MODEL, RESULT>,
		
		// comment out since may be used for map as well, not only in conditions
		STRING_CLAUSE  // extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, RET>

> {

    <T> STRING_CLAUSE lower(StringFunction<T> getter);

    <T> STRING_CLAUSE upper(StringFunction<T> getter);

    <T> STRING_CLAUSE trim(StringFunction<T> getter);
	
}
