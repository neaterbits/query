package com.neaterbits.query.sql.dsl.api;

abstract class Collector_NestedFunctions_Undecided<

		MODEL, RESULT,

		NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
		ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>,
		UNDECIDED_RET extends ISharedFunction_After<MODEL, RESULT>,

		NAMED_SUM_LONG_RET  extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_COUNT_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_LENGTH_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		
		NAMED_BYTE_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SHORT_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_INTEGER_RET	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_LONG_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_BIGINTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_FLOAT_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_DOUBLE_RET	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_STRING_RET 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_DATE_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_CALENDAR_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SQLDATE_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SQLTIME_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SQLTIMESTAMP_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		
		
		ALIAS_SUM_LONG_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_COUNT_RET		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_LENGTH_RET	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,

		ALIAS_BYTE_RET 		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SHORT_RET 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_INTEGER_RET 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_LONG_RET    	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_BIGINTEGER_RET    extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_FLOAT_RET    	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_DOUBLE_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_STRING_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_DATE_RET 		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_CALENDAR_RET 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SQLDATE_RET 		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SQLTIME_RET 		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SQLTIMESTAMP_RET 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,

		UNDECIDED_SUM_LONG_RET  extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_COUNT_RET		extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_LENGTH_RET	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,

		UNDECIDED_BYTE_RET 		extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_SHORT_RET 	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_INTEGER_RET 	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_LONG_RET    	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_BIGINTEGER_RET    extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_FLOAT_RET    	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_DOUBLE_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_STRING_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_DATE_RET 		extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_CALENDAR_RET 	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_SQLDATE_RET 		extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_SQLTIME_RET 		extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_SQLTIMESTAMP_RET 	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,

		NO_PARAM_ARITHMETIC_SAME_TYPE_RET,
		NO_PARAM_ARITHMETIC_DOUBLE_RET,

		NO_PARAM_AGGREGATE_SAME_TYPE_RET,
		NO_PARAM_AGGREGATE_SUM_RET,
		NO_PARAM_AGGREGATE_COUNT_RET,
		
		NO_PARAM_STRING_SAME_TYPE_RET,
		NO_PARAM_STRING_LENGTH_RET

>

		extends Collector_NestedFunctions_Base<MODEL, RESULT,

			NAMED_RET,
			ALIAS_RET,
			UNDECIDED_RET,
		
		/*

				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,

				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,

				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,

				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,

*/
		
		NAMED_SUM_LONG_RET, NAMED_COUNT_RET, NAMED_LENGTH_RET,
		
		NAMED_BYTE_RET, NAMED_SHORT_RET, NAMED_INTEGER_RET, NAMED_LONG_RET, NAMED_BIGINTEGER_RET, NAMED_FLOAT_RET, NAMED_DOUBLE_RET, NAMED_BIGDECIMAL_RET, NAMED_STRING_RET, NAMED_DATE_RET, NAMED_CALENDAR_RET, NAMED_SQLDATE_RET, NAMED_SQLTIME_RET, NAMED_SQLTIMESTAMP_RET,

		ALIAS_SUM_LONG_RET, ALIAS_COUNT_RET, ALIAS_LENGTH_RET,
		
		ALIAS_BYTE_RET, ALIAS_SHORT_RET, ALIAS_INTEGER_RET, ALIAS_LONG_RET, ALIAS_BIGINTEGER_RET, ALIAS_FLOAT_RET, ALIAS_DOUBLE_RET, ALIAS_BIGDECIMAL_RET, ALIAS_STRING_RET, ALIAS_DATE_RET, ALIAS_CALENDAR_RET, ALIAS_SQLDATE_RET, ALIAS_SQLTIME_RET, ALIAS_SQLTIMESTAMP_RET,

				
		UNDECIDED_SUM_LONG_RET, UNDECIDED_COUNT_RET, UNDECIDED_LENGTH_RET,
		
		UNDECIDED_BYTE_RET, UNDECIDED_SHORT_RET, UNDECIDED_INTEGER_RET, UNDECIDED_LONG_RET, UNDECIDED_BIGINTEGER_RET, UNDECIDED_FLOAT_RET, UNDECIDED_DOUBLE_RET, UNDECIDED_BIGDECIMAL_RET, UNDECIDED_STRING_RET, UNDECIDED_DATE_RET, UNDECIDED_CALENDAR_RET, UNDECIDED_SQLDATE_RET, UNDECIDED_SQLTIME_RET, UNDECIDED_SQLTIMESTAMP_RET>

		implements

		// TODO undecided - ISharedFunctions_Transform_Initial_Named<MODEL, RESULT, RET, LENGTH_RET, BYTE_RET, SHORT_RET, INTEGER_RET, LONG_RET, BIGINTEGER_RET, FLOAT_RET, DOUBLE_RET, BIGDECIMAL_RET, STRING_RET>,

		// ISharedFunctions_Arithmetic_NoParam_Base<MODEL, RESULT, RET,
		// NO_PARAM_SHORT_RET, NO_PARAM_INTEGER_RET, NO_PARAM_LONG_RET,
		// NO_PARAM_DOUBLE_RET, NO_PARAM_BIGDECIMAL_RET>,
		// ISharedFunctions_String_NoParam_Base<MODEL, RESULT, RET,
		// NO_PARAM_STRING_RET>

		ISharedFunctions_Arithmetic_NoParam_Base<MODEL, RESULT, NO_PARAM_ARITHMETIC_SAME_TYPE_RET, NO_PARAM_ARITHMETIC_DOUBLE_RET>,
		ISharedFunctions_String_NoParam_Base<MODEL, RESULT, NO_PARAM_STRING_LENGTH_RET, NO_PARAM_STRING_SAME_TYPE_RET>,
		ISharedFunctions_Aggregate_NoParam_Base<MODEL, RESULT, NO_PARAM_AGGREGATE_SAME_TYPE_RET, NO_PARAM_AGGREGATE_SUM_RET, NO_PARAM_AGGREGATE_COUNT_RET>

