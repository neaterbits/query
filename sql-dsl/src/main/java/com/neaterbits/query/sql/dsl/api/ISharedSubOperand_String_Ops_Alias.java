package com.neaterbits.query.sql.dsl.api;

public interface ISharedSubOperand_String_Ops_Alias<MODEL, RESULT, AFTER extends ISharedFunction_After<MODEL, RESULT>>

	extends ISharedSubOperand_End_Alias<MODEL, RESULT, String>,
		ISharedOperands_String_Alias<MODEL, RESULT, AFTER, ISharedSubOperandsBuilder_String_Next_Alias<MODEL, RESULT, AFTER>>,
	
	ISharedFunction_Next<MODEL, RESULT, AFTER> {

}
