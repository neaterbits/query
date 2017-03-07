package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

abstract class Collector_SharedFunctions_Alias<
		MODEL,
		RESULT,

		// commented out since reused for mapping functions 
		RET extends ISharedFunction_After<MODEL, RESULT>,

		SHORT_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		INTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_RET    extends ISharedFunction_Next<MODEL, RESULT, RET>,
		DOUBLE_RET  extends ISharedFunction_Next<MODEL, RESULT, RET>,
		BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_RET  extends ISharedFunction_Next<MODEL, RESULT, RET>
		
		>

		extends Collector_SharedFunctions_Base<MODEL, RESULT>

		implements ISharedFunctions_Transform_Initial_Alias<MODEL, RESULT, RET, SHORT_RET, INTEGER_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET, STRING_RET>


{

	private final ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, RET> func;

	Collector_SharedFunctions_Alias(ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, RET> func, Collector_SharedFunctions_Base<MODEL, RESULT> last) {
		super(last);

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
	
	
		COMPARABLE addAndReturnType(
			Function_Arithmetic function, Supplier<? extends Comparable<?>> getter) {

		return addAndReturnType(function, getter);
	}

	private ISharedFunction_Next<MODEL, RESULT, RET> addAndReturnString(Function_String function, ISupplierString getter) {

		add(function);

		return func.onString(collect(), getter);
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <T> STRING_RET lower(ISupplierString getter) {
		return (STRING_RET) addAndReturnString(Function_String_Lower.INSTANCE, getter);
	}


	@Override
	@SuppressWarnings("unchecked")
	public final <T> STRING_RET upper(ISupplierString getter) {
		return (STRING_RET) addAndReturnString(Function_String_Upper.INSTANCE, getter);
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <T> STRING_RET trim(ISupplierString getter) {
		return (STRING_RET) addAndReturnString(Function_String_Trim.INSTANCE, getter);
	}

	@Override
	public final <T> SHORT_RET abs(ISupplierShort getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public final <T> INTEGER_RET abs(ISupplierInteger getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public final <T> LONG_RET abs(ISupplierLong getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public final <T> DOUBLE_RET abs(ISupplierDouble getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public final <T> BIGDECIMAL_RET abs(ISupplierBigDecimal getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public final <T> DOUBLE_RET sqrt(ISupplierShort getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public final <T> DOUBLE_RET sqrt(ISupplierInteger getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public final <T> DOUBLE_RET sqrt(ISupplierLong getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public final <T> DOUBLE_RET sqrt(ISupplierDouble getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public final <T> DOUBLE_RET sqrt(ISupplierBigDecimal getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}
}
