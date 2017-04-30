package com.neaterbits.query.sql.dsl.api;

public interface ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R extends Comparable<R>, AFTER extends ISharedFunction_After<MODEL, RESULT>>

	extends ISharedSubOperand_End_Alias<MODEL, RESULT, R>,
			ISharedOperands_Numeric_Alias_All<
					MODEL,
					RESULT,
					AFTER,
					ISharedSubOperandsBuilder_Numeric_Next_Alias<MODEL, RESULT, R, AFTER>,
					ISharedSubOperandsBuilder_Numeric_Next_Alias<MODEL, RESULT, R, AFTER>>,

	ISharedFunction_Next<MODEL, RESULT, AFTER> {

}
