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

	SubOperandsBuilder_Initial(ISharedCollector_Functions_Callback_Named<MODEL, RESULT, AFTER> func) {
		super(func);
	}

	@Override
	ISharedFunction_Next<MODEL, RESULT, AFTER> getNamedNoParamNext() {
		
		// Must return no-param version since implements separate interface
		return new SubOperandsBuilder_NoParam<>(this);
	}
 }
