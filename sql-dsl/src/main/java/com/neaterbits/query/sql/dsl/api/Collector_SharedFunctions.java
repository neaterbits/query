package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Collector for all functions
 * @author nhl
 *
 * @param <MODEL>
 * @param <RESULT>
 * @param <RET>
 * @param <COMPARABLE_CLAUSE>
 * @param <STRING_CLAUSE>
 */

final class Collector_SharedFunctions<
				MODEL,
				RESULT,
				RET extends ISharedLogical_Base<MODEL, RESULT>,
	
				COMPARABLE_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, ?, RET>,
				STRING_CLAUSE extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, RET>>

	implements ISharedFunctions_Named_Initial<MODEL, RESULT, RET, COMPARABLE_CLAUSE, STRING_CLAUSE> {
		
	private final Collector_Functions_Callback<MODEL, RESULT, RET> func;
	private final List<FunctionBase> functions;
	
	
	
	Collector_SharedFunctions(Collector_Functions_Callback<MODEL, RESULT, RET> func) {
		
		if (func == null) {
			throw new IllegalArgumentException("func == null");
		}
		
		this.func = func;
		this.functions = new ArrayList<>();
	}
	
	private void add(FunctionBase function) {
		if (function == null) {
			throw new IllegalArgumentException("function == null");
		}
		
		functions.add(function);
	}
	
	private ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, RET> addAndReturnComparable(Function_Arithmetic function, Function<?, ? extends Comparable<?>> getter) {
		
		add(function);

		return func.onComparable(new CollectedFunctions(functions), getter);
	}
	
	private ISharedCondition_Comparable_String_Base<MODEL, RESULT, RET> addAndReturnString(Function_String function, StringFunction<?> getter) {
		
		add(function);

		return func.onString(new CollectedFunctions(functions), getter);
	}
	

	@Override
	@SuppressWarnings("unchecked")
	public <T> STRING_CLAUSE lower(StringFunction<T> getter) {
		return (STRING_CLAUSE)addAndReturnString(Function_String_Lower.INSTANCE, getter);
	}

	@Override
	public ISharedFunctions_Named_String<MODEL, RESULT, RET, STRING_CLAUSE> lower() {
		add(Function_String_Lower.INSTANCE);
		
		return this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> STRING_CLAUSE upper(StringFunction<T> getter) {
		return (STRING_CLAUSE)addAndReturnString(Function_String_Upper.INSTANCE, getter);
	}

	@Override
	public ISharedFunctions_Named_String<MODEL, RESULT, RET, STRING_CLAUSE> upper() {
		add(Function_String_Upper.INSTANCE);

		return this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> STRING_CLAUSE trim(StringFunction<T> getter) {
		return (STRING_CLAUSE)addAndReturnString(Function_String_Trim.INSTANCE, getter);
	}

	@Override
	public ISharedFunctions_Named_String<MODEL, RESULT, RET, STRING_CLAUSE> trim() {
		add(Function_String_Trim.INSTANCE);

		return this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> COMPARABLE_CLAUSE abs(IFunctionInteger<T> getter) {
		return (COMPARABLE_CLAUSE)addAndReturnComparable(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> COMPARABLE_CLAUSE abs(IFunctionLong<T> getter) {
		return (COMPARABLE_CLAUSE)addAndReturnComparable(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public ISharedFunctions_Named_Arithmetic<MODEL, RESULT, RET, COMPARABLE_CLAUSE> abs() {
		add(Function_Arithmetic_Abs.INSTANCE);

		return this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> COMPARABLE_CLAUSE sqrt(IFunctionInteger<T> getter) {
		return (COMPARABLE_CLAUSE)addAndReturnComparable(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> COMPARABLE_CLAUSE sqrt(IFunctionLong<T> getter) {
		return (COMPARABLE_CLAUSE)addAndReturnComparable(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public ISharedFunctions_Named_Arithmetic<MODEL, RESULT, RET, COMPARABLE_CLAUSE> sqrt() {
		add(Function_Arithmetic_Sqrt.INSTANCE);

		return this;
	}
}
