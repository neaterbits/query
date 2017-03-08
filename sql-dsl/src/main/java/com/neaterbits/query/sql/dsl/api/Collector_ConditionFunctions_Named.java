package com.neaterbits.query.sql.dsl.api;

final class Collector_ConditionFunctions_Named<
		MODEL,
		RESULT,
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		SHORT_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		INTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_RET	 extends ISharedFunction_Next<MODEL, RESULT, RET>,
		DOUBLE_RET	  extends ISharedFunction_Next<MODEL, RESULT, RET>,
		BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_RET  extends ISharedFunction_Next<MODEL, RESULT, RET>
	> 

	extends Collector_SharedFunctions_Named<MODEL, RESULT, RET, SHORT_RET, INTEGER_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET, STRING_RET>

	implements ISharedFunctions_Initial_And_NoParam_Named<MODEL, RESULT, RET, SHORT_RET, INTEGER_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET, STRING_RET> {

	Collector_ConditionFunctions_Named(ISharedCollector_Functions_Callback_Named<MODEL, RESULT, RET> func) {
		super(func, null);
	}
	
	@Override
	<R extends Comparable<R>, CLAUSE> CLAUSE addSubNumeric(Function_Arithmetic function,
			ISharedSubOperandsFunction_Named<MODEL, RESULT, R> sub) {
		
		throw new UnsupportedOperationException("TODO - sub for conditions");
	}



	@Override
	<CLAUSE> CLAUSE addSubString(Function_String function, ISharedSubOperandsFunction_String_Named<MODEL, RESULT> sub) {

		throw new UnsupportedOperationException("TODO - sub for conditions");
	}

	@Override
	public ISharedFunctions_String_Named<MODEL, RESULT, RET, STRING_RET> lower() {
		add(Function_String_Lower.INSTANCE);
		
		return this;
	}

	@Override
	public ISharedFunctions_String_Named<MODEL, RESULT, RET, STRING_RET> upper() {
		add(Function_String_Upper.INSTANCE);

		return this;
	}

	@Override
	public ISharedFunctions_String_Named<MODEL, RESULT, RET, STRING_RET> trim() {
		add(Function_String_Trim.INSTANCE);

		return this;
	}

	@Override
	public ISharedFunctions_Arithmetic_Named<MODEL, RESULT, RET, SHORT_RET, INTEGER_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET> abs() {
		add(Function_Arithmetic_Abs.INSTANCE);

		return this;
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public ISharedFunctions_Arithmetic_Named<MODEL, RESULT, RET, DOUBLE_RET, DOUBLE_RET, DOUBLE_RET, DOUBLE_RET, DOUBLE_RET> sqrt() {
		add(Function_Arithmetic_Sqrt.INSTANCE);

		return (ISharedFunctions_Arithmetic_Named)this;
	}

	/*
	@Override
	public ISharedFunctions_Arithmetic_Named<MODEL, RESULT, RET, INTEGER_RET, LONG_RET> sqrt() {
		add(Function_Arithmetic_Sqrt.INSTANCE);

		return this;
	}
	*/
	
}
