package com.neaterbits.query.sql.dsl.api;

public interface ISharedSubOperandsBuilder_NoParam_Alias<MODEL, RESULT, T, AFTER extends ISharedFunction_After<MODEL, RESULT>> 

	extends
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunctions_Arithmetic_Alias<
	
			MODEL,
			RESULT,
			
			AFTER,
			ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, T, AFTER>,
			ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, T, AFTER>,
			ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, T, AFTER>,
			ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, T, AFTER>,
			ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, T, AFTER>
	>,

	ISharedFunctions_Arithmetic_NoParam_Base<
			MODEL,
			RESULT,
			
			ISharedSubOperandsBuilder_NoParam_Alias<MODEL, RESULT, T, AFTER>,
			ISharedSubOperandsBuilder_NoParam_Alias<MODEL, RESULT, Double, AFTER>
		> {

}
