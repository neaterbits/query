package com.neaterbits.query.sql.dsl.api;

final class SubOperandsBuilder_NoParam<
	
	MODEL,
	RESULT,
	
	R extends Comparable<R>,
	
	AFTER extends ISharedFunction_After<MODEL,RESULT>>

	extends SubOperandsBuilder<
		MODEL,
		RESULT,
		
		R,
		
		AFTER,
		
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		
		ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, R, AFTER>,
		ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, R, AFTER>,
		ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, R, AFTER>, 
		ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, R, AFTER>,
		ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, R, AFTER>,
		ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, R, AFTER>,
		
		ISharedSubOperandsBuilder_NoParam_Named<MODEL, RESULT, R, AFTER>,
		ISharedSubOperandsBuilder_NoParam_Named<MODEL, RESULT, Double, AFTER>,
		
		ISharedFunction_Next<MODEL, RESULT, AFTER>
	>

	implements ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, R, AFTER>,
			   ISharedSubOperandsBuilder_NoParam_Named<MODEL, RESULT, R, AFTER> {

	SubOperandsBuilder_NoParam(SubOperandsBuilder_Initial<MODEL, RESULT, R, AFTER> toCopy) {
		super(toCopy);
	}
}
