package com.neaterbits.query.sql.dsl.api;

public interface ISharedSubOperandsBuilder_Next_Alias<MODEL, RESULT, R extends Comparable<R>, AFTER extends ISharedFunction_After<MODEL, RESULT>>

	extends 
		ISharedSubOperand_End_Alias<MODEL, RESULT, R>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedOperands_Numeric_Alias<
			MODEL,
			RESULT,
			R,
			AFTER,
			ISharedSubOperandsBuilder_Next_Alias<MODEL, RESULT, R, AFTER>> {
}
