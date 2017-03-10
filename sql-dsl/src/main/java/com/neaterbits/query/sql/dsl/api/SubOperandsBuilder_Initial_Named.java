package com.neaterbits.query.sql.dsl.api;

final class SubOperandsBuilder_Initial_Named<
		MODEL,
		RESULT,
		R extends Comparable<R>,
		
		NAMED_RET extends ISharedFunction_After<MODEL, RESULT>
		>

		extends SubOperandsBuilder_Initial<
			MODEL,
			RESULT, 
			R,
			NAMED_RET,
			NAMED_RET,
			ISharedFunction_After<MODEL,RESULT>>

		implements 
		
			ISharedSubOperandsBuilder_Named<MODEL, RESULT, R, NAMED_RET>

{

	@Override
	ISharedFunction_Next<MODEL, RESULT, NAMED_RET> getNamedNoParamNext(
			ISharedFunction_Next<MODEL, RESULT, NAMED_RET> def) {
		
		return new SubOperandsBuilder_NoParam_Named<>(this);
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
