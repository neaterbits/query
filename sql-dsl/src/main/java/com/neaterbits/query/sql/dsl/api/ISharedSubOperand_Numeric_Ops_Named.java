package com.neaterbits.query.sql.dsl.api;

public interface ISharedSubOperand_Numeric_Ops_Named<
		MODEL,
		RESULT,
		R extends Comparable<R>, 
		
		AFTER extends ISharedFunction_After<MODEL, RESULT>>

	extends ISharedSubOperand_End_Named<MODEL, RESULT, R>,
			ISharedOperands_Numeric_Named<MODEL, RESULT, R, AFTER, ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, R, AFTER>>,
	
		ISharedFunction_Next<MODEL, RESULT, AFTER> {

}
