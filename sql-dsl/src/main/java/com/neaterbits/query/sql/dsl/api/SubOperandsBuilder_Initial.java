package com.neaterbits.query.sql.dsl.api;

final class SubOperandsBuilder_Initial<

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
			
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>, 
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>,
			
			ISharedSubOperandsBuilder_NoParam_Named<MODEL, RESULT, R, AFTER>,
			ISharedSubOperandsBuilder_NoParam_Named<MODEL, RESULT, Double, AFTER>,
			
			ISharedFunction_Next<MODEL, RESULT, AFTER>
			>

		implements ISharedSubOperandsBuilder_Named<MODEL, RESULT, R, AFTER>
 {

	SubOperandsBuilder_Initial() {

	}
	
	
	ISharedFunction_Next<MODEL, RESULT, AFTER> getNamedNoParamNext(ISharedFunction_Next<MODEL, RESULT, AFTER> def) {
		return new SubOperandsBuilder_NoParam<>(this);
	}

	@Override
	public ISharedSubOperandsBuilder_NoParam_Named<MODEL, RESULT, R, AFTER> abs() {
		return super.absNoParam();
	}

	@Override
	public ISharedSubOperandsBuilder_NoParam_Named<MODEL, RESULT, Double, AFTER> sqrt() {
		return super.sqrtNoParam();
	}
 }
