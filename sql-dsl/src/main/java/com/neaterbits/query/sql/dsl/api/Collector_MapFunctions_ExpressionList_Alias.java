package com.neaterbits.query.sql.dsl.api;

final class Collector_MapFunctions_ExpressionList_Alias<

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
		STRING_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		
		
		NO_PARAM_SUM_LONG_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		NO_PARAM_COUNT_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		
		
		NO_PARAM_SHORT_RET 		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		NO_PARAM_INT_RET	  		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		NO_PARAM_LONG_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		NO_PARAM_DOUBLE_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		NO_PARAM_BIGDECIMAL_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		NO_PARAM_STRING_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>
	>

	extends ResultMapper_ExpressionList_Initial_Alias<MODEL, RESULT, RET,

		SUM_LONG_RET, COUNT_RET, SHORT_RET, INT_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET, STRING_RET
			>

implements ISharedMapFunctions_All_Alias<MODEL, RESULT, RET,
		SUM_LONG_RET, COUNT_RET, SHORT_RET, INT_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET, STRING_RET,
		NO_PARAM_SUM_LONG_RET, NO_PARAM_COUNT_RET, NO_PARAM_SHORT_RET, NO_PARAM_INT_RET, NO_PARAM_LONG_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_BIGDECIMAL_RET, NO_PARAM_STRING_RET>,

			ISharedFunction_Next<MODEL, RESULT, RET>,

		// May be called ".to()" here if we just collect functions 
		//ISharedResultMapperTo<MODEL, RESULT, Comparable, RET>
		
		ISharedResultMap_OpsAndTo_Numeric_Alias<MODEL, RESULT, Integer, RET>

{

	
	Collector_MapFunctions_ExpressionList_Alias(/*ISharedCollector_Functions_Callback_Named<MODEL, RESULT, RET> func,*/ /*, Collector_NestedFunctions_Base<MODEL, RESULT> last, */ IMappingCollector<MODEL, RESULT> impl ) {
	super(impl);
	}
	
	@Override
	public ISharedMapFunctions_Numeric_Alias<MODEL, RESULT, RET, NO_PARAM_SUM_LONG_RET, NO_PARAM_COUNT_RET, NO_PARAM_SHORT_RET, NO_PARAM_INT_RET, NO_PARAM_LONG_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_BIGDECIMAL_RET, NO_PARAM_SUM_LONG_RET, NO_PARAM_COUNT_RET, NO_PARAM_SHORT_RET, NO_PARAM_INT_RET, NO_PARAM_LONG_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_BIGDECIMAL_RET> abs() {
	return super.absNoParam();
	}
	
	@Override
	public ISharedMapFunctions_Numeric_Alias<MODEL, RESULT, RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET> sqrt() {
		return super.sqrtNoParam();
	}
	
	@Override
	public NO_PARAM_STRING_RET lower() {
		throw new UnsupportedOperationException("TODO");
	}
	
	@Override
	public NO_PARAM_STRING_RET upper() {
		throw new UnsupportedOperationException("TODO");
	}
	
	@Override
	public NO_PARAM_STRING_RET trim() {
		throw new UnsupportedOperationException("TODO");
	}

}