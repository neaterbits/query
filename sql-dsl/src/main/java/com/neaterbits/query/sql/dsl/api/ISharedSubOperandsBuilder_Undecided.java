package com.neaterbits.query.sql.dsl.api;

public interface ISharedSubOperandsBuilder_Undecided<
		MODEL,
		RESULT,
		R extends Comparable<R>, 
		NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
		ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>,
		UNDECIDED_RET extends ISharedFunction_After<MODEL, RESULT>>

	extends 
		//ISharedFunction_Next<MODEL, RESULT, NAMED_RET, ALIAS_RET>,
		ISharedFunctions_Arithmetic_Undecided<
			MODEL, RESULT,
			
			NAMED_RET,
			ALIAS_RET,
			UNDECIDED_RET,
			
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			
			ISharedSubOperand_Numeric_Ops_Undecided<MODEL, RESULT, R, UNDECIDED_RET>,
			ISharedSubOperand_Numeric_Ops_Undecided<MODEL, RESULT, R, UNDECIDED_RET>,
			ISharedSubOperand_Numeric_Ops_Undecided<MODEL, RESULT, R, UNDECIDED_RET>,
			ISharedSubOperand_Numeric_Ops_Undecided<MODEL, RESULT, R, UNDECIDED_RET>,
			ISharedSubOperand_Numeric_Ops_Undecided<MODEL, RESULT, R, UNDECIDED_RET>,
			ISharedSubOperand_Numeric_Ops_Undecided<MODEL, RESULT, R, UNDECIDED_RET>,
			ISharedSubOperand_Numeric_Ops_Undecided<MODEL, RESULT, R, UNDECIDED_RET>,
			ISharedSubOperand_Numeric_Ops_Undecided<MODEL, RESULT, R, UNDECIDED_RET>

			>,
		ISharedFunctions_Arithmetic_NoParam_Base<
					MODEL,
					RESULT,
					
					ISharedSubOperandsBuilder_NoParam_Undecided<MODEL, RESULT, R, NAMED_RET, ALIAS_RET>,
					ISharedSubOperandsBuilder_NoParam_Undecided<MODEL, RESULT, Double, NAMED_RET, ALIAS_RET>
		>,
					
		// can call subs to String functions too
		ISharedFunctions_String_Undecided<
			MODEL, RESULT, NAMED_RET, ALIAS_RET,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>, 
			ISharedSubOperand_String_Ops_Named<MODEL, RESULT, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>, 
			ISharedSubOperand_String_Ops_Alias<MODEL, RESULT, ALIAS_RET>>
	
			{

}
