package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

final class Collector_Expression_List_Sub<

	MODEL,
	RESULT,
	R extends Comparable<R>,
	
	OPERAND_RET extends ISharedFunction_After<MODEL, RESULT>,
	
	NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
	ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>,
	UNDECIDED_RET extends ISharedFunction_After<MODEL, RESULT>

	>

	extends Collector_ExpressionList<

		MODEL,
		RESULT,

		R,
		
		OPERAND_RET,
		
		NAMED_RET,
		ALIAS_RET,
		UNDECIDED_RET,
		
		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, R, OPERAND_RET>,
		ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,// STRING_OPERAND_NEXT,
		
		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, Long, NAMED_RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, Long, NAMED_RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, Integer, NAMED_RET>,

		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, Byte, NAMED_RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, Short, NAMED_RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, Integer, NAMED_RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, Long, NAMED_RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, BigInteger, NAMED_RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, Float, NAMED_RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, Double, NAMED_RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, BigDecimal, NAMED_RET>,
		ISharedSubOperandsBuilder_String_Next_Named<MODEL, RESULT, NAMED_RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, java.util.Date, NAMED_RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, java.util.Calendar, NAMED_RET>,
		ISharedSubOperandsBuilder_NumericSQLTimeType_Next_Named<MODEL, RESULT, java.sql.Date, NAMED_RET>,
		ISharedSubOperandsBuilder_NumericSQLTimeType_Next_Named<MODEL, RESULT, java.sql.Time, NAMED_RET>,
		ISharedSubOperandsBuilder_NumericSQLTimeType_Next_Named<MODEL, RESULT, java.sql.Timestamp, NAMED_RET>,


		ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		
		ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		
		ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		
		ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>
		
		
		/*
		ISharedSubOperandsBuilder_Numeric_Next_Alias<MODEL, RESULT, Long, ALIAS_RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Alias<MODEL, RESULT, Long, ALIAS_RET>,
		
		ISharedSubOperandsBuilder_Numeric_Next_Alias<MODEL, RESULT, Short, ALIAS_RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Alias<MODEL, RESULT, Integer, ALIAS_RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Alias<MODEL, RESULT, Long, ALIAS_RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Alias<MODEL, RESULT, Double, ALIAS_RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Alias<MODEL, RESULT, BigDecimal, ALIAS_RET>,
		ISharedSubOperandsBuilder_String_Next_Alias<MODEL, RESULT, ALIAS_RET>
		*/

>

	implements
		ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, OPERAND_RET>

/*, Cannot implement at this level
		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, R, RET>,
		ISharedSubOperandsBuilder_String_Next_Named<MODEL, RESULT, RET> 
		*/
		{

	Collector_Expression_List_Sub(Expression expression) {
		super(expression, "sub");
	}

	@Override
	NamedFunctions createNamedFunctions(ISharedCollector_Functions_Callback<MODEL, RESULT, NAMED_RET> func) {
		return new NamedFunctions(func);
	}

	@Override
	AliasFunctions createAliasFunctions(ISharedCollector_Functions_Callback<MODEL, RESULT, ALIAS_RET> func) {
		return new AliasFunctions(func);
	}
}
