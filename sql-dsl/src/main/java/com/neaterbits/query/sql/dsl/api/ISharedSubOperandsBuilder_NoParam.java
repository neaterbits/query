package com.neaterbits.query.sql.dsl.api;

public interface ISharedSubOperandsBuilder_NoParam<MODEL, RESULT, T, AFTER extends ISharedFunction_After<MODEL, RESULT>> 

	extends
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunctions_Arithmetic_Named<
				MODEL,
				RESULT,
				
				AFTER,
/*
				ISharedSubOperand_End<MODEL, RESULT, T>,
				ISharedSubOperand_End<MODEL, RESULT, T>,
				ISharedSubOperand_End<MODEL, RESULT, T>,
				ISharedSubOperand_End<MODEL, RESULT, T>,
				ISharedSubOperand_End<MODEL, RESULT, T>
*/
				ISharedSubOperandsBuilder_NoParam_End<MODEL, RESULT, T, AFTER>,
				ISharedSubOperandsBuilder_NoParam_End<MODEL, RESULT, T, AFTER>,
				ISharedSubOperandsBuilder_NoParam_End<MODEL, RESULT, T, AFTER>,
				ISharedSubOperandsBuilder_NoParam_End<MODEL, RESULT, T, AFTER>,
				ISharedSubOperandsBuilder_NoParam_End<MODEL, RESULT, T, AFTER>
				
				/*
				ISharedSubOperandsBuilder_NoParam<MODEL, RESULT, T, AFTER>,
				ISharedSubOperandsBuilder_NoParam<MODEL, RESULT, T, AFTER>,
				ISharedSubOperandsBuilder_NoParam<MODEL, RESULT, T, AFTER>,
				ISharedSubOperandsBuilder_NoParam<MODEL, RESULT, T, AFTER>,
				ISharedSubOperandsBuilder_NoParam<MODEL, RESULT, T, AFTER>
				*/
		>,

		ISharedFunctions_Arithmetic_NoParam_Base<
				MODEL,
				RESULT,
				
				ISharedSubOperandsBuilder_NoParam<MODEL, RESULT, T, AFTER>,
				ISharedSubOperandsBuilder_NoParam<MODEL, RESULT, Double, AFTER>
			> {

}
