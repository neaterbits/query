package com.neaterbits.query.sql.dsl.api;


abstract class SubOperandsBuilder<

			MODEL,

			RESULT,
			R extends Comparable<R>,

			
			OPERAND_RET extends ISharedFunction_After<MODEL, RESULT>,
			
			NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
			ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>,
			
			NUMERIC_OPERAND_NEXT    extends ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,
			STRING_OPERAND_NEXT    extends ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,

			NAMED_SUM_LONG_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_COUNT_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			
			NAMED_BYTE_RET 		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_SHORT_RET 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_INTEGER_RET 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_LONG_RET	 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_BIGINTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_FLOAT_RET	 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_DOUBLE_RET	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_STRING_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_DATE_RET 		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_CALENDAR_RET	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_SQLDATE_RET 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_SQLTIME_RET 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_SQLTIMESTAMP_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,

			
			ALIAS_SUM_LONG_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_COUNT_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			
			ALIAS_BYTE_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_SHORT_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_INTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_LONG_RET	 extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_BIGINTEGER_RET	 extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_FLOAT_RET	 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_DOUBLE_RET	 extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_STRING_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_DATE_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_CALENDAR_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_SQLDATE_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_SQLTIME_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_SQLTIMESTAMP_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>

			/*
			NO_PARAM_ARITHMETIC_SAME_TYPE_RET,
			NO_PARAM_ARITHMETIC_DOUBLE_RET,
			
			NO_PARAM_STRING_RET
			*/>

	extends Collector_ExpressionList<
			MODEL, RESULT, R,

			OPERAND_RET,
			
			NAMED_RET,
			ALIAS_RET,
			
			NUMERIC_OPERAND_NEXT,
			STRING_OPERAND_NEXT,
			
			NAMED_SUM_LONG_RET,
			NAMED_COUNT_RET,
			
			NAMED_BYTE_RET,
			NAMED_SHORT_RET,
			NAMED_INTEGER_RET,
			NAMED_LONG_RET,
			NAMED_BIGINTEGER_RET,
			NAMED_FLOAT_RET,
			NAMED_DOUBLE_RET,
			NAMED_BIGDECIMAL_RET,
			NAMED_STRING_RET,
			NAMED_DATE_RET,
			NAMED_CALENDAR_RET,
			NAMED_SQLDATE_RET,
			NAMED_SQLTIME_RET,
			NAMED_SQLTIMESTAMP_RET,
			
			ALIAS_SUM_LONG_RET,
			ALIAS_COUNT_RET,
			
			ALIAS_BYTE_RET,
			ALIAS_SHORT_RET,
			ALIAS_INTEGER_RET,
			ALIAS_LONG_RET,
			ALIAS_BIGINTEGER_RET,
			ALIAS_FLOAT_RET,
			ALIAS_DOUBLE_RET,
			ALIAS_BIGDECIMAL_RET,
			ALIAS_STRING_RET,
			ALIAS_DATE_RET,
			ALIAS_CALENDAR_RET,
			ALIAS_SQLDATE_RET,
			ALIAS_SQLTIME_RET,
			ALIAS_SQLTIMESTAMP_RET
			>

/*
		extends Collector_NestedFunctions_Named<
		
		
			MODEL,
			RESULT,
			
			AFTER,
			
			SUM_LONG_RET,
			COUNT_RET,
			
			SHORT_RET,
			INTEGER_RET,
			LONG_RET,
			DOUBLE_RET,
			BIGDECIMAL_RET,
			STRING_RET,
			
			NO_PARAM_ARITHMETIC_SAME_TYPE_RET,
			NO_PARAM_ARITHMETIC_DOUBLE_RET,
			
			NO_PARAM_STRING_RET
			
			>
			*/

{
		
	SubOperandsBuilder() {

	}
		
	SubOperandsBuilder(SubOperandsBuilder<MODEL, RESULT, R, OPERAND_RET,
			?, ?, ?, ?,
			?, ?,
			?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
			?, ?,
			?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> toCopy) {
		super(toCopy);
	}
	
	private class SubNamedFunctions extends NamedFunctions
	
		implements ISharedSubOperandsBuilder_NoParam_Named {

		SubNamedFunctions(ISharedCollector_Functions_Callback func) {
			super(func);
		}
		
	}
	

	@Override
	final NamedFunctions createNamedFunctions(
			ISharedCollector_Functions_Callback<MODEL, RESULT, NAMED_RET> func) {
		return new SubNamedFunctions(func);
	}


	private class SubAliasFunctions extends AliasFunctions
	
		implements ISharedSubOperandsBuilder_NoParam_Alias {

		SubAliasFunctions(ISharedCollector_Functions_Callback func) {
			super(func);
		}
	}
	
	@Override
	final AliasFunctions createAliasFunctions(ISharedCollector_Functions_Callback<MODEL, RESULT, ALIAS_RET> func) {
		return new SubAliasFunctions(func);
	}
}
