package com.neaterbits.query.sql.dsl.api;

abstract class Collector_NestedFunctions_Undecided<

		MODEL, RESULT,

		RET extends ISharedFunction_After<MODEL, RESULT>,

		SUM_LONG_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		COUNT_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LENGTH_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,

		BYTE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		SHORT_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		INTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		BIGINTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		FLOAT_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		DOUBLE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		DATE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		CALENDAR_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		SQLDATE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		SQLTIME_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		SQLTIMESTAMP_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,

		NO_PARAM_ARITHMETIC_SAME_TYPE_RET,
		NO_PARAM_ARITHMETIC_DOUBLE_RET,

		NO_PARAM_STRING_SAME_TYPE_RET,
		NO_PARAM_STRING_LENGTH_RET

>

		extends Collector_NestedFunctions_Base<MODEL, RESULT,

		ISharedFunction_After<MODEL, RESULT>, ISharedFunction_After<MODEL, RESULT>, RET,

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

				SUM_LONG_RET, COUNT_RET, LENGTH_RET,
				
				BYTE_RET, SHORT_RET, INTEGER_RET, LONG_RET, BIGINTEGER_RET, FLOAT_RET, DOUBLE_RET, BIGDECIMAL_RET, STRING_RET, DATE_RET, CALENDAR_RET, SQLDATE_RET, SQLTIME_RET, SQLTIMESTAMP_RET>

		implements

		// TODO undecided - ISharedFunctions_Transform_Initial_Named<MODEL, RESULT, RET, LENGTH_RET, BYTE_RET, SHORT_RET, INTEGER_RET, LONG_RET, BIGINTEGER_RET, FLOAT_RET, DOUBLE_RET, BIGDECIMAL_RET, STRING_RET>,

		// ISharedFunctions_Arithmetic_NoParam_Base<MODEL, RESULT, RET,
		// NO_PARAM_SHORT_RET, NO_PARAM_INTEGER_RET, NO_PARAM_LONG_RET,
		// NO_PARAM_DOUBLE_RET, NO_PARAM_BIGDECIMAL_RET>,
		// ISharedFunctions_String_NoParam_Base<MODEL, RESULT, RET,
		// NO_PARAM_STRING_RET>

		ISharedFunctions_Arithmetic_NoParam_Base<MODEL, RESULT, NO_PARAM_ARITHMETIC_SAME_TYPE_RET, NO_PARAM_ARITHMETIC_DOUBLE_RET>,
		ISharedFunctions_String_NoParam_Base<MODEL, RESULT, NO_PARAM_STRING_LENGTH_RET, NO_PARAM_STRING_SAME_TYPE_RET>

{

	private final ISharedCollector_Functions_Callback<MODEL, RESULT, RET> func;

	Collector_NestedFunctions_Undecided(ISharedCollector_Functions_Callback<MODEL, RESULT, RET> func /* , Collector_NestedFunctions_Base<MODEL, RESULT> last */) {

		if (func == null) {
		throw new IllegalArgumentException("func == null");
		}
		
		this.func = func;
	}
	
	Collector_NestedFunctions_Undecided(Collector_NestedFunctions_Undecided<
		MODEL, RESULT, RET,
		?, ?, ?,
		?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 
		?, ?, ?, ?
		> toCopy) {

		super(toCopy);

		this.func = toCopy.func;
	}

	@Override
	final ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>> continueAfterNamedComparableFunctions(Expression expression) {
		throw new UnsupportedOperationException("N/A");
	}

	@Override
	final ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>> continueAfterNamedStringFunctions(Expression expression) {
		throw new UnsupportedOperationException("N/A");
	}


	@Override
	final ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>> continueAfterAliasComparableFunctions(Expression expression) {
		throw new UnsupportedOperationException("N/A");
	}

	@Override
	final ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>> continueAfterAliasStringFunctions(Expression expression) {
		throw new UnsupportedOperationException("N/A");
	}

	@Override
	final ISharedFunction_Next<MODEL, RESULT, RET> continueAfterUndecidedComparableFunctions(Expression expression) {
		return func.onComparable(expression);
	}

	@Override
	final ISharedFunction_Next<MODEL, RESULT, RET> continueAfterUndecidedStringFunctions(Expression expression) {
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
}
