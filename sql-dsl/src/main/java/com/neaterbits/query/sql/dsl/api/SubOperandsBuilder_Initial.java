package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

abstract class SubOperandsBuilder_Initial<

		MODEL,
		RESULT,

		R extends Comparable<R>,
		
		AFTER extends ISharedFunction_After<MODEL,RESULT>,
		
		NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
		ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>,
		UNDECIDED_RET extends ISharedFunction_After<MODEL, RESULT>,
		
		NAMED_NUMERIC_OPERAND_NEXT    extends ISharedFunction_Next<MODEL, RESULT, AFTER>,
		NAMED_STRING_OPERAND_NEXT    extends ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ALIAS_NUMERIC_OPERAND_NEXT    extends ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ALIAS_STRING_OPERAND_NEXT    extends ISharedFunction_Next<MODEL, RESULT, AFTER>,
		COMMON_NUMERIC_OPERAND_NEXT    extends ISharedFunction_Next<MODEL, RESULT, AFTER>,
		COMMON_STRING_OPERAND_NEXT    extends ISharedFunction_Next<MODEL, RESULT, AFTER>
		
	>

	extends SubOperandsBuilder<
			MODEL,
			RESULT,
			
			R,
			
			AFTER,
			
			NAMED_RET,
			ALIAS_RET,
			UNDECIDED_RET,

			NAMED_NUMERIC_OPERAND_NEXT,
			NAMED_STRING_OPERAND_NEXT,
			ALIAS_NUMERIC_OPERAND_NEXT,
			ALIAS_STRING_OPERAND_NEXT,
			COMMON_NUMERIC_OPERAND_NEXT,
			COMMON_STRING_OPERAND_NEXT,
			
			
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, Byte, NAMED_RET>, 
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, Short, NAMED_RET>, 
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, Integer, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, Long, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, BigInteger, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, Float, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, Double, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, BigDecimal, NAMED_RET>,
			ISharedSubOperand_String_Ops_Named<MODEL, RESULT, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_NumericSQLTimeType_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_NumericSQLTimeType_Ops_Named<MODEL, RESULT, R, NAMED_RET>,
			ISharedSubOperand_NumericSQLTimeType_Ops_Named<MODEL, RESULT, R, NAMED_RET>,

			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,

			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, Byte, ALIAS_RET>, 
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, Short, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, Integer, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, Long, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, BigInteger, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, Float, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, Double, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, BigDecimal, ALIAS_RET>,
			ISharedSubOperand_String_Ops_Alias<MODEL, RESULT, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_Numeric_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_NumericSQLTimeType_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_NumericSQLTimeType_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			ISharedSubOperand_NumericSQLTimeType_Ops_Alias<MODEL, RESULT, R, ALIAS_RET>,
			
			
			ISharedSubOperand_Numeric_Ops_Undecided<MODEL, RESULT, R, UNDECIDED_RET>,
			ISharedSubOperand_Numeric_Ops_Undecided<MODEL, RESULT, R, UNDECIDED_RET>,
			ISharedSubOperand_Numeric_Ops_Undecided<MODEL, RESULT, R, UNDECIDED_RET>,

			ISharedSubOperand_Numeric_Ops_Undecided<MODEL, RESULT, Byte, UNDECIDED_RET>, 
			ISharedSubOperand_Numeric_Ops_Undecided<MODEL, RESULT, Short, UNDECIDED_RET>,
			ISharedSubOperand_Numeric_Ops_Undecided<MODEL, RESULT, Integer, UNDECIDED_RET>,
			ISharedSubOperand_Numeric_Ops_Undecided<MODEL, RESULT, Long, UNDECIDED_RET>,
			ISharedSubOperand_Numeric_Ops_Undecided<MODEL, RESULT, BigInteger, UNDECIDED_RET>,
			ISharedSubOperand_Numeric_Ops_Undecided<MODEL, RESULT, Float, UNDECIDED_RET>,
			ISharedSubOperand_Numeric_Ops_Undecided<MODEL, RESULT, Double, UNDECIDED_RET>,
			ISharedSubOperand_Numeric_Ops_Undecided<MODEL, RESULT, BigDecimal, UNDECIDED_RET>,
			ISharedSubOperand_String_Ops_Undecided<MODEL, RESULT, UNDECIDED_RET>,
			ISharedSubOperand_Numeric_Ops_Undecided<MODEL, RESULT, R, UNDECIDED_RET>,
			ISharedSubOperand_Numeric_Ops_Undecided<MODEL, RESULT, R, UNDECIDED_RET>,
			ISharedSubOperand_NumericSQLTimeType_Ops_Undecided<MODEL, RESULT, R, UNDECIDED_RET>,
			ISharedSubOperand_NumericSQLTimeType_Ops_Undecided<MODEL, RESULT, R, UNDECIDED_RET>,
			ISharedSubOperand_NumericSQLTimeType_Ops_Undecided<MODEL, RESULT, R, UNDECIDED_RET>
			
			>

 {

 }
