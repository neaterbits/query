package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.neaterbits.query.sql.dsl.api.entity.ScalarType;

final class AdhocFunctions<
		MODEL,
		RESULT,
		ENTITY,
		RET extends ISharedLogical_Base<MODEL, RESULT>,

		INTEGER_CLAUSE extends ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Integer, RET>,
		LONG_CLAUSE extends ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Long, RET>,
		STRING_CLAUSE extends ISharedComparison_Comparable_String_Value<MODEL, RESULT, RET>>

	implements IAdhocFunctions_Arithmetic<MODEL, RESULT, ENTITY, RET, INTEGER_CLAUSE, LONG_CLAUSE>,
			   IAdhocFunctions_String<MODEL, RESULT, ENTITY, RET, STRING_CLAUSE>,
			   IAdhocFunctions_Initial<MODEL, RESULT, ENTITY, RET, INTEGER_CLAUSE, LONG_CLAUSE, STRING_CLAUSE> {

				  
				   
				   
	private final ConditionsType conditionsType;
	private ScalarType scalarType; 
	private final IAdhocFunctions_Callback<MODEL, RESULT, ?> listener;
		
	private final List<FunctionCalcBase> functions;


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
	
	private void setScalarType(ScalarType scalarType) {
		if (scalarType == null) {
			throw new IllegalArgumentException("scalarType == null");
		}
		
		if (this.scalarType != null && this.scalarType != scalarType) {
			throw new IllegalArgumentException("scalar type changed from " + this.scalarType + " to " + scalarType);
		}

		this.scalarType = scalarType;
	}

	/**************************************************************************
	** Apply
	**************************************************************************/
	
	Object applyTo(Object value) {
		
		for (FunctionCalcBase function : functions) {
			value = function.applyTo(scalarType, value);
		}
		
		return value;
	}

	
	/**************************************************************************
	** Getters
	**************************************************************************/

	
	ConditionsType getConditionsType() {
		return conditionsType;
	}

	List<FunctionCalcBase> getFunctions() {
		return functions;
	}
	

	/**************************************************************************
	** Helpers
	**************************************************************************/
	private void add(FunctionCalcBase function) {
		if (function == null) {
			throw new IllegalArgumentException("function == null");
		}

		functions.add(function);
	}

	@SuppressWarnings("unchecked")
	private <VALUE extends Comparable<?>,
			CLAUSE extends ISharedComparison_Comparable_Common_Value<MODEL, RESULT, VALUE, RET>>
	
			CLAUSE onComparable(Function_Arithmetic f, Function<?, ? extends Comparable<?>> getter) {
		add(f);
		
		return (CLAUSE)listener.onComparable(this, getter);
	}
		
	@SuppressWarnings("unchecked")
	private STRING_CLAUSE onString(Function_String f, IFunctionString<?> getter) {
		add(f);
		
		return (STRING_CLAUSE)listener.onString(this, getter);
	}
	
	/**************************************************************************
	** IAdhocFunctions_Arithmetic
	**************************************************************************/
	
	@Override
	public INTEGER_CLAUSE abs(IFunctionInteger<ENTITY> getter) {
		
		setScalarType(ScalarType.INTEGER);
		
		return onComparable(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public LONG_CLAUSE abs(IFunctionLong<ENTITY> getter) {
		setScalarType(ScalarType.LONG);

		return onComparable(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public IAdhocFunctions_Arithmetic<MODEL, RESULT, ENTITY, RET, INTEGER_CLAUSE, LONG_CLAUSE> abs() {
		
		add(Function_Arithmetic_Abs.INSTANCE);
		
		return this;
	}

	@Override
	public INTEGER_CLAUSE sqrt(IFunctionInteger<ENTITY> getter) {
		setScalarType(ScalarType.INTEGER);

		return onComparable(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public LONG_CLAUSE sqrt(IFunctionLong<ENTITY> getter) {
		setScalarType(ScalarType.LONG);

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
	public STRING_CLAUSE lower(IFunctionString<ENTITY> getter) {
		return onString(Function_String_Lower.INSTANCE, getter);
	}

	@Override
	public IAdhocFunctions_String<MODEL, RESULT, ENTITY, RET, STRING_CLAUSE> lower() {
		setScalarType(ScalarType.STRING);
		
		add(Function_String_Lower.INSTANCE);
		
		return this;
	}

	@Override
	public STRING_CLAUSE upper(IFunctionString<ENTITY> getter) {
		setScalarType(ScalarType.STRING);
		
		return onString(Function_String_Upper.INSTANCE, getter);
	}

	@Override
	public IAdhocFunctions_String<MODEL, RESULT, ENTITY, RET, STRING_CLAUSE> upper() {
		setScalarType(ScalarType.STRING);

		add(Function_String_Upper.INSTANCE);
		
		return this;
	}

	@Override
	public STRING_CLAUSE trim(IFunctionString<ENTITY> getter) {
		setScalarType(ScalarType.STRING);

		return onString(Function_String_Trim.INSTANCE, getter);
	}

	@Override
	public IAdhocFunctions_String<MODEL, RESULT, ENTITY, RET, STRING_CLAUSE> trim() {
		setScalarType(ScalarType.STRING);
		
		add(Function_String_Trim.INSTANCE);
		
		return this;
	}
}
