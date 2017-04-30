package com.neaterbits.query.sql.dsl.api;

public interface IAdhocFunctions_String<
		MODEL,
		RESULT,
		ENTITY,
		RET extends ISharedLogical_Base<MODEL, RESULT>,
		
		STRING_CLAUSE extends ISharedComparison_Comparable_String_Value<MODEL, RESULT, RET>> {
		
		STRING_CLAUSE lower(IFunctionString<ENTITY> getter);
		IAdhocFunctions_String<MODEL, RESULT, ENTITY, RET, STRING_CLAUSE> lower();
		
		STRING_CLAUSE upper(IFunctionString<ENTITY> getter);
		IAdhocFunctions_String<MODEL, RESULT, ENTITY, RET, STRING_CLAUSE> upper();
		
		STRING_CLAUSE trim(IFunctionString<ENTITY> getter);
		IAdhocFunctions_String<MODEL, RESULT, ENTITY, RET, STRING_CLAUSE> trim();
}
