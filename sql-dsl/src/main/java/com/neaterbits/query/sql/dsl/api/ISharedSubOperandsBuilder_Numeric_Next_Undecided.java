package com.neaterbits.query.sql.dsl.api;

public interface ISharedSubOperandsBuilder_Numeric_Next_Undecided<
		MODEL,
		RESULT,
		R extends Comparable<R>, 
		AFTER extends ISharedFunction_After<MODEL, RESULT>>

	extends 
		ISharedSubOperand_End_Undecided<MODEL, RESULT, R>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedOperands_Numeric_Undecided_All<
			MODEL,
			RESULT,
			AFTER,
			ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, R, AFTER>,
			ISharedSubOperandsBuilder_Numeric_Next_Alias<MODEL, RESULT, R, AFTER>,
			ISharedSubOperandsBuilder_Numeric_Next_Undecided<MODEL, RESULT, R, AFTER>> {
}
