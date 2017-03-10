package com.neaterbits.query.sql.dsl.api;

final class SubOperandsBuilder_NoParam_Named<
			MODEL,
			RESULT, 
			R extends Comparable<R>,
			
			NAMED_RET extends ISharedFunction_After<MODEL, RESULT>
			> 

	extends SubOperandsBuilder_NoParam<MODEL, RESULT, R, NAMED_RET, NAMED_RET, ISharedFunction_After<MODEL, RESULT>> {

	
	SubOperandsBuilder_NoParam_Named(SubOperandsBuilder_Initial<MODEL, RESULT, R, NAMED_RET, NAMED_RET, ISharedFunction_After<MODEL, RESULT>> toCopy) {
		super(toCopy);
	}

	@Override
	public ISharedSubOperandsBuilder_NoParam_Named<MODEL, RESULT, R, NAMED_RET> abs() {
		return super.absNoParam();
	}

	@Override
	public ISharedSubOperandsBuilder_NoParam_Named<MODEL, RESULT, Double, NAMED_RET> sqrt() {
		return super.sqrtNoParam();
	}
}
