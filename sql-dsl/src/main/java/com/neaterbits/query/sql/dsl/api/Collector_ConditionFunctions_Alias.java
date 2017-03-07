package com.neaterbits.query.sql.dsl.api;

class Collector_ConditionFunctions_Alias<

		MODEL,
		RESULT,
		
		// commented out since reused for mapping functions 
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		SHORT_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		INTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_RET    extends ISharedFunction_Next<MODEL, RESULT, RET>,
		DOUBLE_RET  extends ISharedFunction_Next<MODEL, RESULT, RET>,
		BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_RET  extends ISharedFunction_Next<MODEL, RESULT, RET>>

	extends Collector_SharedFunctions_Alias<
			MODEL, 
			RESULT, 
			RET,

			SHORT_RET,
			INTEGER_RET,
			LONG_RET,
			DOUBLE_RET,
			BIGDECIMAL_RET,
			
			STRING_RET> 

	implements ISharedFunctions_Initial_And_NoParam_Alias<MODEL, RESULT, RET, SHORT_RET, INTEGER_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET, STRING_RET> 

{
	
	Collector_ConditionFunctions_Alias(ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, RET> func) {
		super(func, null);
	}

	@Override
	public ISharedFunctions_String_Alias<MODEL, RESULT, RET, STRING_RET> lower() {
		add(Function_String_Lower.INSTANCE);

		return this;
	}

	@Override
	public ISharedFunctions_String_Alias<MODEL, RESULT, RET, STRING_RET> upper() {
		add(Function_String_Upper.INSTANCE);

		return this;
	}

	@Override
	public ISharedFunctions_Arithmetic_Alias<MODEL, RESULT, RET, SHORT_RET, INTEGER_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET> abs() {
		add(Function_Arithmetic_Abs.INSTANCE);

		return this;
	}

	/*
	@Override
	public ISharedFunctions_Arithmetic_Alias<MODEL, RESULT, RET, INTEGER_RET, LONG_RET, DOUBLE_RET> sqrt() {
		add(Function_Arithmetic_Sqrt.INSTANCE);

		return this;
	}
	*/

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public ISharedFunctions_Arithmetic_Alias<MODEL, RESULT, RET, DOUBLE_RET, DOUBLE_RET, DOUBLE_RET, DOUBLE_RET, DOUBLE_RET> sqrt() {
		add(Function_Arithmetic_Sqrt.INSTANCE);

		return (ISharedFunctions_Arithmetic_Alias)this;
	}
	

	@Override
	public ISharedFunctions_String_Alias<MODEL, RESULT, RET, STRING_RET> trim() {
		add(Function_String_Trim.INSTANCE);

		return this;
	}
}
