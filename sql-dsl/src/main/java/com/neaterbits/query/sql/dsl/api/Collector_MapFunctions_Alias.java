package com.neaterbits.query.sql.dsl.api;

final class Collector_MapFunctions_Alias<
		MODEL,
		RESULT,

		RET extends ISharedSelectSourceBuilder<MODEL, RESULT>,

		// for sums, we return Long for short and int so must differentiate from other aggregate
		// fuctions, this is because sum may wrap over limits of type
		SUM_LONG_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		COUNT_RET 	 extends ISharedFunction_Next<MODEL, RESULT, RET>,
		
		// for other types aggregates, we return the same result as the input type, eg.
		// max of short-type will never be > short type
		SHORT_RET 	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		INT_RET 	extends ISharedFunction_Next<MODEL, RESULT, RET>, 
		LONG_RET 	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		DOUBLE_RET 	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_RET 	extends ISharedFunction_Next<MODEL, RESULT, RET>
	>


	extends Collector_SharedFunctions_Alias<
		MODEL,
		RESULT,

		RET,

		INT_RET,
		LONG_RET,
		DOUBLE_RET,
		STRING_RET> 
	
	
		implements 
		
			ISharedMapFunctions_Alias<MODEL, RESULT, RET, SUM_LONG_RET, COUNT_RET, SHORT_RET, INT_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET, STRING_RET>
	
	{

	Collector_MapFunctions_Alias(ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, RET> func) {
		super(func, null);
	}

	@Override
	public SHORT_RET avg(ISupplierShort field) {
		return addAndReturnType(Function_Aggregate.AVG, field);
	}

	@Override
	public INT_RET avg(ISupplierInteger field) {
		return addAndReturnType(Function_Aggregate.AVG, field);
	}

	@Override
	public LONG_RET avg(ISupplierLong field) {
		return addAndReturnType(Function_Aggregate.AVG, field);
	}

	@Override
	public BIGDECIMAL_RET avg(ISupplierBigDecimal field) {
		return addAndReturnType(Function_Aggregate.AVG, field);
	}

	@Override
	public COUNT_RET count(ISupplierShort field) {
		return addAndReturnType(Function_Aggregate.COUNT, field);
	}

	@Override
	public COUNT_RET count(ISupplierInteger field) {
		return addAndReturnType(Function_Aggregate.COUNT, field);
	}

	@Override
	public COUNT_RET count(ISupplierLong field) {
		return addAndReturnType(Function_Aggregate.COUNT, field);
	}

	@Override
	public COUNT_RET count(ISupplierBigDecimal field) {
		return addAndReturnType(Function_Aggregate.COUNT, field);
	}

	@Override
	public SHORT_RET max(ISupplierShort field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public INT_RET max(ISupplierInteger field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public LONG_RET max(ISupplierLong field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public BIGDECIMAL_RET max(ISupplierBigDecimal field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public SHORT_RET min(ISupplierShort field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public INT_RET min(ISupplierInteger field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public LONG_RET min(ISupplierLong field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public BIGDECIMAL_RET min(ISupplierBigDecimal field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public SUM_LONG_RET sum(ISupplierShort field) {
		return addAndReturnType(Function_Aggregate.SUM, field);
	}

	@Override
	public SUM_LONG_RET sum(ISupplierInteger field) {
		return addAndReturnType(Function_Aggregate.SUM, field);
	}

	@Override
	public SUM_LONG_RET sum(ISupplierLong field) {
		return addAndReturnType(Function_Aggregate.SUM, field);
	}

	@Override
	public BIGDECIMAL_RET sum(ISupplierBigDecimal field) {
		return addAndReturnType(Function_Aggregate.SUM, field);
	}
}
