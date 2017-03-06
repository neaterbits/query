package com.neaterbits.query.sql.dsl.api;

final class Collector_ConditionFunctions_Named<
		MODEL,
		RESULT,
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		INTEGER_NEXT extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_NEXT	 extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_NEXT  extends ISharedFunction_Next<MODEL, RESULT, RET>
	> 

	extends Collector_SharedFunctions_Named<MODEL, RESULT, RET, INTEGER_NEXT, LONG_NEXT, STRING_NEXT>

	implements ISharedFunctions_Initial_And_NoParam_Named<MODEL, RESULT, RET, INTEGER_NEXT, LONG_NEXT, STRING_NEXT> {

	Collector_ConditionFunctions_Named(ISharedCollector_Functions_Callback_Named<MODEL, RESULT, RET> func) {
		super(func, null);
	}
		
	@Override
	public ISharedFunctions_String_Named<MODEL, RESULT, RET, STRING_NEXT> lower() {
		add(Function_String_Lower.INSTANCE);
		
		return this;
	}

	@Override
	public ISharedFunctions_String_Named<MODEL, RESULT, RET, STRING_NEXT> upper() {
		add(Function_String_Upper.INSTANCE);

		return this;
	}

	@Override
	public ISharedFunctions_String_Named<MODEL, RESULT, RET, STRING_NEXT> trim() {
		add(Function_String_Trim.INSTANCE);

		return this;
	}

	@Override
	public ISharedFunctions_Arithmetic_Named<MODEL, RESULT, RET, INTEGER_NEXT, LONG_NEXT> abs() {
		add(Function_Arithmetic_Abs.INSTANCE);

		return this;
	}
	
	@Override
	public ISharedFunctions_Arithmetic_Named<MODEL, RESULT, RET, INTEGER_NEXT, LONG_NEXT> sqrt() {
		add(Function_Arithmetic_Sqrt.INSTANCE);

		return this;
	}
}
