package com.neaterbits.query.sql.dsl.api;

class Collector_ConditionFunctions_Alias<

		MODEL,
		RESULT,
		
		// commented out since reused for mapping functions 
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		INTEGER_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_CLAUSE    extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_CLAUSE  extends ISharedFunction_Next<MODEL, RESULT, RET>>

	extends Collector_SharedFunctions_Alias<
			MODEL, 
			RESULT, 
			RET,
			
			INTEGER_CLAUSE,
			LONG_CLAUSE,
			STRING_CLAUSE> 

	implements ISharedFunctions_Initial_And_NoParam_Alias<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE, STRING_CLAUSE> 

{
	
	Collector_ConditionFunctions_Alias(ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, RET> func) {
		super(func);
	}

	@Override
	public ISharedFunctions_String_Alias<MODEL, RESULT, RET, STRING_CLAUSE> lower() {
		add(Function_String_Lower.INSTANCE);

		return this;
	}

	@Override
	public ISharedFunctions_String_Alias<MODEL, RESULT, RET, STRING_CLAUSE> upper() {
		add(Function_String_Upper.INSTANCE);

		return this;
	}

	@Override
	public ISharedFunctions_Arithmetic_Alias<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE> abs() {
		add(Function_Arithmetic_Abs.INSTANCE);

		return this;
	}

	@Override
	public ISharedFunctions_Arithmetic_Alias<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE> sqrt() {
		add(Function_Arithmetic_Sqrt.INSTANCE);

		return this;
	}

	@Override
	public ISharedFunctions_String_Alias<MODEL, RESULT, RET, STRING_CLAUSE> trim() {
		add(Function_String_Trim.INSTANCE);

		return this;
	}
}
