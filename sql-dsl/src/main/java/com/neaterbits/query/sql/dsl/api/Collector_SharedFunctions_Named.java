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
 * @param <STRING_RET>
 */

abstract class Collector_SharedFunctions_Named<
				MODEL,
				RESULT,
				RET extends ISharedFunction_After<MODEL, RESULT>,
	
				INTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
				LONG_RET	 extends ISharedFunction_Next<MODEL, RESULT, RET>,
				DOUBLE_RET	 extends ISharedFunction_Next<MODEL, RESULT, RET>,
				STRING_RET  extends ISharedFunction_Next<MODEL, RESULT, RET>
				
				>

	extends Collector_SharedFunctions_Base<MODEL, RESULT>

	implements ISharedFunctions_Transform_Initial_Named<MODEL, RESULT, RET, INTEGER_RET, LONG_RET, DOUBLE_RET, STRING_RET>

{
		
	private final ISharedCollector_Functions_Callback_Named<MODEL, RESULT, RET> func;
	
	
	Collector_SharedFunctions_Named(ISharedCollector_Functions_Callback_Named<MODEL, RESULT, RET> func, Collector_SharedFunctions_Base<MODEL, RESULT> last) {
		
		super(last);
		
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
	public <T> STRING_RET lower(StringFunction<T> getter) {
		return (STRING_RET)addAndReturnString(Function_String_Lower.INSTANCE, getter);
	}


	@Override
	@SuppressWarnings("unchecked")
	public <T> STRING_RET upper(StringFunction<T> getter) {
		return (STRING_RET)addAndReturnString(Function_String_Upper.INSTANCE, getter);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> STRING_RET trim(StringFunction<T> getter) {
		return (STRING_RET)addAndReturnString(Function_String_Trim.INSTANCE, getter);
	}

	@Override
	public <T> INTEGER_RET abs(IFunctionInteger<T> getter) {
		return addAndReturnComparable(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public <T> LONG_RET abs(IFunctionLong<T> getter) {
		return addAndReturnComparable(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public <T> DOUBLE_RET sqrt(IFunctionInteger<T> getter) {
		return addAndReturnComparable(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public <T> DOUBLE_RET sqrt(IFunctionLong<T> getter) {
		return addAndReturnComparable(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

}
