package com.neaterbits.query.sql.dsl.api;

public interface ISharedSubOperandsBuilder_String_Next_Alias<MODEL, RESULT, AFTER extends ISharedFunction_After<MODEL, RESULT>>

	extends 
		ISharedSubOperand_End_Alias<MODEL, RESULT, String>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedOperands_String_Alias_All<
			MODEL,
			RESULT,
			AFTER,
			ISharedSubOperandsBuilder_String_Next_Alias<MODEL, RESULT, AFTER>,
			ISharedSubOperandsBuilder_String_Next_Alias<MODEL, RESULT, AFTER>> {
}
