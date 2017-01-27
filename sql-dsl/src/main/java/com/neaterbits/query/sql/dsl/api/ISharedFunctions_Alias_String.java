package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Alias_String<

			MODEL,
			RESULT,
			RET extends ISharedLogical_Base<MODEL, RESULT>,
			
			STRING_CLAUSE extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, RET>> {
		

	<T> STRING_CLAUSE lower(ISupplierString getter);
	ISharedFunctions_Alias_String<MODEL, RESULT, RET, STRING_CLAUSE> lower();
	
	<T> STRING_CLAUSE upper(ISupplierString getter);
	ISharedFunctions_Alias_String<MODEL, RESULT, RET, STRING_CLAUSE> upper();
	
	<T> STRING_CLAUSE trim(ISupplierString getter);
	ISharedFunctions_Alias_String<MODEL, RESULT, RET, STRING_CLAUSE> trim();
		
}
