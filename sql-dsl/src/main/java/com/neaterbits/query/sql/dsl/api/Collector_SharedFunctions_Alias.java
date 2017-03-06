package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

class Collector_SharedFunctions_Alias<
		MODEL,
		RESULT,

		// commented out since reused for mapping functions 
		RET extends ISharedFunction_After<MODEL, RESULT>,

		INTEGER_CLAUSE extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_CLAUSE    extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_CLAUSE  extends ISharedFunction_Next<MODEL, RESULT, RET>
		
		>

		extends Collector_SharedFunctions_Base<MODEL, RESULT, RET>

		implements 

			ISharedFunctions_Initial_And_NoParam_Alias<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE, STRING_CLAUSE> {

	private final ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, RET> func;

	Collector_SharedFunctions_Alias(ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, RET> func) {

		if (func == null) {
			throw new IllegalArgumentException("func == null");
		}

		this.func = func;
	}

	@SuppressWarnings("unchecked")
	final <VALUE extends Comparable<?>, TYPE extends ISharedFunction_Next<MODEL, RESULT, RET>>  
	
		 TYPE addAndReturnType(FunctionBase function, Supplier<? extends Comparable<?>> getter) {

		add(function);

		return (TYPE)func.onComparable(collect(), getter);
	}
	
	private <VALUE extends Comparable<?>,
			COMPARABLE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, VALUE, ?>>
	
	
		COMPARABLE addAndReturnComparable(
			Function_Arithmetic function, Supplier<? extends Comparable<?>> getter) {

		return addAndReturnType(function, getter);
	}

	private ISharedFunction_Next<MODEL, RESULT, RET> addAndReturnString(Function_String function, ISupplierString getter) {

		add(function);

		return func.onString(collect(), getter);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> STRING_CLAUSE lower(ISupplierString getter) {
		return (STRING_CLAUSE) addAndReturnString(Function_String_Lower.INSTANCE, getter);
	}

	@Override
	public ISharedFunctions_String_Alias<MODEL, RESULT, RET, STRING_CLAUSE> lower() {
		add(Function_String_Lower.INSTANCE);

		return this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> STRING_CLAUSE upper(ISupplierString getter) {
		return (STRING_CLAUSE) addAndReturnString(Function_String_Upper.INSTANCE, getter);
	}

	@Override
	public ISharedFunctions_String_Alias<MODEL, RESULT, RET, STRING_CLAUSE> upper() {
		add(Function_String_Upper.INSTANCE);

		return this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> STRING_CLAUSE trim(ISupplierString getter) {
		return (STRING_CLAUSE) addAndReturnString(Function_String_Trim.INSTANCE, getter);
	}

	@Override
	public ISharedFunctions_String_Alias<MODEL, RESULT, RET, STRING_CLAUSE> trim() {
		add(Function_String_Trim.INSTANCE);

		return this;
	}

	@Override
	public <T> INTEGER_CLAUSE abs(ISupplierInteger getter) {
		return addAndReturnComparable(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public <T> LONG_CLAUSE abs(ISupplierLong getter) {
		return addAndReturnComparable(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public ISharedFunctions_Arithmetic_Alias<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE> abs() {
		add(Function_Arithmetic_Abs.INSTANCE);

		return this;
	}

	@Override
	public <T> INTEGER_CLAUSE sqrt(ISupplierInteger getter) {
		return addAndReturnComparable(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public <T> LONG_CLAUSE sqrt(ISupplierLong getter) {
		return addAndReturnComparable(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public ISharedFunctions_Arithmetic_Alias<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE> sqrt() {
		add(Function_Arithmetic_Sqrt.INSTANCE);

		return this;
	}
}
