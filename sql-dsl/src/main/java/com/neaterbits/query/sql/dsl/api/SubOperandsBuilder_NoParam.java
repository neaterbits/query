package com.neaterbits.query.sql.dsl.api;

abstract class SubOperandsBuilder_NoParam<
	
	MODEL,
	RESULT,
	
	R extends Comparable<R>,
	
	AFTER extends ISharedFunction_After<MODEL,RESULT>,
	
	NAMED_RET extends ISharedFunction_After<MODEL,RESULT>,
	ALIAS_RET extends ISharedFunction_After<MODEL,RESULT>/*,

	
	NUMERIC_OPERAND_NEXT   extends ISharedFunction_Next<MODEL, RESULT, AFTER>,
	STRING_OPERAND_NEXT    extends ISharedFunction_Next<MODEL, RESULT, AFTER> */
	>

	extends SubOperandsBuilder<
		MODEL,
		RESULT,
		
		R,
		
		AFTER,
		
		NAMED_RET,
		ALIAS_RET,
		
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		
		
		ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, R, NAMED_RET>,
		ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, R, NAMED_RET>,
		
		ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, R, NAMED_RET>, 
		ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, R, NAMED_RET>,
		ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, R, NAMED_RET>,
		ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, R, NAMED_RET>,
		ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, R, NAMED_RET>,
		ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, R, NAMED_RET>,
		ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, R, NAMED_RET>,
		ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, R, NAMED_RET>, 
		ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, R, NAMED_RET>,
		ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, R, NAMED_RET>,
		ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, R, NAMED_RET>,
		ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, R, NAMED_RET>,
		ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, R, NAMED_RET>,
		ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, R, NAMED_RET>,
		
		ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, R, ALIAS_RET>,
		ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, R, ALIAS_RET>,
		
		ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, R, ALIAS_RET>, 
		ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, R, ALIAS_RET>,
		ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, R, ALIAS_RET>,
		ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, R, ALIAS_RET>,
		ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, R, ALIAS_RET>,
		ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, R, ALIAS_RET>,
		ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, R, ALIAS_RET>,
		ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, R, ALIAS_RET>, 
		ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, R, ALIAS_RET>,
		ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, R, ALIAS_RET>,
		ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, R, ALIAS_RET>,
		ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, R, ALIAS_RET>,
		ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, R, ALIAS_RET>,
		ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, R, ALIAS_RET>
	>

	// ISharedSubOperandsBuilder_NoParam_Named<MODEL, RESULT, R, NAMED_RET>
	{

	SubOperandsBuilder_NoParam(SubOperandsBuilder_Initial<MODEL, RESULT, R, AFTER, NAMED_RET, ALIAS_RET, ?, ?> toCopy) {
		super(toCopy);
	}

	/*
	@Override
	public ISharedSubOperandsBuilder_NoParam_Named<MODEL, RESULT, R, AFTER> abs() {
		return super.absNoParam();
	}

	@Override
	public ISharedSubOperandsBuilder_NoParam_Named<MODEL, RESULT, Double, AFTER> sqrt() {
		return super.sqrtNoParam();
	}
	
*/	
}
