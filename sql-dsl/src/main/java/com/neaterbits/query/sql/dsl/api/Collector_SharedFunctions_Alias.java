package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

final class Collector_SharedFunctions_Alias<MODEL, RESULT, RET extends ISharedLogical_Base<MODEL, RESULT>,

		COMPARABLE_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, ?, RET>, STRING_CLAUSE extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, RET>>

		extends Collector_SharedFunctions_Base<MODEL, RESULT, RET>

		implements ISharedFunctions_Alias_Initial<MODEL, RESULT, RET, COMPARABLE_CLAUSE, STRING_CLAUSE> {

	private final ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, RET> func;

	Collector_SharedFunctions_Alias(ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, RET> func) {
	
		if (func == null) {
			throw new IllegalArgumentException("func == null");
		}
		
		this.func = func;
	}

	private ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, RET> addAndReturnComparable(
			Function_Arithmetic function, Supplier<? extends Comparable<?>> getter) {

		add(function);

		return func.onComparable(collect(), getter);
	}

	private ISharedCondition_Comparable_String_Base<MODEL, RESULT, RET> addAndReturnString(Function_String function, ISupplierString getter) {

		add(function);

		return func.onString(collect(), getter);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> STRING_CLAUSE lower(ISupplierString getter) {
		return (STRING_CLAUSE) addAndReturnString(Function_String_Lower.INSTANCE, getter);
	}

	@Override
	public ISharedFunctions_Alias_String<MODEL, RESULT, RET, STRING_CLAUSE> lower() {
		add(Function_String_Lower.INSTANCE);

		return this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> STRING_CLAUSE upper(ISupplierString getter) {
		return (STRING_CLAUSE) addAndReturnString(Function_String_Upper.INSTANCE, getter);
	}

	@Override
	public ISharedFunctions_Alias_String<MODEL, RESULT, RET, STRING_CLAUSE> upper() {
		add(Function_String_Upper.INSTANCE);

		return this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> STRING_CLAUSE trim(ISupplierString getter) {
		return (STRING_CLAUSE) addAndReturnString(Function_String_Trim.INSTANCE, getter);
	}

	@Override
	public ISharedFunctions_Alias_String<MODEL, RESULT, RET, STRING_CLAUSE> trim() {
		add(Function_String_Trim.INSTANCE);

		return this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> COMPARABLE_CLAUSE abs(ISupplierInteger getter) {
		return (COMPARABLE_CLAUSE) addAndReturnComparable(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> COMPARABLE_CLAUSE abs(ISupplierLong getter) {
		return (COMPARABLE_CLAUSE) addAndReturnComparable(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public ISharedFunctions_Alias_Arithmetic<MODEL, RESULT, RET, COMPARABLE_CLAUSE> abs() {
		add(Function_Arithmetic_Abs.INSTANCE);

		return this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> COMPARABLE_CLAUSE sqrt(ISupplierInteger getter) {
		return (COMPARABLE_CLAUSE) addAndReturnComparable(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> COMPARABLE_CLAUSE sqrt(ISupplierLong getter) {
		return (COMPARABLE_CLAUSE) addAndReturnComparable(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public ISharedFunctions_Alias_Arithmetic<MODEL, RESULT, RET, COMPARABLE_CLAUSE> sqrt() {
		add(Function_Arithmetic_Sqrt.INSTANCE);

		return this;
	}
}
