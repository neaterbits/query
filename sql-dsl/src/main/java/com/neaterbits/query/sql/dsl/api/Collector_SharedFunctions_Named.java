package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

/**
 * Collector for all functions
 * @author nhl
 *
 * @param <MODEL>
 * @param <RESULT>
 * @param <RET>
 * @param <COMPARABLE_CLAUSE>
 * @param <STRING_NEXT>
 */

class Collector_SharedFunctions_Named<
				MODEL,
				RESULT,
				RET extends ISharedFunction_After<MODEL, RESULT>,
	
				INTEGER_NEXT extends ISharedFunction_Next<MODEL, RESULT, RET>,
				LONG_NEXT	 extends ISharedFunction_Next<MODEL, RESULT, RET>,
				STRING_NEXT  extends ISharedFunction_Next<MODEL, RESULT, RET>
				
				>

	extends Collector_SharedFunctions_Base<MODEL, RESULT, RET>

	implements ISharedFunctions_Initial_And_NoParam_Named<MODEL, RESULT, RET, INTEGER_NEXT, LONG_NEXT, STRING_NEXT> {
		
	private final ISharedCollector_Functions_Callback_Named<MODEL, RESULT, RET> func;
	
	
	Collector_SharedFunctions_Named(ISharedCollector_Functions_Callback_Named<MODEL, RESULT, RET> func) {
		
		if (func == null) {
			throw new IllegalArgumentException("func == null");
		}
		
		this.func = func;
	}

	@SuppressWarnings("unchecked")
	final <VAL extends Comparable<?>, CLAUSE>
	 	CLAUSE addAndReturnType(FunctionBase function, Function<?, ? extends Comparable<?>> getter) {
	
		add(function);

		return (CLAUSE)func.onComparable(collect(), getter);
	}
	
	
	private
		<VAL extends Comparable<?>, CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, VAL, ?>>
	
		 CLAUSE addAndReturnComparable(Function_Arithmetic function, Function<?, ? extends Comparable<?>> getter) {

		return addAndReturnType(function, getter);
	}
	
	private ISharedFunction_Next<MODEL, RESULT, RET> addAndReturnString(Function_String function, StringFunction<?> getter) {
		
		add(function);

		return func.onString(collect(), getter);
	}
	

	@Override
	@SuppressWarnings("unchecked")
	public <T> STRING_NEXT lower(StringFunction<T> getter) {
		return (STRING_NEXT)addAndReturnString(Function_String_Lower.INSTANCE, getter);
	}

	@Override
	public ISharedFunctions_String_Named<MODEL, RESULT, RET, STRING_NEXT> lower() {
		add(Function_String_Lower.INSTANCE);
		
		return this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> STRING_NEXT upper(StringFunction<T> getter) {
		return (STRING_NEXT)addAndReturnString(Function_String_Upper.INSTANCE, getter);
	}

	@Override
	public ISharedFunctions_String_Named<MODEL, RESULT, RET, STRING_NEXT> upper() {
		add(Function_String_Upper.INSTANCE);

		return this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> STRING_NEXT trim(StringFunction<T> getter) {
		return (STRING_NEXT)addAndReturnString(Function_String_Trim.INSTANCE, getter);
	}

	@Override
	public ISharedFunctions_String_Named<MODEL, RESULT, RET, STRING_NEXT> trim() {
		add(Function_String_Trim.INSTANCE);

		return this;
	}

	@Override
	public <T> INTEGER_NEXT abs(IFunctionInteger<T> getter) {
		return addAndReturnComparable(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public <T> LONG_NEXT abs(IFunctionLong<T> getter) {
		return addAndReturnComparable(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public ISharedFunctions_Arithmetic_Named<MODEL, RESULT, RET, INTEGER_NEXT, LONG_NEXT> abs() {
		add(Function_Arithmetic_Abs.INSTANCE);

		return this;
	}

	@Override
	public <T> INTEGER_NEXT sqrt(IFunctionInteger<T> getter) {
		return addAndReturnComparable(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public <T> LONG_NEXT sqrt(IFunctionLong<T> getter) {
		return addAndReturnComparable(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public ISharedFunctions_Arithmetic_Named<MODEL, RESULT, RET, INTEGER_NEXT, LONG_NEXT> sqrt() {
		add(Function_Arithmetic_Sqrt.INSTANCE);

		return this;
	}
}
