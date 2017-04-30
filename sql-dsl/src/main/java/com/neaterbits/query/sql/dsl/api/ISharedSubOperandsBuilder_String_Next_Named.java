package com.neaterbits.query.sql.dsl.api;

public interface ISharedSubOperandsBuilder_String_Next_Named<MODEL, RESULT, AFTER extends ISharedFunction_After<MODEL, RESULT>>

	extends 
		ISharedSubOperand_End_Named<MODEL, RESULT, String>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedOperands_String_Named_All<
			MODEL,
			RESULT,
			AFTER,
			ISharedSubOperandsBuilder_String_Next_Named<MODEL, RESULT, AFTER>,
			ISharedSubOperandsBuilder_String_Next_Named<MODEL, RESULT, AFTER>> {
}
