package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedSubOperandsBuilder_Named<MODEL, RESULT, R extends Comparable<R>, AFTER extends ISharedFunction_After<MODEL, RESULT>>

		extends 
			ISharedFunction_Next<MODEL, RESULT, AFTER>,
			ISharedFunctions_Arithmetic_Named_All<
				MODEL, RESULT,
				AFTER,
				
				ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, Byte, AFTER>,
				ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, Short, AFTER>,
				ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, Integer, AFTER>,
				ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, Long, AFTER>,
				ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, BigInteger, AFTER>,
				ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, Float, AFTER>,
				ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, Double, AFTER>,
				ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, BigDecimal, AFTER>
			>,
			ISharedFunctions_Arithmetic_NoParam_Base<
						MODEL,
						RESULT,
						
						ISharedSubOperandsBuilder_NoParam_Named<MODEL, RESULT, R, AFTER>,
						ISharedSubOperandsBuilder_NoParam_Named<MODEL, RESULT, Double, AFTER>
			>,
						
			// can call subs to String functions too
			ISharedFunctions_String_Named_All<
				MODEL, RESULT, AFTER,
				ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>, 
				ISharedSubOperand_String_Ops_Named<MODEL, RESULT, AFTER>>

				{

}
