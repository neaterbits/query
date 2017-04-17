package com.neaterbits.query.sql.dsl.api;

public interface ISharedSubOperandsBuilder_Named<MODEL, RESULT, R extends Comparable<R>, AFTER extends ISharedFunction_After<MODEL, RESULT>>

		extends 
			ISharedFunction_Next<MODEL, RESULT, AFTER>,
			ISharedFunctions_Arithmetic_Named<
				MODEL, RESULT,
				AFTER,
				
				ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>,
				ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>,
				ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>,
				ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>,
				ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>,
				ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>,
				ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>,
				ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>
			>,
			ISharedFunctions_Arithmetic_NoParam_Base<
						MODEL,
						RESULT,
						
						ISharedSubOperandsBuilder_NoParam_Named<MODEL, RESULT, R, AFTER>,
						ISharedSubOperandsBuilder_NoParam_Named<MODEL, RESULT, Double, AFTER>
			>

				{

}
