package com.neaterbits.query.sql.dsl.api;

public class SubOperandsBuilder_NoParam_Alias<
		MODEL,
		RESULT, 
		R extends Comparable<R>,
		ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>>
		
	extends SubOperandsBuilder_NoParam<MODEL, RESULT, R, ALIAS_RET, ISharedFunction_After<MODEL, RESULT>, ALIAS_RET> 

	implements ISharedSubOperandsBuilder_NoParam_Alias<MODEL, RESULT, R, ALIAS_RET>,
	           ISharedSubOperandsBuilder_NoParam_End_Alias<MODEL, RESULT, R, ALIAS_RET>

{

	SubOperandsBuilder_NoParam_Alias(
			SubOperandsBuilder_Initial_Alias<
					MODEL, RESULT, R,
					ALIAS_RET> toCopy) {
		super(toCopy);
	}

	
	@Override
	public ISharedSubOperandsBuilder_NoParam_Alias<MODEL, RESULT, R, ALIAS_RET> abs() {
		return super.absNoParam();
	}

	@Override
	public ISharedSubOperandsBuilder_NoParam_Alias<MODEL, RESULT, Double, ALIAS_RET> sqrt() {
		return super.sqrtNoParam();
	}
}
