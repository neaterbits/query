package com.neaterbits.query.sql.dsl.api;

final class SubOperandsBuilder_NoParam_Undecided<
		MODEL,
		RESULT, 
		R extends Comparable<R>,
		
		NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
		ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>,
		UNDECIDED_RET extends ISharedFunction_After<MODEL, RESULT>
	> 

	extends SubOperandsBuilder_NoParam<MODEL, RESULT, R, UNDECIDED_RET, ISharedFunction_After<MODEL, RESULT>, ISharedFunction_After<MODEL, RESULT>, UNDECIDED_RET> 
	implements ISharedSubOperandsBuilder_NoParam_Undecided<MODEL, RESULT, R, NAMED_RET, ALIAS_RET, UNDECIDED_RET>,
			   ISharedSubOperandsBuilder_NoParam_End_Undecided<MODEL, RESULT, R, UNDECIDED_RET> {


	SubOperandsBuilder_NoParam_Undecided(SubOperandsBuilder_Initial<MODEL, RESULT, R, UNDECIDED_RET, ISharedFunction_After<MODEL, RESULT>, ISharedFunction_After<MODEL, RESULT>, UNDECIDED_RET, ?, ?> toCopy) {
		super(toCopy);
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
