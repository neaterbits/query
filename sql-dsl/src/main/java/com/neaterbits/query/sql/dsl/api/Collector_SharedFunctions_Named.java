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
	
				SHORT_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
				INTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
				LONG_RET	 extends ISharedFunction_Next<MODEL, RESULT, RET>,
				DOUBLE_RET	 extends ISharedFunction_Next<MODEL, RESULT, RET>,
				BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
				STRING_RET  extends ISharedFunction_Next<MODEL, RESULT, RET>
				
				>

	extends Collector_SharedFunctions_Base<MODEL, RESULT>

	implements ISharedFunctions_Transform_Initial_Named<MODEL, RESULT, RET, SHORT_RET, INTEGER_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET, STRING_RET>

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

	abstract <R extends Comparable<R>, CLAUSE> CLAUSE addSubNumeric(Function_Arithmetic function, ISharedSubOperandsFunction_Named<MODEL, RESULT, R> sub);
	
	abstract <CLAUSE> CLAUSE addSubString(Function_String function, ISharedSubOperandsFunction_String_Named<MODEL, RESULT> sub);

	@Override
	@SuppressWarnings("unchecked")
	public final <T> STRING_RET lower(StringFunction<T> getter) {
		return (STRING_RET)addAndReturnString(Function_String_Lower.INSTANCE, getter);
	}


	@Override
	@SuppressWarnings("unchecked")
	public final <T> STRING_RET upper(StringFunction<T> getter) {
		return (STRING_RET)addAndReturnString(Function_String_Upper.INSTANCE, getter);
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <T> STRING_RET trim(StringFunction<T> getter) {
		return (STRING_RET)addAndReturnString(Function_String_Trim.INSTANCE, getter);
	}
	
	
	@Override
	public final <T> SHORT_RET abs(IFunctionShort<T> getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public final <T> INTEGER_RET abs(IFunctionInteger<T> getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public final <T> LONG_RET abs(IFunctionLong<T> getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}
	
	@Override
	public final <T> SHORT_RET absOfShort(ISharedSubOperandsFunction_Short_Named<MODEL, RESULT> sub) {
		return addSubNumeric(Function_Arithmetic_Abs.INSTANCE, sub);
	}

	@Override
	public final <T> INTEGER_RET absOfInteger(ISharedSubOperandsFunction_Integer_Named<MODEL, RESULT> sub) {
		return addSubNumeric(Function_Arithmetic_Abs.INSTANCE, sub);
	}

	@Override
	public final <T> LONG_RET absOfLong(ISharedSubOperandsFunction_Long_Named<MODEL, RESULT> sub) {
		return addSubNumeric(Function_Arithmetic_Abs.INSTANCE, sub);
	}

	@Override
	public final <T> BIGDECIMAL_RET absOfDecimal(ISharedSubOperandsFunction_BigDecimal_Named<MODEL, RESULT> sub) {
		return addSubNumeric(Function_Arithmetic_Abs.INSTANCE, sub);
	}

	@Override
	public final <T> BIGDECIMAL_RET abs(IFunctionBigDecimal<T> getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public final <T> DOUBLE_RET sqrt(IFunctionShort<T> getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public final <T> DOUBLE_RET sqrt(IFunctionInteger<T> getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public final <T> DOUBLE_RET sqrt(IFunctionLong<T> getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public final <T> DOUBLE_RET sqrt(IFunctionBigDecimal<T> getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public final <T> DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Double_Named<MODEL, RESULT> sub) {
		return addSubNumeric(Function_Arithmetic_Sqrt.INSTANCE, sub);
	}
}
