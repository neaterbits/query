package com.neaterbits.query.sql.dsl.api;

public interface ISharedSubOperandsBuilder_NoParam_Undecided<MODEL, RESULT, T,
		NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
		ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>> 

	extends
		ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		ISharedFunctions_Arithmetic_Undecided<
	
				MODEL,
				RESULT,
				
				NAMED_RET,
				ALIAS_RET,
				
				ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, T, NAMED_RET>,
				ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, T, NAMED_RET>,
				ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, T, NAMED_RET>,
				ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, T, NAMED_RET>,
				ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, T, NAMED_RET>,
				ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, T, NAMED_RET>,
				ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, T, NAMED_RET>,
				ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, T, NAMED_RET>,

				ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, T, ALIAS_RET>,
				ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, T, ALIAS_RET>,
				ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, T, ALIAS_RET>,
				ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, T, ALIAS_RET>,
				ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, T, ALIAS_RET>,
				ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, T, ALIAS_RET>,
				ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, T, ALIAS_RET>,
				ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, T, ALIAS_RET>
		>,
	
		ISharedFunctions_Arithmetic_NoParam_Base<
				MODEL,
				RESULT,
				
				ISharedSubOperandsBuilder_NoParam_Undecided<MODEL, RESULT, T, NAMED_RET, ALIAS_RET>,
				ISharedSubOperandsBuilder_NoParam_Undecided<MODEL, RESULT, Double, NAMED_RET, ALIAS_RET>
			> {

}
