package com.neaterbits.query.sql.dsl.api;

final class SubOperandsBuilder_Initial_Alias<
	MODEL,
	RESULT,
	R extends Comparable<R>,
	
	ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>
	>
	
	extends SubOperandsBuilder_Initial<
		MODEL,
		RESULT, 
		R,
		ALIAS_RET,
		
		ISharedFunction_After<MODEL,RESULT>,
		ALIAS_RET,
		ISharedFunction_After<MODEL,RESULT>,

		ISharedFunction_Next<MODEL,RESULT, ALIAS_RET>,
		ISharedFunction_Next<MODEL,RESULT, ALIAS_RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Alias<MODEL,RESULT,R,ALIAS_RET>,
		ISharedSubOperandsBuilder_String_Next_Alias<MODEL,RESULT,ALIAS_RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Alias<MODEL,RESULT,R,ALIAS_RET>,
		ISharedSubOperandsBuilder_String_Next_Alias<MODEL,RESULT,ALIAS_RET>
		>
	
	implements 
	
		ISharedSubOperandsBuilder_Alias<MODEL, RESULT, R, ALIAS_RET>,
		ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>
	
	{
	
		/*
	@Override
	ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> getNamedNoParamNext(
		ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> def) {
	
		//return new SubOperandsBuilder_NoParam_Named<>(this);
		throw new UnsupportedOperationException("N/A");
	}
	*/
		
	@Override
	ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> getAliasNoParamNext(
			ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> def) {

		return new SubOperandsBuilder_NoParam_Alias<>(this);
	}
	
	@Override
	public ISharedSubOperandsBuilder_NoParam_Alias<MODEL, RESULT, R, ALIAS_RET> abs() {
		return super.absAliasNoParam();
	}
	

	@Override
	public ISharedSubOperandsBuilder_NoParam_Alias<MODEL, RESULT, Double, ALIAS_RET> sqrt() {
		return super.sqrtAliasNoParam();
	}
}
