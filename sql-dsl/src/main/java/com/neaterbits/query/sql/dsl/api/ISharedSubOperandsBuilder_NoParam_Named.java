package com.neaterbits.query.sql.dsl.api;

public interface ISharedSubOperandsBuilder_NoParam_Named<MODEL, RESULT, T, AFTER extends ISharedFunction_After<MODEL, RESULT>> 

	extends
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunctions_Arithmetic_Named_All<

		MODEL,
				RESULT,
				
				AFTER,
				ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, T, AFTER>,
				ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, T, AFTER>,
				ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, T, AFTER>,
				ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, T, AFTER>,
				ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, T, AFTER>,
				ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, T, AFTER>,
				ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, T, AFTER>,
				ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, T, AFTER>
		>,

		ISharedFunctions_Arithmetic_NoParam_Base<
				MODEL,
				RESULT,
				
				ISharedSubOperandsBuilder_NoParam_Named<MODEL, RESULT, T, AFTER>,
				ISharedSubOperandsBuilder_NoParam_Named<MODEL, RESULT, Double, AFTER>
			> {

}
