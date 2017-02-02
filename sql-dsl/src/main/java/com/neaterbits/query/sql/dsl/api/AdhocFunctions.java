package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

final class AdhocFunctions<
		MODEL,
		RESULT,
		ENTITY,
		RET extends ISharedLogical_Base<MODEL, RESULT>,

		INTEGER_CLAUSE extends ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Integer, RET>,
		LONG_CLAUSE extends ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Long, RET>,
		STRING_CLAUSE extends ISharedCondition_Comparable_String_Value<MODEL, RESULT, RET>>

	implements IAdhocFunctions_Arithmetic<MODEL, RESULT, ENTITY, RET, INTEGER_CLAUSE, LONG_CLAUSE>,
			   IAdhocFunctions_String<MODEL, RESULT, ENTITY, RET, STRING_CLAUSE>,
			   IAdhocFunctions_Initial<MODEL, RESULT, ENTITY, RET, INTEGER_CLAUSE, LONG_CLAUSE, STRING_CLAUSE> {

				  
				   
				   
	private final ConditionsType conditionsType;
	private final IAdhocFunctions_Callback<MODEL, RESULT, ?> listener;
		
	private final List<FunctionBase> functions;
	
	

	AdhocFunctions(ConditionsType conditionsType, IAdhocFunctions_Callback<MODEL, RESULT, ?> listener) {

		if (conditionsType == null) {
			throw new IllegalArgumentException("conditionsType == null");
		}

		if (listener == null) {
			throw new IllegalArgumentException("listener == null");
		}

		this.conditionsType = conditionsType;
		this.listener = listener;
		this.functions = new ArrayList<>();
	}

	
	/**************************************************************************
	** Getters
	**************************************************************************/

	
	ConditionsType getConditionsType() {
		return conditionsType;
	}

	List<FunctionBase> getFunctions() {
		return functions;
	}
	

	/**************************************************************************
	** Helpers
	**************************************************************************/
	private void add(FunctionBase function) {
		if (function == null) {
			throw new IllegalArgumentException("function == null");
		}
	}

	@SuppressWarnings("unchecked")
	private <VALUE extends Comparable<?>,
			CLAUSE extends ISharedCondition_Comparable_Common_Value<MODEL, RESULT, VALUE, RET>>
	
			CLAUSE onComparable(Function_Arithmetic f, Function<?, ? extends Comparable<?>> getter) {
		add(f);
		
		return (CLAUSE)listener.onComparable(this, getter);
	}
		
	@SuppressWarnings("unchecked")
	private STRING_CLAUSE onString(Function_String f, StringFunction<?> getter) {
		add(f);
		
		return (STRING_CLAUSE)listener.onString(this, getter);
	}
	
	/**************************************************************************
	** IAdhocFunctions_Arithmetic
	**************************************************************************/
	
	@Override
	public INTEGER_CLAUSE abs(IFunctionInteger<ENTITY> getter) {
		return onComparable(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public LONG_CLAUSE abs(IFunctionLong<ENTITY> getter) {
		return onComparable(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public IAdhocFunctions_Arithmetic<MODEL, RESULT, ENTITY, RET, INTEGER_CLAUSE, LONG_CLAUSE> abs() {
		
		add(Function_Arithmetic_Abs.INSTANCE);
		
		return this;
	}

	@Override
	public INTEGER_CLAUSE sqrt(IFunctionInteger<ENTITY> getter) {

		return onComparable(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public LONG_CLAUSE sqrt(IFunctionLong<ENTITY> getter) {

		return onComparable(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public IAdhocFunctions_Arithmetic<MODEL, RESULT, ENTITY, RET, INTEGER_CLAUSE, LONG_CLAUSE> sqrt() {

		add(Function_Arithmetic_Sqrt.INSTANCE);

		return this;
	}

	
	/**************************************************************************
	** IAdhocFunctions_String
	**************************************************************************/

	@Override
	public STRING_CLAUSE lower(StringFunction<ENTITY> getter) {
		return onString(Function_String_Lower.INSTANCE, getter);
	}

	@Override
	public IAdhocFunctions_String<MODEL, RESULT, ENTITY, RET, STRING_CLAUSE> lower() {
		
		add(Function_String_Lower.INSTANCE);
		
		return this;
	}

	@Override
	public STRING_CLAUSE upper(StringFunction<ENTITY> getter) {
		
		return onString(Function_String_Upper.INSTANCE, getter);
	}

	@Override
	public IAdhocFunctions_String<MODEL, RESULT, ENTITY, RET, STRING_CLAUSE> upper() {
		
		add(Function_String_Upper.INSTANCE);
		
		return this;
	}

	@Override
	public STRING_CLAUSE trim(StringFunction<ENTITY> getter) {

		return onString(Function_String_Trim.INSTANCE, getter);
	}

	@Override
	public IAdhocFunctions_String<MODEL, RESULT, ENTITY, RET, STRING_CLAUSE> trim() {
		
		add(Function_String_Trim.INSTANCE);
		
		return this;
	}
}
