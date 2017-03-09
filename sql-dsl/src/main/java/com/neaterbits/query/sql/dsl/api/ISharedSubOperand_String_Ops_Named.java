package com.neaterbits.query.sql.dsl.api;

public interface ISharedSubOperand_String_Ops_Named<MODEL, RESULT, AFTER extends ISharedFunction_After<MODEL, RESULT>>

	extends ISharedSubOperand_End_Named<MODEL, RESULT, String>,
		ISharedOperands_String_Named<MODEL, RESULT, AFTER, ISharedSubOperandsBuilder_String_Next_Named<MODEL, RESULT, AFTER>>,

	ISharedFunction_Next<MODEL, RESULT, AFTER> {

}
