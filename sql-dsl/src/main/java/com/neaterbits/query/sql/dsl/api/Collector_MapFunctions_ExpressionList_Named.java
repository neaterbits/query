package com.neaterbits.query.sql.dsl.api;

@Deprecated
final class Collector_MapFunctions_ExpressionList_Named<

			MODEL,
			RESULT,

			RET extends ISharedSelectSourceBuilder<MODEL, RESULT>,

			// for sums, we return Long for short and so must differentiate from other aggregate
			// functions, this is because sum may wrap over limits of type
			SUM_LONG_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
			COUNT_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,

			// for other types aggregates, we return the same result as the input type, eg.
			// max of short-type will never be > short type
			BYTE_RET 		extends ISharedFunction_Next<MODEL, RESULT, RET>,
			SHORT_RET 		extends ISharedFunction_Next<MODEL, RESULT, RET>,
			INT_RET	  		extends ISharedFunction_Next<MODEL, RESULT, RET>,
			LONG_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
			BIGINTEGER_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
			FLOAT_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
			DOUBLE_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
			BIGDECIMAL_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
			STRING_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
			DATE_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
			CALENDAR_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
			SQLDATE_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
			SQLTIME_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
			SQLTIMESTAMP_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
			
			
			NO_PARAM_SUM_LONG_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
			NO_PARAM_COUNT_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
			

			NO_PARAM_BYTE_RET 		extends ISharedFunction_Next<MODEL, RESULT, RET>,
			NO_PARAM_SHORT_RET 		extends ISharedFunction_Next<MODEL, RESULT, RET>,
			NO_PARAM_INT_RET	  	extends ISharedFunction_Next<MODEL, RESULT, RET>,
			NO_PARAM_LONG_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
			NO_PARAM_BIGINTEGER_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
			NO_PARAM_FLOAT_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
			NO_PARAM_DOUBLE_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
			NO_PARAM_BIGDECIMAL_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
			NO_PARAM_STRING_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
			NO_PARAM_DATE_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
			NO_PARAM_CALENDAR_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
			NO_PARAM_SQLDATE_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
			NO_PARAM_SQLTIME_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
			NO_PARAM_SQLTIMESTAMP_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>
>

		extends ResultMapper_ExpressionList_Initial_Named<MODEL, RESULT, RET,
		
			SUM_LONG_RET, COUNT_RET, BYTE_RET, SHORT_RET, INT_RET, LONG_RET, BIGINTEGER_RET, FLOAT_RET, DOUBLE_RET, BIGDECIMAL_RET, STRING_RET, DATE_RET, CALENDAR_RET, SQLDATE_RET, SQLTIME_RET, SQLTIMESTAMP_RET
		>

	implements ISharedMapFunctions_All_Named<MODEL, RESULT, RET,
			SUM_LONG_RET, COUNT_RET, BYTE_RET, SHORT_RET, INT_RET, LONG_RET, BIGINTEGER_RET, FLOAT_RET, DOUBLE_RET, BIGDECIMAL_RET, DATE_RET, STRING_RET,
			NO_PARAM_SUM_LONG_RET, NO_PARAM_COUNT_RET, NO_PARAM_BYTE_RET, NO_PARAM_SHORT_RET, NO_PARAM_INT_RET, NO_PARAM_LONG_RET, NO_PARAM_BIGINTEGER_RET, NO_PARAM_FLOAT_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_BIGDECIMAL_RET, NO_PARAM_DATE_RET, NO_PARAM_STRING_RET>,
		
		    ISharedFunction_Next<MODEL, RESULT, RET>,
			
		    // May be called ".to()" here if we just collect functions 
		    //ISharedResultMapperTo<MODEL, RESULT, Comparable, RET>
			
		    ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer, RET>
			
			{

				
	Collector_MapFunctions_ExpressionList_Named(/*ISharedCollector_Functions_Callback_Named<MODEL, RESULT, RET> func,*/ /*, Collector_NestedFunctions_Base<MODEL, RESULT> last, */ IMappingCollector<MODEL, RESULT> impl ) {
		super(impl);
	}

	@Override
	public ISharedMapFunctions_Numeric_Named<
			MODEL, RESULT, RET,
			NO_PARAM_SUM_LONG_RET, NO_PARAM_COUNT_RET,
			
			NO_PARAM_BYTE_RET, NO_PARAM_SHORT_RET, NO_PARAM_INT_RET, NO_PARAM_LONG_RET, NO_PARAM_BIGINTEGER_RET, NO_PARAM_FLOAT_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_BIGDECIMAL_RET, NO_PARAM_DATE_RET, 
			NO_PARAM_SUM_LONG_RET, NO_PARAM_COUNT_RET,
			NO_PARAM_BYTE_RET, NO_PARAM_SHORT_RET, NO_PARAM_INT_RET, NO_PARAM_LONG_RET, NO_PARAM_BIGINTEGER_RET, NO_PARAM_FLOAT_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_BIGDECIMAL_RET, NO_PARAM_DATE_RET
			
		> abs() {
			
		return super.absNamedNoParam();
	}

	@Override
	public ISharedMapFunctions_Numeric_Named<
			MODEL, RESULT, RET, 
			NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET,
			NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET,
			NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET,
			NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET> sqrt() {
				
		return super.sqrtNamedNoParam();
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

