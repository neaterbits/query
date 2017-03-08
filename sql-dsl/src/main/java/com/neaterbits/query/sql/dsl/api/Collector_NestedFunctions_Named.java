package com.neaterbits.query.sql.dsl.api;


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

@Deprecated
abstract class Collector_NestedFunctions_Named<

				MODEL,
				RESULT,

				RET extends ISharedFunction_After<MODEL, RESULT>,

				SUM_LONG_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
				COUNT_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
				
				SHORT_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
				INTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
				LONG_RET	 extends ISharedFunction_Next<MODEL, RESULT, RET>,
				DOUBLE_RET	 extends ISharedFunction_Next<MODEL, RESULT, RET>,
				BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
				STRING_RET  extends ISharedFunction_Next<MODEL, RESULT, RET>,
				
				NO_PARAM_ARITHMETIC_SAME_TYPE_RET,
				NO_PARAM_ARITHMETIC_DOUBLE_RET,
				
				NO_PARAM_STRING_RET
				
				>

	extends Collector_NestedFunctions_Base<
				MODEL,
				RESULT,
				
				RET,
				ISharedFunction_After<MODEL, RESULT>,

				SUM_LONG_RET,
				COUNT_RET,
				
				SHORT_RET,
				INTEGER_RET,
				LONG_RET,
				DOUBLE_RET,
				BIGDECIMAL_RET,
				STRING_RET,
				
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
				ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>
				>

	implements
	
		ISharedFunctions_Transform_Initial_Named<MODEL, RESULT, RET, SHORT_RET, INTEGER_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET, STRING_RET>,
	
			//ISharedFunctions_Arithmetic_NoParam_Base<MODEL, RESULT, RET, NO_PARAM_SHORT_RET, NO_PARAM_INTEGER_RET, NO_PARAM_LONG_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_BIGDECIMAL_RET>,
			// ISharedFunctions_String_NoParam_Base<MODEL, RESULT, RET, NO_PARAM_STRING_RET>
	
	
			ISharedFunctions_Arithmetic_NoParam_Base<MODEL, RESULT, NO_PARAM_ARITHMETIC_SAME_TYPE_RET, NO_PARAM_ARITHMETIC_DOUBLE_RET>,
			ISharedFunctions_String_NoParam_Base<MODEL, RESULT, NO_PARAM_STRING_RET>

{
		
	private final ISharedCollector_Functions_Callback_Named<MODEL, RESULT, RET> func;
	
	
	Collector_NestedFunctions_Named(ISharedCollector_Functions_Callback_Named<MODEL, RESULT, RET> func /* , Collector_NestedFunctions_Base<MODEL, RESULT> last */) {
		
		if (func == null) {
			throw new IllegalArgumentException("func == null");
		}
		
		this.func = func;
	}
	
	@Override
	final ISharedFunction_Next<MODEL, RESULT, RET> continueAfterNamedComparableFunctions(Expression expression) {
		return func.onComparable(expression);
	}

	@Override
	final ISharedFunction_Next<MODEL, RESULT, RET> continueAfterNamedStringFunctions(Expression expression) {
		return func.onString(expression);
	}

	/*
	@SuppressWarnings("unchecked")
	final <VAL extends Comparable<?>, CLAUSE>
	 	CLAUSE addAndReturnType(FunctionBase function, Function<?, ? extends Comparable<?>> getter) {
	
		addNoParam(function);

		return (CLAUSE)func.onComparable(collect(), getter);
	}
	
	
	private
		<VAL extends Comparable<?>, CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, VAL, ?>>
	
		 CLAUSE addAndReturnComparable(Function_Arithmetic function, Function<?, ? extends Comparable<?>> getter) {

		return addAndReturnType(function, getter);
	}
	*/
	
	

	/*
	@Override
	final <T> ISharedFunction_Next<MODEL, RESULT, RET> addAndReturnString(Function_String function, StringFunction<T> getter) {
		
		addNoParam(function);

		return func.onString(collect(), getter);
	}
	*/



	@Override
	final ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>
			continueAfterAliasFunctions(Expression expression) {
		throw new UnsupportedOperationException("N/A");
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public final NO_PARAM_STRING_RET lower() {
		addNoParam(Function_String_Lower.INSTANCE);
		
		return (NO_PARAM_STRING_RET)this;
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public final NO_PARAM_STRING_RET upper() {
		addNoParam(Function_String_Upper.INSTANCE);

		return (NO_PARAM_STRING_RET)this;
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public final NO_PARAM_STRING_RET trim() {
		addNoParam(Function_String_Trim.INSTANCE);

		return (NO_PARAM_STRING_RET)this;
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public final NO_PARAM_ARITHMETIC_SAME_TYPE_RET abs() {
		addNoParam(Function_Arithmetic_Abs.INSTANCE);

		return (NO_PARAM_ARITHMETIC_SAME_TYPE_RET)this;
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public final NO_PARAM_ARITHMETIC_DOUBLE_RET sqrt() {
		addNoParam(Function_Arithmetic_Sqrt.INSTANCE);

		return (NO_PARAM_ARITHMETIC_DOUBLE_RET)this;
	}
}
