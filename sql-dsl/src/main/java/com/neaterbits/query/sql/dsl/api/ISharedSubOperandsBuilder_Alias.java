package com.neaterbits.query.sql.dsl.api;

public interface ISharedSubOperandsBuilder_Alias<MODEL, RESULT, R extends Comparable<R>, AFTER extends ISharedFunction_After<MODEL, RESULT>>

	extends 
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunctions_Arithmetic_Alias<
			MODEL, RESULT,
			AFTER,
			
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, AFTER>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, AFTER>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, AFTER>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, AFTER>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, AFTER>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, AFTER>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, AFTER>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, AFTER>
		>,
		ISharedFunctions_Arithmetic_NoParam_Base<
					MODEL,
					RESULT,
					
					ISharedSubOperandsBuilder_NoParam_Alias<MODEL, RESULT, R, AFTER>,
					ISharedSubOperandsBuilder_NoParam_Alias<MODEL, RESULT, Double, AFTER>
		>

		{

}
