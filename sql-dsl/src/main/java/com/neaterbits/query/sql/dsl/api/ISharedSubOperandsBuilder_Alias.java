package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedSubOperandsBuilder_Alias<MODEL, RESULT, R extends Comparable<R>, AFTER extends ISharedFunction_After<MODEL, RESULT>>

	extends 
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunctions_Arithmetic_Alias_All<
			MODEL, RESULT,
			AFTER,
			
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, Byte, AFTER>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, Short, AFTER>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, Integer, AFTER>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, Long, AFTER>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, BigInteger, AFTER>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, Float, AFTER>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, Double, AFTER>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, BigDecimal, AFTER>
		>,
		ISharedFunctions_Arithmetic_NoParam_Base<
					MODEL,
					RESULT,
					
					ISharedSubOperandsBuilder_NoParam_Alias<MODEL, RESULT, R, AFTER>,
					ISharedSubOperandsBuilder_NoParam_Alias<MODEL, RESULT, Double, AFTER>
		>,
		ISharedFunctions_String_Alias_All<
					MODEL,
					RESULT,
					AFTER,
					ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, AFTER>,
					ISharedSubOperand_String_Ops_Alias<MODEL, RESULT, AFTER>
		>

		{

}
