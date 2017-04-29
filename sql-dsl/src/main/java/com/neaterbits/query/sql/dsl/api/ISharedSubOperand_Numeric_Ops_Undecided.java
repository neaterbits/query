package com.neaterbits.query.sql.dsl.api;

public interface ISharedSubOperand_Numeric_Ops_Undecided<MODEL, RESULT, R extends Comparable<R>, AFTER extends ISharedFunction_After<MODEL, RESULT>>

	extends ISharedSubOperand_End_Undecided<MODEL, RESULT, R>,
			ISharedOperands_Numeric_Undecided<MODEL, RESULT, AFTER, ISharedSubOperandsBuilder_Numeric_Next_Undecided<MODEL, RESULT, R, AFTER>>,
	
	ISharedFunction_Next<MODEL, RESULT, AFTER> {

}