{

	private final ISharedCollector_Functions_Callback<MODEL, RESULT, UNDECIDED_RET> func;

	Collector_NestedFunctions_Undecided(ISharedCollector_Functions_Callback<MODEL, RESULT, UNDECIDED_RET> func /* , Collector_NestedFunctions_Base<MODEL, RESULT> last */) {

		if (func == null) {
		throw new IllegalArgumentException("func == null");
		}
		
		this.func = func;
	}
	
	Collector_NestedFunctions_Undecided(Collector_NestedFunctions_Undecided<
		MODEL, RESULT,
		NAMED_RET, ALIAS_RET, UNDECIDED_RET,
		?, ?, ?,
		?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 
		?, ?, ?,
		?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 
		?, ?, ?,
		?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 
		?, ?, ?, ?, ?, ?, ?
		> toCopy) {

		super(toCopy);

		this.func = toCopy.func;
	}

	
	@Override
	final ISharedFunction_Next<MODEL, RESULT, NAMED_RET> continueAfterNamedComparableFunctions(Expression expression) {
		throw new UnsupportedOperationException("N/A");
	}

	@Override
	final ISharedFunction_Next<MODEL, RESULT, NAMED_RET> continueAfterNamedStringFunctions(Expression expression) {
		throw new UnsupportedOperationException("N/A");
	}


	@Override
	final ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> continueAfterAliasComparableFunctions(Expression expression) {
		throw new UnsupportedOperationException("N/A");
	}

	@Override
	final ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> continueAfterAliasStringFunctions(Expression expression) {
		throw new UnsupportedOperationException("N/A");
	}

	@Override
	final ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET> continueAfterUndecidedComparableFunctions(Expression expression) {
		return func.onComparable(expression);
	}

	@Override
	final ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET> continueAfterUndecidedStringFunctions(Expression expression) {
		return func.onString(expression);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public final NO_PARAM_STRING_SAME_TYPE_RET lower() {
		addNoParamFunctionToList(Function_String_Lower.INSTANCE);

		return (NO_PARAM_STRING_SAME_TYPE_RET) getUndecidedNoParamNext();
	}

	@Override
	@SuppressWarnings("unchecked")
	public final NO_PARAM_STRING_SAME_TYPE_RET upper() {
		addNoParamFunctionToList(Function_String_Upper.INSTANCE);

		return (NO_PARAM_STRING_SAME_TYPE_RET) getUndecidedNoParamNext();
	}

	@Override
	@SuppressWarnings("unchecked")
	public final NO_PARAM_STRING_SAME_TYPE_RET trim() {
		addNoParamFunctionToList(Function_String_Trim.INSTANCE);

		return (NO_PARAM_STRING_SAME_TYPE_RET) getUndecidedNoParamNext();
	}

	@Override
	@SuppressWarnings("unchecked")
	public final NO_PARAM_STRING_LENGTH_RET length() {
		addNoParamFunctionToList(Function_String_Length.INSTANCE);

		return (NO_PARAM_STRING_LENGTH_RET) getUndecidedNoParamNext();
	}

	@Override
	@SuppressWarnings("unchecked")
	public final NO_PARAM_ARITHMETIC_SAME_TYPE_RET abs() {
		addNoParamFunctionToList(Function_Arithmetic_Abs.INSTANCE);

		return (NO_PARAM_ARITHMETIC_SAME_TYPE_RET) getUndecidedNoParamNext();
	}

	@Override
	@SuppressWarnings("unchecked")
	public final NO_PARAM_ARITHMETIC_DOUBLE_RET sqrt() {
		
		addNoParamFunctionToList(Function_Arithmetic_Sqrt.INSTANCE);
	
		return (NO_PARAM_ARITHMETIC_DOUBLE_RET)getUndecidedNoParamNext();
	}

	@Override
	@SuppressWarnings("unchecked")
	public final NO_PARAM_AGGREGATE_SUM_RET sum() {
		addNoParamFunctionToList(Function_Aggregate.SUM);
		
		return (NO_PARAM_AGGREGATE_SUM_RET)getUndecidedNoParamNext();
	}

	@Override
	@SuppressWarnings("unchecked")
	public final NO_PARAM_AGGREGATE_SAME_TYPE_RET min() {
		addNoParamFunctionToList(Function_Aggregate.MIN);
		
		return (NO_PARAM_AGGREGATE_SAME_TYPE_RET)getUndecidedNoParamNext();
	}

	@Override
	@SuppressWarnings("unchecked")
	public final NO_PARAM_AGGREGATE_SAME_TYPE_RET max() {
		addNoParamFunctionToList(Function_Aggregate.MAX);
		
		return (NO_PARAM_AGGREGATE_SAME_TYPE_RET)getUndecidedNoParamNext();
	}

	@Override
	@SuppressWarnings("unchecked")
	public final NO_PARAM_AGGREGATE_SAME_TYPE_RET avg() {
		addNoParamFunctionToList(Function_Aggregate.AVG);
		
		return (NO_PARAM_AGGREGATE_SAME_TYPE_RET)getUndecidedNoParamNext();
	}

	@Override
	@SuppressWarnings("unchecked")
	public final NO_PARAM_AGGREGATE_COUNT_RET count() {
		addNoParamFunctionToList(Function_Aggregate.COUNT);
		
		return (NO_PARAM_AGGREGATE_COUNT_RET)getUndecidedNoParamNext();
	}
}
