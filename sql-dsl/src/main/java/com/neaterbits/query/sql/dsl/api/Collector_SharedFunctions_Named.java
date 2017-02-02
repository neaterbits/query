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
 * @param <STRING_CLAUSE>
 */

final class Collector_SharedFunctions_Named<
				MODEL,
				RESULT,
				RET extends ISharedLogical_Base<MODEL, RESULT>,
	
				INTEGER_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, RET>,
				LONG_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Long, RET>,
				STRING_CLAUSE extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, RET>>

	extends Collector_SharedFunctions_Base<MODEL, RESULT, RET>

	implements ISharedFunctions_Named_Initial<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE, STRING_CLAUSE> {
		
	private final ISharedCollector_Functions_Callback_Named<MODEL, RESULT, RET> func;
	
	
	Collector_SharedFunctions_Named(ISharedCollector_Functions_Callback_Named<MODEL, RESULT, RET> func) {
		
		if (func == null) {
			throw new IllegalArgumentException("func == null");
		}
		
		this.func = func;
	}
	
	
	@SuppressWarnings("unchecked")
	private
		<VAL extends Comparable<?>, CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, VAL, RET>>
	
		 CLAUSE addAndReturnComparable(Function_Arithmetic function, Function<?, ? extends Comparable<?>> getter) {
		
		add(function);

		return (CLAUSE)func.onComparable(collect(), getter);
	}
	
	private ISharedCondition_Comparable_String_Base<MODEL, RESULT, RET> addAndReturnString(Function_String function, StringFunction<?> getter) {
		
		add(function);

		return func.onString(collect(), getter);
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
	public <T> INTEGER_CLAUSE abs(IFunctionInteger<T> getter) {
		return addAndReturnComparable(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public <T> LONG_CLAUSE abs(IFunctionLong<T> getter) {
		return addAndReturnComparable(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public ISharedFunctions_Named_Arithmetic<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE> abs() {
		add(Function_Arithmetic_Abs.INSTANCE);

		return this;
	}

	@Override
	public <T> INTEGER_CLAUSE sqrt(IFunctionInteger<T> getter) {
		return addAndReturnComparable(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public <T> LONG_CLAUSE sqrt(IFunctionLong<T> getter) {
		return addAndReturnComparable(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public ISharedFunctions_Named_Arithmetic<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE> sqrt() {
		add(Function_Arithmetic_Sqrt.INSTANCE);

		return this;
	}
}
