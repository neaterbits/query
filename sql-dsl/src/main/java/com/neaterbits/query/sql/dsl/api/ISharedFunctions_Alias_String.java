package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Alias_String<

			MODEL,
			RESULT,

			RET extends ISharedFunction_After<MODEL, RESULT>,

			STRING_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, RET>

	> {
		

	<T> STRING_CLAUSE lower(ISupplierString getter);
	
	<T> STRING_CLAUSE upper(ISupplierString getter);
	
	<T> STRING_CLAUSE trim(ISupplierString getter);
		
}
