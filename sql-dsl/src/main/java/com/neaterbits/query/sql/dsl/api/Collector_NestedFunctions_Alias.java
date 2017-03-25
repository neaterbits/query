package com.neaterbits.query.sql.dsl.api;


abstract class Collector_NestedFunctions_Alias<
		MODEL,
		RESULT,

		// commented out since reused for mapping functions 
		RET extends ISharedFunction_After<MODEL, RESULT>,

		SUM_LONG_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		COUNT_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		
		SHORT_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		INTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_RET    extends ISharedFunction_Next<MODEL, RESULT, RET>,
		DOUBLE_RET  extends ISharedFunction_Next<MODEL, RESULT, RET>,
		BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		DATE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_RET  extends ISharedFunction_Next<MODEL, RESULT, RET>
		
		>

		extends Collector_NestedFunctions_Base<
				MODEL, 
				RESULT,
				
				ISharedFunction_After<MODEL, RESULT>,
				RET,
				
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				
				SUM_LONG_RET,
				COUNT_RET,
				
				SHORT_RET,
				INTEGER_RET,
				LONG_RET,
				DOUBLE_RET,
				BIGDECIMAL_RET,
				DATE_RET,
				STRING_RET
				>

		implements ISharedFunctions_Transform_Initial_Alias<MODEL, RESULT, RET, SHORT_RET, INTEGER_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET, STRING_RET>,
		
			ISharedFunctions_Initial_And_NoParam_Alias<
					MODEL,
					RESULT,
					
					RET,
					
					SHORT_RET,
					INTEGER_RET,
					LONG_RET,
					DOUBLE_RET,
					BIGDECIMAL_RET,
					STRING_RET
					>


{

	private final ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, RET> func;

	Collector_NestedFunctions_Alias(ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, RET> func /* , Collector_NestedFunctions_Base<MODEL, RESULT> last */) {
		//super(last);

		if (func == null) {
			throw new IllegalArgumentException("func == null");
		}

		this.func = func;
	}

	Collector_NestedFunctions_Alias(Collector_NestedFunctions_Alias<MODEL, RESULT, RET, ?, ?, ?, ?, ?, ?, ?, ?, ?> toCopy) {
		super(toCopy);

		this.func = toCopy.func;
	}
	

	/*
	@SuppressWarnings("unchecked")
	final <VALUE extends Comparable<?>, TYPE extends ISharedFunction_Next<MODEL, RESULT, RET>>  
	
		 TYPE addAndReturnType(FunctionBase function, Supplier<? extends Comparable<?>> getter) {

		add(function);

		return (TYPE)func.onComparable(collect(), getter);
	}
	
	private <VALUE extends Comparable<?>,
			COMPARABLE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, VALUE, ?>>
	
	
		COMPARABLE addAndReturnType(
			Function_Arithmetic function, Supplier<? extends Comparable<?>> getter) {

		return addAndReturnType(function, getter);
	}

	
	@Override
	ISharedFunction_Next<MODEL, RESULT, RET> addAndReturnString(Function_String function, ISupplierString getter) {

		add(function);

		return func.onString(collect(), getter);
	}
	*/

	
	@Override
	final ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>> continueAfterNamedComparableFunctions(
			Expression expression) {
		throw new UnsupportedOperationException("N/A");
	}



	@Override
	final ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>> continueAfterNamedStringFunctions(
			Expression expression) {
		throw new UnsupportedOperationException("N/A");
	}


	@Override
	@SuppressWarnings("unchecked")
	public final ISharedFunctions_String_Alias<MODEL, RESULT, RET, STRING_RET> lower() {
		addNoParam(Function_String_Lower.INSTANCE);

		return (ISharedFunctions_String_Alias<MODEL, RESULT, RET, STRING_RET>)getAliasNoParamNext();
	}

	@Override
	@SuppressWarnings("unchecked")
		public final ISharedFunctions_String_Alias<MODEL, RESULT, RET, STRING_RET> upper() {
		addNoParam(Function_String_Upper.INSTANCE);

		return (ISharedFunctions_String_Alias<MODEL, RESULT, RET, STRING_RET>)getAliasNoParamNext();
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public final ISharedFunctions_Arithmetic_Alias<MODEL, RESULT, RET, SHORT_RET, INTEGER_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET> abs() {
		addNoParam(Function_Arithmetic_Abs.INSTANCE);

		return (ISharedFunctions_Arithmetic_Alias)getAliasNoParamNext();
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
	public final ISharedFunctions_Arithmetic_Alias<MODEL, RESULT, RET, DOUBLE_RET, DOUBLE_RET, DOUBLE_RET, DOUBLE_RET, DOUBLE_RET> sqrt() {
		addNoParam(Function_Arithmetic_Sqrt.INSTANCE);

		return (ISharedFunctions_Arithmetic_Alias)getAliasNoParamNext();
	}
	

	@Override
	@SuppressWarnings("unchecked")
	public final ISharedFunctions_String_Alias<MODEL, RESULT, RET, STRING_RET> trim() {
		addNoParam(Function_String_Trim.INSTANCE);

		return (ISharedFunctions_String_Alias<MODEL, RESULT, RET, STRING_RET>)getAliasNoParamNext();
	}
}
