package com.neaterbits.query.sql.dsl.api;

public interface ISharedSubOperand_String_Ops_Undecided<MODEL, RESULT, AFTER extends ISharedFunction_After<MODEL, RESULT>>

	extends ISharedSubOperand_End_Undecided<MODEL, RESULT, String>,
		ISharedOperands_String_Named_Base<MODEL, RESULT, AFTER, ISharedSubOperandsBuilder_String_Next_Alias<MODEL, RESULT, AFTER>>,
		ISharedOperands_String_Alias_Base<MODEL, RESULT, AFTER, ISharedSubOperandsBuilder_String_Next_Alias<MODEL, RESULT, AFTER>>,
		ISharedOperands_String_Common<MODEL, RESULT, AFTER, ISharedSubOperandsBuilder_String_Next_Undecided<MODEL, RESULT, AFTER>>,
		
	
	ISharedFunction_Next<MODEL, RESULT, AFTER> {

	/*
	 * 	extends ISharedSubOperand_End_Undecided<MODEL, RESULT, R>,
			ISharedOperands_Numeric_Named_Base<MODEL, RESULT, AFTER, ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, R, AFTER>>,
			ISharedOperands_Numeric_Alias_Base<MODEL, RESULT, AFTER, ISharedSubOperandsBuilder_Numeric_Next_Alias<MODEL, RESULT, R, AFTER>>,
			ISharedOperands_Numeric_Common<MODEL, RESULT, AFTER, ISharedSubOperandsBuilder_Numeric_Next_Undecided<MODEL, RESULT, R, AFTER>>,
	
	ISharedFunction_Next<MODEL, RESULT, AFTER> {


	 */
}
