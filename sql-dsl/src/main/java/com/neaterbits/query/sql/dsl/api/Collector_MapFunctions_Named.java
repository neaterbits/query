package com.neaterbits.query.sql.dsl.api;

final class Collector_MapFunctions_Named<

			MODEL,
			RESULT,

			RET extends ISharedSelectSourceBuilder<MODEL, RESULT>,

			// for sums, we return Long for short and so must differentiate from other aggregate
			// functions, this is because sum may wrap over limits of type
			SUM_LONG_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
			COUNT_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,

			// for other types aggregates, we return the same result as the input type, eg.
			// max of short-type will never be > short type
			SHORT_RET 		extends ISharedFunction_Next<MODEL, RESULT, RET>,
			INT_RET	  		extends ISharedFunction_Next<MODEL, RESULT, RET>,
			LONG_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
			DOUBLE_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
			BIGDECIMAL_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
			STRING_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>

			>
			
		
	extends Collector_SharedFunctions_Named<
			MODEL,
			RESULT,
			
			RET,
			
			INT_RET,
			LONG_RET,
			DOUBLE_RET,
			STRING_RET> 


			
		implements ISharedMapFunctions_Named<MODEL, RESULT, RET, SUM_LONG_RET, COUNT_RET, SHORT_RET, INT_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET, STRING_RET> {

	Collector_MapFunctions_Named(ISharedCollector_Functions_Callback_Named<MODEL, RESULT, RET> func, Collector_SharedFunctions_Base<MODEL, RESULT> last) {
		super(func, last);
	}

	@Override
	public <T> SHORT_RET avg(IFunctionShort<T> field) {
		return addAndReturnType(Function_Aggregate.AVG, field);
	}

	@Override
	public <T> INT_RET avg(IFunctionInteger<T> field) {
		return addAndReturnType(Function_Aggregate.AVG, field);
	}

	@Override
	public <T> LONG_RET avg(IFunctionLong<T> field) {
		return addAndReturnType(Function_Aggregate.AVG, field);
	}

	@Override
	public <T> BIGDECIMAL_RET avg(IFunctionBigDecimal<T> field) {
		return addAndReturnType(Function_Aggregate.AVG, field);
	}

	@Override
	public <T> COUNT_RET count(IFunctionShort<T> field) {
		return addAndReturnType(Function_Aggregate.COUNT, field);
	}

	@Override
	public <T> COUNT_RET count(IFunctionInteger<T> field) {
		return addAndReturnType(Function_Aggregate.COUNT, field);
	}

	@Override
	public <T> COUNT_RET count(IFunctionLong<T> field) {
		return addAndReturnType(Function_Aggregate.COUNT, field);
	}

	@Override
	public <T> COUNT_RET count(IFunctionBigDecimal<T> field) {
		return addAndReturnType(Function_Aggregate.COUNT, field);
	}

	@Override
	public <T> SHORT_RET max(IFunctionShort<T> field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public <T> INT_RET max(IFunctionInteger<T> field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public <T> LONG_RET max(IFunctionLong<T> field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public <T> BIGDECIMAL_RET max(IFunctionBigDecimal<T> field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public <T> SHORT_RET min(IFunctionShort<T> field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public <T> INT_RET min(IFunctionInteger<T> field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public <T> LONG_RET min(IFunctionLong<T> field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public <T> BIGDECIMAL_RET min(IFunctionBigDecimal<T> field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public <T> SUM_LONG_RET sum(IFunctionShort<T> field) {
		return addAndReturnType(Function_Aggregate.SUM, field);
	}

	@Override
	public <T> SUM_LONG_RET sum(IFunctionInteger<T> field) {
		return addAndReturnType(Function_Aggregate.SUM, field);
	}

	@Override
	public <T> SUM_LONG_RET sum(IFunctionLong<T> field) {
		return addAndReturnType(Function_Aggregate.SUM, field);
	}

	@Override
	public <T> BIGDECIMAL_RET sum(IFunctionBigDecimal<T> field) {
		return addAndReturnType(Function_Aggregate.SUM, field);
	}

	// NoParam
	@Override
	public ISharedMapFunctions_Numeric_Named<MODEL, RESULT, RET, SUM_LONG_RET, COUNT_RET, SHORT_RET, INT_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET> abs() {
		add(Function_Arithmetic_Abs.INSTANCE);
		
		return this;
	}

	/*
	@Override
	public ISharedMapFunctions_Numeric_Named<MODEL, RESULT, RET, SUM_LONG_RET, COUNT_RET, SHORT_RET, INT_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET> sqrt() {
		add(Function_Arithmetic_Sqrt.INSTANCE);

		return this;
	}
	*/

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public ISharedMapFunctions_Numeric_Named<MODEL, RESULT, RET, DOUBLE_RET, DOUBLE_RET, DOUBLE_RET, DOUBLE_RET, DOUBLE_RET, DOUBLE_RET, DOUBLE_RET> sqrt() {
		add(Function_Arithmetic_Sqrt.INSTANCE);

		return (ISharedMapFunctions_Numeric_Named)this;
	}
	
	
	@Override
	public ISharedFunctions_String_Named<MODEL, RESULT, RET, STRING_RET> lower() {
		add(Function_String_Lower.INSTANCE);
		
		return this;
	}


	@Override
	public ISharedFunctions_String_Named<MODEL, RESULT, RET, STRING_RET> upper() {
		add(Function_String_Upper.INSTANCE);
		
		return this;
	}

	@Override
	public ISharedFunctions_String_Named<MODEL, RESULT, RET, STRING_RET> trim() {
		add(Function_String_Trim.INSTANCE);
		
		return this;
	}
}

