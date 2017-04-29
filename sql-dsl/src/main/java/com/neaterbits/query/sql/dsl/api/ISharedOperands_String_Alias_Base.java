package com.neaterbits.query.sql.dsl.api;

public interface ISharedOperands_String_Alias_Base<

		MODEL,
		RESULT,
		
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>
	> {

	TYPE_RET concat(ISupplierString getter);

}
