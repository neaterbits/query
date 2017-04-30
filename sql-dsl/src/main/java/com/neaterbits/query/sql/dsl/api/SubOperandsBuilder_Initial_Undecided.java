package com.neaterbits.query.sql.dsl.api;

final class SubOperandsBuilder_Initial_Undecided<
	MODEL,
	RESULT,
	R extends Comparable<R>,
	
	NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
	ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>,
	UNDECIDED_RET extends ISharedFunction_After<MODEL, RESULT>
	>
	
	extends SubOperandsBuilder_Initial<
		MODEL,
		RESULT, 
		R,
		UNDECIDED_RET,
		
		NAMED_RET,
		ALIAS_RET,
		UNDECIDED_RET,

		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL,RESULT,R,UNDECIDED_RET>,
		ISharedSubOperandsBuilder_String_Next_Named<MODEL,RESULT,UNDECIDED_RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Alias<MODEL,RESULT,R,UNDECIDED_RET>,
		ISharedSubOperandsBuilder_String_Next_Alias<MODEL,RESULT,UNDECIDED_RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Undecided<MODEL,RESULT,R,UNDECIDED_RET>,
		ISharedSubOperandsBuilder_String_Next_Undecided<MODEL,RESULT,UNDECIDED_RET>
		>
	
	implements 
	
		ISharedSubOperandsBuilder_Undecided<MODEL, RESULT, R, NAMED_RET, ALIAS_RET, UNDECIDED_RET>,
		ISharedSubOperand_Numeric_Ops_Undecided<MODEL, RESULT, R, UNDECIDED_RET>
	
	{
	
	@Override
	ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET> getUndecidedNoParamNext(ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET> def) {
	
		return new SubOperandsBuilder_NoParam_Undecided<>(this);
	}
	
	@Override
	public ISharedSubOperandsBuilder_NoParam_Undecided<MODEL, RESULT, R, NAMED_RET, ALIAS_RET, UNDECIDED_RET> abs() {
		return super.absUndecidedNoParam();
	}
	
	
	@Override
	public ISharedSubOperandsBuilder_NoParam_Undecided<MODEL, RESULT, Double, NAMED_RET, ALIAS_RET, UNDECIDED_RET> sqrt() {
		return super.sqrtUndecidedNoParam();
	}
}
