package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

final class ResultMapper_ExpressionList_Initial_Undecided<
		MODEL,
		RESULT,

		NAMED_RET extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		ALIAS_RET extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		UNDECIDED_RET extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		
		NAMED_SUM_LONG_RET  extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_COUNT_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_LENGTH_RET	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_BYTE_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SHORT_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_INT_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_LONG_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_BIGINTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_FLOAT_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_DOUBLE_RET	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_STRING_RET 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_DATE_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_CALENDAR_RET 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SQLDATE_RET 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SQLTIME_RET 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SQLTIMESTAMP_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,

		NO_PARAM_NAMED_SUM_LONG_RET  extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NO_PARAM_NAMED_COUNT_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NO_PARAM_NAMED_LENGTH_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NO_PARAM_NAMED_BYTE_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NO_PARAM_NAMED_SHORT_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NO_PARAM_NAMED_INT_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NO_PARAM_NAMED_LONG_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NO_PARAM_NAMED_BIGINTEGER_RET	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NO_PARAM_NAMED_FLOAT_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NO_PARAM_NAMED_DOUBLE_RET	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NO_PARAM_NAMED_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NO_PARAM_NAMED_STRING_RET 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NO_PARAM_NAMED_DATE_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NO_PARAM_NAMED_CALENDAR_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NO_PARAM_NAMED_SQLDATE_RET	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NO_PARAM_NAMED_SQLTIME_RET 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NO_PARAM_NAMED_SQLTIMESTAMP_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		
		ALIAS_SUM_LONG_RET 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_COUNT_RET		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_LENGTH_RET		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_BYTE_RET		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SHORT_RET		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_INT_RET		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_LONG_RET		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_BIGINTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_FLOAT_RET		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_DOUBLE_RET	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_STRING_RET 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_DATE_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_CALENDAR_RET 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SQLDATE_RET 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SQLTIME_RET 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SQLTIMESTAMP_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,

		NO_PARAM_ALIAS_SUM_LONG_RET 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		NO_PARAM_ALIAS_COUNT_RET		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		NO_PARAM_ALIAS_LENGTH_RET		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		NO_PARAM_ALIAS_BYTE_RET		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		NO_PARAM_ALIAS_SHORT_RET	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		NO_PARAM_ALIAS_INT_RET		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		NO_PARAM_ALIAS_LONG_RET		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		NO_PARAM_ALIAS_BIGINTEGER_RET		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		NO_PARAM_ALIAS_FLOAT_RET	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		NO_PARAM_ALIAS_DOUBLE_RET	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		NO_PARAM_ALIAS_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		NO_PARAM_ALIAS_STRING_RET 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		NO_PARAM_ALIAS_DATE_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		NO_PARAM_ALIAS_CALENDAR_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		NO_PARAM_ALIAS_SQLDATE_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		NO_PARAM_ALIAS_SQLTIME_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		NO_PARAM_ALIAS_SQLTIMESTAMP_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		
		UNDECIDED_SUM_LONG_RET 	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_COUNT_RET		extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_LENGTH_RET		extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_BYTE_RET		extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_SHORT_RET		extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_INT_RET		extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_LONG_RET		extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_BIGINTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_FLOAT_RET		extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_DOUBLE_RET	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_STRING_RET 	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_DATE_RET extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_CALENDAR_RET 	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_SQLDATE_RET 	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_SQLTIME_RET 	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_SQLTIMESTAMP_RET extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,

		NO_PARAM_UNDECIDED_SUM_LONG_RET 	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		NO_PARAM_UNDECIDED_COUNT_RET		extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		NO_PARAM_UNDECIDED_LENGTH_RET		extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		NO_PARAM_UNDECIDED_BYTE_RET		extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		NO_PARAM_UNDECIDED_SHORT_RET	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		NO_PARAM_UNDECIDED_INT_RET		extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		NO_PARAM_UNDECIDED_LONG_RET		extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		NO_PARAM_UNDECIDED_BIGINTEGER_RET		extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		NO_PARAM_UNDECIDED_FLOAT_RET	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		NO_PARAM_UNDECIDED_DOUBLE_RET	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		NO_PARAM_UNDECIDED_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		NO_PARAM_UNDECIDED_STRING_RET 	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		NO_PARAM_UNDECIDED_DATE_RET extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		NO_PARAM_UNDECIDED_CALENDAR_RET extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		NO_PARAM_UNDECIDED_SQLDATE_RET extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		NO_PARAM_UNDECIDED_SQLTIME_RET extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		NO_PARAM_UNDECIDED_SQLTIMESTAMP_RET extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>
		
		>

	extends ResultMapper_ExpressionList_Base<
			MODEL,
			RESULT, 
			
			Integer,
			
			ISharedFunction_After<MODEL, RESULT>,
			
			NAMED_RET,
			ALIAS_RET,
			UNDECIDED_RET,
			
			ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Integer, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Integer, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			
			NAMED_SUM_LONG_RET,
			NAMED_COUNT_RET,
			NAMED_LENGTH_RET,
			
			NAMED_BYTE_RET,
			NAMED_SHORT_RET,
			NAMED_INT_RET,
			NAMED_LONG_RET,
			NAMED_BIGINTEGER_RET,
			NAMED_FLOAT_RET,
			NAMED_DOUBLE_RET,
			NAMED_BIGDECIMAL_RET,
			NAMED_STRING_RET,
			NAMED_DATE_RET,
			NAMED_CALENDAR_RET,
			NAMED_SQLDATE_RET,
			NAMED_SQLTIME_RET,
			NAMED_SQLTIMESTAMP_RET,

			ALIAS_SUM_LONG_RET,
			ALIAS_COUNT_RET,
			ALIAS_LENGTH_RET,
			
			ALIAS_BYTE_RET,
			ALIAS_SHORT_RET,
			ALIAS_INT_RET,
			ALIAS_LONG_RET,
			ALIAS_BIGINTEGER_RET,
			ALIAS_FLOAT_RET,
			ALIAS_DOUBLE_RET,
			ALIAS_BIGDECIMAL_RET,
			ALIAS_STRING_RET,
			ALIAS_DATE_RET,
			ALIAS_CALENDAR_RET,
			ALIAS_SQLDATE_RET,
			ALIAS_SQLTIME_RET,
			ALIAS_SQLTIMESTAMP_RET,
			
			UNDECIDED_SUM_LONG_RET,
			UNDECIDED_COUNT_RET,
			UNDECIDED_LENGTH_RET,
			
			UNDECIDED_BYTE_RET,
			UNDECIDED_SHORT_RET,
			UNDECIDED_INT_RET,
			UNDECIDED_LONG_RET,
			UNDECIDED_BIGINTEGER_RET,
			UNDECIDED_FLOAT_RET,
			UNDECIDED_DOUBLE_RET,
			UNDECIDED_BIGDECIMAL_RET,
			UNDECIDED_STRING_RET,
			UNDECIDED_DATE_RET,
			UNDECIDED_CALENDAR_RET,
			UNDECIDED_SQLDATE_RET,
			UNDECIDED_SQLTIME_RET,
			UNDECIDED_SQLTIMESTAMP_RET
			
			>

	
	implements
		
	
		ISharedMap_Functions_All_Undecided<
			MODEL,
			RESULT,

			NAMED_RET,
			ALIAS_RET,
			UNDECIDED_RET,
			
			NAMED_SUM_LONG_RET,
			NAMED_COUNT_RET,
			NAMED_LENGTH_RET,
			NAMED_BYTE_RET,
			NAMED_SHORT_RET,
			NAMED_INT_RET,
			NAMED_LONG_RET,
			NAMED_BIGINTEGER_RET,
			NAMED_FLOAT_RET,
			NAMED_DOUBLE_RET,
			NAMED_BIGDECIMAL_RET,
			NAMED_STRING_RET,
			NAMED_DATE_RET,
			NAMED_CALENDAR_RET,
			NAMED_SQLDATE_RET,
			NAMED_SQLTIME_RET,
			NAMED_SQLTIMESTAMP_RET,

			NO_PARAM_NAMED_SUM_LONG_RET,
			NO_PARAM_NAMED_COUNT_RET,
			NO_PARAM_NAMED_LENGTH_RET,
			NO_PARAM_NAMED_BYTE_RET,
			NO_PARAM_NAMED_SHORT_RET,
			NO_PARAM_NAMED_INT_RET,
			NO_PARAM_NAMED_LONG_RET,
			NO_PARAM_NAMED_BIGINTEGER_RET,
			NO_PARAM_NAMED_FLOAT_RET,
			NO_PARAM_NAMED_DOUBLE_RET,
			NO_PARAM_NAMED_BIGDECIMAL_RET,
			NO_PARAM_NAMED_STRING_RET,
			NO_PARAM_NAMED_DATE_RET,
			NO_PARAM_NAMED_CALENDAR_RET,
			NO_PARAM_NAMED_SQLDATE_RET,
			NO_PARAM_NAMED_SQLTIME_RET,
			NO_PARAM_NAMED_SQLTIMESTAMP_RET,
			
			ALIAS_SUM_LONG_RET,
			ALIAS_COUNT_RET,
			ALIAS_LENGTH_RET,
			ALIAS_BYTE_RET,
			ALIAS_SHORT_RET,
			ALIAS_INT_RET,
			ALIAS_LONG_RET,
			ALIAS_BIGINTEGER_RET,
			ALIAS_FLOAT_RET,
			ALIAS_DOUBLE_RET,
			ALIAS_BIGDECIMAL_RET,
			ALIAS_STRING_RET,
			ALIAS_DATE_RET,
			ALIAS_CALENDAR_RET,
			ALIAS_SQLDATE_RET,
			ALIAS_SQLTIME_RET,
			ALIAS_SQLTIMESTAMP_RET,

			NO_PARAM_ALIAS_SUM_LONG_RET,
			NO_PARAM_ALIAS_COUNT_RET,
			NO_PARAM_ALIAS_LENGTH_RET,
			NO_PARAM_ALIAS_BYTE_RET,
			NO_PARAM_ALIAS_SHORT_RET,
			NO_PARAM_ALIAS_INT_RET,
			NO_PARAM_ALIAS_LONG_RET,
			NO_PARAM_ALIAS_BIGINTEGER_RET,
			NO_PARAM_ALIAS_FLOAT_RET,
			NO_PARAM_ALIAS_DOUBLE_RET,
			NO_PARAM_ALIAS_BIGDECIMAL_RET,
			NO_PARAM_ALIAS_STRING_RET,
			NO_PARAM_ALIAS_DATE_RET,
			NO_PARAM_ALIAS_CALENDAR_RET,
			NO_PARAM_ALIAS_SQLDATE_RET,
			NO_PARAM_ALIAS_SQLTIME_RET,
			NO_PARAM_ALIAS_SQLTIMESTAMP_RET,
			
			UNDECIDED_SUM_LONG_RET,
			UNDECIDED_COUNT_RET,
			UNDECIDED_LENGTH_RET,
			UNDECIDED_BYTE_RET,
			UNDECIDED_SHORT_RET,
			UNDECIDED_INT_RET,
			UNDECIDED_LONG_RET,
			UNDECIDED_BIGINTEGER_RET,
			UNDECIDED_FLOAT_RET,
			UNDECIDED_DOUBLE_RET,
			UNDECIDED_BIGDECIMAL_RET,
			UNDECIDED_STRING_RET,
			UNDECIDED_DATE_RET,
			UNDECIDED_CALENDAR_RET,
			UNDECIDED_SQLDATE_RET,
			UNDECIDED_SQLTIME_RET,
			UNDECIDED_SQLTIMESTAMP_RET,

			NO_PARAM_UNDECIDED_SUM_LONG_RET,
			NO_PARAM_UNDECIDED_COUNT_RET,
			NO_PARAM_UNDECIDED_LENGTH_RET,
			NO_PARAM_UNDECIDED_BYTE_RET,
			NO_PARAM_UNDECIDED_SHORT_RET,
			NO_PARAM_UNDECIDED_INT_RET,
			NO_PARAM_UNDECIDED_LONG_RET,
			NO_PARAM_UNDECIDED_BIGINTEGER_RET,
			NO_PARAM_UNDECIDED_FLOAT_RET,
			NO_PARAM_UNDECIDED_DOUBLE_RET,
			NO_PARAM_UNDECIDED_BIGDECIMAL_RET,
			NO_PARAM_UNDECIDED_STRING_RET,
			NO_PARAM_UNDECIDED_DATE_RET,
			NO_PARAM_UNDECIDED_CALENDAR_RET,
			NO_PARAM_UNDECIDED_SQLDATE_RET,
			NO_PARAM_UNDECIDED_SQLTIME_RET,
			NO_PARAM_UNDECIDED_SQLTIMESTAMP_RET			
		>,

		ISharedFunctions_StringResult_Undecided<
			MODEL,
			RESULT,
			NAMED_RET,
			ALIAS_RET,
			NAMED_STRING_RET,
			ALIAS_STRING_RET
		>,

		ISharedFunctions_String_StringResult_Undecided<
			MODEL,
			RESULT,
			NAMED_RET,
			ALIAS_RET, 
			NAMED_STRING_RET,
			ALIAS_STRING_RET
		>,
		ISharedMap_Functions_String_StringResult_Undecided<
			MODEL,
			RESULT,
			NAMED_RET,
			ALIAS_RET, 
			NAMED_STRING_RET,
			NAMED_STRING_RET,
			ALIAS_STRING_RET,
			ALIAS_STRING_RET
		>			
						
	//,
			
	//ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer, ISharedFunction_After<MODEL,RESULT>>
	//ISharedResultOps_String_Named<MODEL, RESULT, ISharedFunction_After<MODEL,RESULT>>
		
		/*, TODO
	ISharedResultOps_Numeric_Alias<MODEL, RESULT, Integer, ISharedFunction_After<MODEL, RESULT> > */ {
	
	private final Supplier<IMappingCollector<MODEL, RESULT>> getNamed;
	private final Supplier<IMappingCollector<MODEL, RESULT>> getAliased;
	private final Supplier<IMappingCollector<MODEL, RESULT>> getUndecided;
	
	ResultMapper_ExpressionList_Initial_Undecided(
				// IMappingCollector<MODEL, RESULT> impl, 
				Supplier<IMappingCollector<MODEL, RESULT>> getNamed,
				Supplier<IMappingCollector<MODEL, RESULT>> getAliased,
				Supplier<IMappingCollector<MODEL, RESULT>> getUndecided) {
		//super(impl);

		if (getNamed == null) {
			throw new IllegalArgumentException("getNamed == null");
		}
		
		if (getAliased == null) {
			throw new IllegalArgumentException("getAliased == null");
		}
		
		if (getUndecided == null) {
			throw new IllegalArgumentException("getUndecided == null");
		}

		this.getNamed = getNamed;
		this.getAliased = getAliased;
		this.getUndecided = getUndecided;
	}
	
	

	@Override
	IMappingCollector<MODEL, RESULT> getMappingCollector(EFieldAccessType fieldAccessType) {
		
		final IMappingCollector<MODEL, RESULT> ret;
		
		switch (fieldAccessType) {
		case NAMED:
			ret = getNamed.get();
			break;
			
		case ALIAS:
			ret = getAliased.get();
			break;
			
		case UNDECIDED:
			ret = getUndecided.get();
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown field access type: " + fieldAccessType);
		}
		
		return ret;
	}

	
			

	
	
			
			/*
			
	private Collector_MapFunctions_Named<
			MODEL, RESULT, NAMED_RET,
			NAMED_SUM_LONG_RET, NAMED_COUNT_RET, NAMED_SHORT_RET, NAMED_INT_RET, NAMED_LONG_RET, NAMED_DOUBLE_RET, NAMED_BIGDECIMAL_RET, NAMED_STRING_RET,
			NO_PARAM_NAMED_SUM_LONG_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_SHORT_RET, NO_PARAM_NAMED_INT_RET, NO_PARAM_NAMED_LONG_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_BIGDECIMAL_RET, NO_PARAM_NAMED_STRING_RET
			
			> named_do_not_call_directly;
			
			
	private Collector_MapFunctions_Alias<MODEL, RESULT, ALIAS_RET, ALIAS_SUM_LONG_RET, ALIAS_COUNT_RET, ALIAS_SHORT_RET, ALIAS_INT_RET, ALIAS_LONG_RET, ALIAS_DOUBLE_RET, ALIAS_BIGDECIMAL_RET, ALIAS_STRING_RET> alias_do_not_call_directly;

    */
    
	/*
    private final ISharedCollector_Functions_Callback_Named<MODEL, RESULT, NAMED_RET> namedCallback;
    private final ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, ALIAS_RET> aliasCallback;
    */
    
    
    //private final IMappingCollector<MODEL, RESULT> impl;
			
    
    /*
    ResultMapper_ExpressionList_Initial_Undecided(
			ISharedCollector_Functions_Callback_Named<MODEL, RESULT, NAMED_RET> namedCallback,
			ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, ALIAS_RET> aliasCallback
			/* IMappingCollector<MODEL, RESULT> impl */
			//) {

    	//super(null);
    
    	/*
    	if (impl == null) {
    		throw new IllegalArgumentException("impl == null");
    	}
    	*/

				/*
    	this.namedCallback = namedCallback;
		this.aliasCallback = aliasCallback;
		*/

		//this.impl = impl;
	//}
	
    /*
	@Override
	ISharedFunction_Next<MODEL, RESULT, NAMED_RET> continueAfterNamedComparableFunctions(Expression expression) {
		return namedCallback.onComparable(expression);
	}

	@Override
	ISharedFunction_Next<MODEL, RESULT, NAMED_RET> continueAfterNamedStringFunctions(Expression expression) {
		return namedCallback.onString(expression);
	}

	@Override
	ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> continueAfterAliasFunctions(Expression expression) {
		throw new UnsupportedOperationException("TODO");
	} 
	*/   
    


/*

	Collector_MapFunctions_Alias<MODEL, RESULT, ALIAS_RET, ALIAS_SUM_LONG_RET, ALIAS_COUNT_RET, ALIAS_SHORT_RET, ALIAS_INT_RET, ALIAS_LONG_RET, ALIAS_DOUBLE_RET, ALIAS_BIGDECIMAL_RET, ALIAS_STRING_RET> 
    
    	alias() {
    		
    	if (this.named_do_not_call_directly != null) {
    		throw new IllegalStateException("Already set named");
    	}
    	
    	if (this.alias_do_not_call_directly == null) {
    		this.alias_do_not_call_directly = new Collector_MapFunctions_Alias<>(aliasCallback);
    	}
    	
    	return alias_do_not_call_directly;
	}
    	
    	
	Collector_MapFunctions_Named<MODEL, RESULT, NAMED_RET,
	
			NAMED_SUM_LONG_RET, NAMED_COUNT_RET, NAMED_SHORT_RET, NAMED_INT_RET, NAMED_LONG_RET, NAMED_DOUBLE_RET, NAMED_BIGDECIMAL_RET, NAMED_STRING_RET,
			NO_PARAM_NAMED_SUM_LONG_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_SHORT_RET, NO_PARAM_NAMED_INT_RET, NO_PARAM_NAMED_LONG_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_BIGDECIMAL_RET, NO_PARAM_NAMED_STRING_RET>
    	
			named() {

    	if (this.alias_do_not_call_directly != null) {
    		throw new IllegalStateException("Already set alias");
    	}
    	
    	if (this.named_do_not_call_directly == null) {
    		this.named_do_not_call_directly = new Collector_MapFunctions_Named<>(namedCallback, this, impl);
    	}

    	return named_do_not_call_directly;
	}
	*/

			
			/*
	@Override
	public <T> NAMED_SHORT_RET abs(IFunctionShort<T> getter) {
		return named().abs(getter);
	}

	@Override
	public <T> NAMED_INT_RET abs(IFunctionInteger<T> getter) {
		return named().abs(getter);
	}

	@Override
	public <T> NAMED_LONG_RET abs(IFunctionLong<T> getter) {
		return named().abs(getter);
	}

	@Override
	public <T> ALIAS_SHORT_RET abs(ISupplierShort getter) {
		return alias().abs(getter);
	}

	@Override
	public <T> ALIAS_INT_RET abs(ISupplierInteger getter) {
		return alias().abs(getter);
	}

	@Override
	public <T> ALIAS_LONG_RET abs(ISupplierLong getter) {
		return alias().abs(getter);
	}
	
	@Override
	public final <T> NAMED_SHORT_RET absOfShort(ISharedSubOperandsFunction_Short_Named<MODEL, RESULT> sub) {
		return named().absOfShort(sub);
	}

	@Override
	public final <T> NAMED_INT_RET absOfInteger(ISharedSubOperandsFunction_Integer_Named<MODEL, RESULT> sub) {
		return named().absOfInteger(sub);
	}

	@Override
	public final <T> NAMED_LONG_RET absOfLong(ISharedSubOperandsFunction_Long_Named<MODEL, RESULT> sub) {
		return named().absOfLong(sub);
	}

	@Override
	public final <T> NAMED_BIGDECIMAL_RET absOfDecimal(ISharedSubOperandsFunction_BigDecimal_Named<MODEL, RESULT> sub) {
		return named().absOfDecimal(sub);
	}
	

	@Override
	public <T> ALIAS_DOUBLE_RET abs(ISupplierDouble getter) {
		return alias().abs(getter);
	}

	@Override
	public <T> ALIAS_BIGDECIMAL_RET abs(ISupplierBigDecimal getter) {
		return alias().abs(getter);
	}

	@Override
	public <T> NAMED_BIGDECIMAL_RET abs(IFunctionBigDecimal<T> getter) {
		return named().abs(getter);
	}

	@Override
	public <T> NAMED_SHORT_RET avg(IFunctionShort<T> field) {
		return named().avg(field);
	}

	@Override
	public <T> NAMED_INT_RET avg(IFunctionInteger<T> field) {
		return named().avg(field);
	}

	@Override
	public <T> NAMED_LONG_RET avg(IFunctionLong<T> field) {
		return named().avg(field);
	}

	@Override
	public <T> NAMED_BIGDECIMAL_RET avg(IFunctionBigDecimal<T> field) {
		return named().avg(field);
	}
	*/

    
    /*
	@Override
	public ALIAS_SHORT_RET avg(ISupplierShort field) {
		return alias().avg(field);
	}

	@Override
	public ALIAS_INT_RET avg(ISupplierInteger field) {
		return alias().avg(field);
	}

	@Override
	public ALIAS_LONG_RET avg(ISupplierLong field) {
		return alias().avg(field);
	}

	@Override
	public ALIAS_BIGDECIMAL_RET avg(ISupplierBigDecimal field) {
		return alias().avg(field);
	}

	@Override
	public <T> NAMED_COUNT_RET count(IFunctionShort<T> field) {
		return named().count(field);
	}

	@Override
	public <T> NAMED_COUNT_RET count(IFunctionInteger<T> field) {
		return named().count(field);
	}

	@Override
	public <T> NAMED_COUNT_RET count(IFunctionLong<T> field) {
		return named().count(field);
	}

	@Override
	public <T> NAMED_COUNT_RET count(IFunctionBigDecimal<T> field) {
		return named().count(field);
	}

	@Override
	public ALIAS_COUNT_RET count(ISupplierShort field) {
		return alias().count(field);
	}

	@Override
	public ALIAS_COUNT_RET count(ISupplierInteger field) {
		return alias().count(field);
	}

	@Override
	public ALIAS_COUNT_RET count(ISupplierLong field) {
		return alias().count(field);
	}

	@Override
	public ALIAS_COUNT_RET count(ISupplierBigDecimal field) {
		return alias().count(field);
	}

	@Override
	public <T> ALIAS_STRING_RET lower(ISupplierString getter) {
		return alias().lower(getter);
	}

	@Override
	public <T> NAMED_STRING_RET lower(StringFunction<T> getter) {
		return named().lower(getter);
	}

	@Override
	public <T> NAMED_SHORT_RET max(IFunctionShort<T> field) {
		return named().max(field);
	}

	@Override
	public <T> NAMED_INT_RET max(IFunctionInteger<T> field) {
		return named().max(field);
	}

	@Override
	public <T> NAMED_LONG_RET max(IFunctionLong<T> field) {
		return named().max(field);
	}

	@Override
	public <T> NAMED_BIGDECIMAL_RET max(IFunctionBigDecimal<T> field) {
		return named().max(field);
	}

	@Override
	public ALIAS_SHORT_RET max(ISupplierShort field) {
		return alias().max(field);
	}

	@Override
	public ALIAS_INT_RET max(ISupplierInteger field) {
		return alias().max(field);
	}

	@Override
	public ALIAS_LONG_RET max(ISupplierLong field) {
		return alias().max(field);
	}

	@Override
	public ALIAS_BIGDECIMAL_RET max(ISupplierBigDecimal field) {
		return alias().max(field);
	}

	@Override
	public <T> NAMED_SHORT_RET min(IFunctionShort<T> field) {
		return named().min(field);
	}

	@Override
	public <T> NAMED_INT_RET min(IFunctionInteger<T> field) {
		return named().min(field);
	}

	@Override
	public <T> NAMED_LONG_RET min(IFunctionLong<T> field) {
		return named().min(field);
	}

	@Override
	public <T> NAMED_BIGDECIMAL_RET min(IFunctionBigDecimal<T> field) {
		return named().min(field);
	}

	@Override
	public ALIAS_SHORT_RET min(ISupplierShort field) {
		return alias().min(field);
	}

	@Override
	public ALIAS_INT_RET min(ISupplierInteger field) {
		return alias().min(field);
	}

	@Override
	public ALIAS_LONG_RET min(ISupplierLong field) {
		return alias().min(field);
	}

	@Override
	public ALIAS_BIGDECIMAL_RET min(ISupplierBigDecimal field) {
		return alias().min(field);
	}
	
	@Override
	public <T> NAMED_DOUBLE_RET sqrt(IFunctionShort<T> getter) {
		return named().sqrt(getter);
	}

	@Override
	public <T> NAMED_DOUBLE_RET sqrt(IFunctionInteger<T> getter) {
		return named().sqrt(getter);
	}

	@Override
	public <T> NAMED_DOUBLE_RET sqrt(IFunctionLong<T> getter) {
		return named().sqrt(getter);
	}

	@Override
	public <T> NAMED_DOUBLE_RET sqrt(IFunctionBigDecimal<T> getter) {
		return named().sqrt(getter);
	}

	@Override
	public <T> NAMED_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Double_Named<MODEL, RESULT> sub) {
		return named().sqrtOf(sub);
	}

	@Override
	public <T> ALIAS_DOUBLE_RET sqrt(ISupplierShort getter) {
		return alias().sqrt(getter);
	}

	@Override
	public <T> ALIAS_DOUBLE_RET sqrt(ISupplierInteger getter) {
		return alias().sqrt(getter);
	}

	@Override
	public <T> ALIAS_DOUBLE_RET sqrt(ISupplierLong getter) {
		return alias().sqrt(getter);
	}

	@Override
	public <T> ALIAS_DOUBLE_RET sqrt(ISupplierDouble getter) {
		return alias().sqrt(getter);
	}

	@Override
	public <T> ALIAS_DOUBLE_RET sqrt(ISupplierBigDecimal getter) {
		return alias().sqrt(getter);
	}
	
	@Override
	public <T> NAMED_SUM_LONG_RET sum(IFunctionShort<T> field) {
		return named().sum(field);
	}

	@Override
	public <T> NAMED_SUM_LONG_RET sum(IFunctionInteger<T> field) {
		return named().sum(field);
	}

	@Override
	public <T> NAMED_SUM_LONG_RET sum(IFunctionLong<T> field) {
		return named().sum(field);
	}

	@Override
	public <T> NAMED_BIGDECIMAL_RET sum(IFunctionBigDecimal<T> field) {
		return named().sum(field);
	}

	@Override
	public ALIAS_SUM_LONG_RET sum(ISupplierShort field) {
		return alias().sum(field);
	}

	@Override
	public ALIAS_SUM_LONG_RET sum(ISupplierInteger field) {
		return alias().sum(field);
	}

	@Override
	public ALIAS_SUM_LONG_RET sum(ISupplierLong field) {
		return alias().sum(field);
	}

	@Override
	public ALIAS_BIGDECIMAL_RET sum(ISupplierBigDecimal field) {
		return alias().sum(field);
	}

	@Override
	public <T> ALIAS_STRING_RET trim(ISupplierString getter) {
		return alias().trim(getter);
	}

	@Override
	public <T> NAMED_STRING_RET trim(StringFunction<T> getter) {
		return named().trim(getter);
	}

	@Override
	public <T> ALIAS_STRING_RET upper(ISupplierString getter) {
		return alias().upper(getter);
	}

	@Override
	public <T> NAMED_STRING_RET upper(StringFunction<T> getter) {
		return named().upper(getter);
	}
	
	*/


	@Override
	ISharedFunction_Next<MODEL, RESULT, NAMED_RET> getNamedComparableFunctionNext(Expression expression) {
		return new ResultMapper_ExpressionList_Comparable_Named<>(expression, getMappingCollector(EFieldAccessType.NAMED));
	}



	@Override
	ISharedFunction_Next<MODEL, RESULT, NAMED_RET> getNamedStringFunctionNext(Expression expression) {
		return new ResultMapper_ExpressionList_String_Named<>(expression, getMappingCollector(EFieldAccessType.NAMED));
	}



	@Override
	ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> getAliasComparableFunctionNext(Expression expression) {
		return new ResultMapper_ExpressionList_Comparable_Alias<>(expression, getMappingCollector(EFieldAccessType.ALIAS));
	}


	@Override
	ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> getAliasStringFunctionNext(Expression expression) {
		return new ResultMapper_ExpressionList_String_Alias<>(expression, getMappingCollector(EFieldAccessType.ALIAS));
	}


	@Override
	ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET> getUndecidedComparableFunctionNext(Expression expression) {
		return new ResultMapper_ExpressionList_Comparable_Undecided<>(expression, getMappingCollector(EFieldAccessType.UNDECIDED));
	}

	@Override
	ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET> getUndecidedStringFunctionNext(Expression expression) {
		return (ISharedFunction_Next)this;
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public ISharedMap_Functions_Numeric_Undecided<
	
				MODEL, RESULT,
				NAMED_RET, ALIAS_RET, UNDECIDED_RET,
				
				NO_PARAM_NAMED_SUM_LONG_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_LENGTH_RET,
				NO_PARAM_NAMED_BYTE_RET, NO_PARAM_NAMED_SHORT_RET, NO_PARAM_NAMED_INT_RET, NO_PARAM_NAMED_LONG_RET, NO_PARAM_NAMED_BIGINTEGER_RET, NO_PARAM_NAMED_FLOAT_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_BIGDECIMAL_RET, NO_PARAM_NAMED_DATE_RET,
				NO_PARAM_NAMED_SUM_LONG_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_LENGTH_RET,
				NO_PARAM_NAMED_BYTE_RET, NO_PARAM_NAMED_SHORT_RET, NO_PARAM_NAMED_INT_RET, NO_PARAM_NAMED_LONG_RET, NO_PARAM_NAMED_BIGINTEGER_RET, NO_PARAM_NAMED_FLOAT_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_BIGDECIMAL_RET, NO_PARAM_NAMED_DATE_RET,
				
				NO_PARAM_ALIAS_SUM_LONG_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_LENGTH_RET,
				NO_PARAM_ALIAS_BYTE_RET, NO_PARAM_ALIAS_SHORT_RET, NO_PARAM_ALIAS_INT_RET, NO_PARAM_ALIAS_LONG_RET, NO_PARAM_ALIAS_BIGINTEGER_RET, NO_PARAM_ALIAS_FLOAT_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_BIGDECIMAL_RET, NO_PARAM_ALIAS_DATE_RET,
				NO_PARAM_ALIAS_SUM_LONG_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_LENGTH_RET,
				NO_PARAM_ALIAS_BYTE_RET, NO_PARAM_ALIAS_SHORT_RET, NO_PARAM_ALIAS_INT_RET, NO_PARAM_ALIAS_LONG_RET, NO_PARAM_ALIAS_BIGINTEGER_RET, NO_PARAM_ALIAS_FLOAT_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_BIGDECIMAL_RET, NO_PARAM_ALIAS_DATE_RET,
				
				NO_PARAM_UNDECIDED_SUM_LONG_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_LENGTH_RET,
				NO_PARAM_UNDECIDED_BYTE_RET, NO_PARAM_UNDECIDED_SHORT_RET, NO_PARAM_UNDECIDED_INT_RET, NO_PARAM_UNDECIDED_LONG_RET, NO_PARAM_UNDECIDED_BIGINTEGER_RET, NO_PARAM_UNDECIDED_FLOAT_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_BIGDECIMAL_RET, NO_PARAM_UNDECIDED_DATE_RET,
				NO_PARAM_UNDECIDED_SUM_LONG_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_LENGTH_RET,
				NO_PARAM_UNDECIDED_BYTE_RET, NO_PARAM_UNDECIDED_SHORT_RET, NO_PARAM_UNDECIDED_INT_RET, NO_PARAM_UNDECIDED_LONG_RET, NO_PARAM_UNDECIDED_BIGINTEGER_RET, NO_PARAM_UNDECIDED_FLOAT_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_BIGDECIMAL_RET, NO_PARAM_UNDECIDED_DATE_RET
				
				> abs() {
					
					
		addNoParam(Function_Arithmetic_Abs.INSTANCE);
		
		return (ISharedMap_Functions_Numeric_Undecided)this;
	}

	
	/*
	@Override
	public ISharedNumericFunctions_Initial<MODEL, RESULT, NAMED_RET, ALIAS_RET, NAMED_SUM_LONG_RET, NAMED_COUNT_RET, NAMED_SHORT_RET, NAMED_INT_RET, NAMED_LONG_RET, NAMED_DOUBLE_RET, NAMED_BIGDECIMAL_RET, ALIAS_SUM_LONG_RET, ALIAS_COUNT_RET, ALIAS_SHORT_RET, ALIAS_INT_RET, ALIAS_LONG_RET, ALIAS_DOUBLE_RET, ALIAS_BIGDECIMAL_RET> sqrt() {
		add(Function_Arithmetic_Sqrt.INSTANCE);
		
		return this;
	}
	*/

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public ISharedMap_Functions_Numeric_Undecided<
				MODEL, RESULT,
				NAMED_RET, ALIAS_RET, UNDECIDED_RET,
				
				NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_DOUBLE_RET,
				NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_DOUBLE_RET,
				NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_DOUBLE_RET,
				NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_DOUBLE_RET,
				
				NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_DOUBLE_RET,
				NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_DOUBLE_RET,
				NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_DOUBLE_RET,
				NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_DOUBLE_RET,
				
				NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_DOUBLE_RET,
				NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_DOUBLE_RET,
				NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_DOUBLE_RET,
				NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_DOUBLE_RET
				
			> sqrt() {
		addNoParam(Function_Arithmetic_Sqrt.INSTANCE);
		
		return (ISharedMap_Functions_Numeric_Undecided)this;
	}


	@Override
	public ISharedMap_Functions_String_StringResult_Undecided<
			MODEL,
			RESULT,
			NAMED_RET,
			ALIAS_RET,
			NAMED_STRING_RET, 
			NAMED_STRING_RET, 
			ALIAS_STRING_RET,
			ALIAS_STRING_RET
			> lower() {
		addNoParam(Function_String_Lower.INSTANCE);
		
		return this;
	}


	@Override
	public ISharedMap_Functions_String_StringResult_Undecided<
			MODEL,
			RESULT, 
			NAMED_RET, 
			ALIAS_RET,
			NAMED_STRING_RET,
			NAMED_STRING_RET,
			ALIAS_STRING_RET,
			ALIAS_STRING_RET
			> upper() {
		addNoParam(Function_String_Upper.INSTANCE);
		
		return this;
	}

	@Override
	public ISharedMap_Functions_String_StringResult_Undecided<
			MODEL,
			RESULT, 
			NAMED_RET,
			ALIAS_RET,
			NAMED_STRING_RET,
			NAMED_STRING_RET,
			ALIAS_STRING_RET,
			ALIAS_STRING_RET
			> trim() {
		addNoParam(Function_String_Trim.INSTANCE);
		
		return this;
	}


	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public ISharedMap_Functions_Numeric_Undecided<
					MODEL, RESULT,
					NAMED_RET, ALIAS_RET, UNDECIDED_RET,
					
					NO_PARAM_NAMED_SUM_LONG_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_LENGTH_RET,
					NO_PARAM_NAMED_SUM_LONG_RET, NO_PARAM_NAMED_SUM_LONG_RET, NO_PARAM_NAMED_SUM_LONG_RET, NO_PARAM_NAMED_SUM_LONG_RET, NO_PARAM_NAMED_BIGINTEGER_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_BIGDECIMAL_RET, NO_PARAM_NAMED_DATE_RET,

					NO_PARAM_NAMED_SUM_LONG_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_LENGTH_RET,
					NO_PARAM_NAMED_SUM_LONG_RET, NO_PARAM_NAMED_SUM_LONG_RET, NO_PARAM_NAMED_SUM_LONG_RET, NO_PARAM_NAMED_SUM_LONG_RET, NO_PARAM_NAMED_BIGINTEGER_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_BIGDECIMAL_RET, NO_PARAM_NAMED_DATE_RET,
					
					NO_PARAM_ALIAS_SUM_LONG_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_LENGTH_RET,
					NO_PARAM_ALIAS_SUM_LONG_RET, NO_PARAM_ALIAS_SUM_LONG_RET, NO_PARAM_ALIAS_SUM_LONG_RET, NO_PARAM_ALIAS_SUM_LONG_RET, NO_PARAM_ALIAS_BIGINTEGER_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_BIGDECIMAL_RET, NO_PARAM_ALIAS_DATE_RET,
					
					NO_PARAM_ALIAS_SUM_LONG_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_LENGTH_RET,
					NO_PARAM_ALIAS_SUM_LONG_RET, NO_PARAM_ALIAS_SUM_LONG_RET, NO_PARAM_ALIAS_SUM_LONG_RET, NO_PARAM_ALIAS_SUM_LONG_RET, NO_PARAM_ALIAS_BIGINTEGER_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_BIGDECIMAL_RET, NO_PARAM_ALIAS_DATE_RET,

					NO_PARAM_UNDECIDED_SUM_LONG_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_LENGTH_RET,
					NO_PARAM_UNDECIDED_SUM_LONG_RET, NO_PARAM_UNDECIDED_SUM_LONG_RET, NO_PARAM_UNDECIDED_SUM_LONG_RET, NO_PARAM_UNDECIDED_SUM_LONG_RET, NO_PARAM_UNDECIDED_BIGINTEGER_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_BIGDECIMAL_RET, NO_PARAM_UNDECIDED_DATE_RET,
					
					NO_PARAM_UNDECIDED_SUM_LONG_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_LENGTH_RET,
					NO_PARAM_UNDECIDED_SUM_LONG_RET, NO_PARAM_UNDECIDED_SUM_LONG_RET, NO_PARAM_UNDECIDED_SUM_LONG_RET, NO_PARAM_UNDECIDED_SUM_LONG_RET, NO_PARAM_UNDECIDED_BIGINTEGER_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_BIGDECIMAL_RET, NO_PARAM_UNDECIDED_DATE_RET
					
					> sum() {

		addNoParam(Function_Aggregate.SUM);
		
		return (ISharedMap_Functions_Numeric_Undecided)this;
	}
	
	



					

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public ISharedMap_Functions_Numeric_Undecided<
	
					MODEL, RESULT,
					NAMED_RET, ALIAS_RET, UNDECIDED_RET,
					
					NO_PARAM_NAMED_SUM_LONG_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_LENGTH_RET,
					NO_PARAM_NAMED_BYTE_RET, NO_PARAM_NAMED_SHORT_RET, NO_PARAM_NAMED_INT_RET, NO_PARAM_NAMED_LONG_RET, NO_PARAM_NAMED_BIGINTEGER_RET, NO_PARAM_NAMED_FLOAT_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_BIGDECIMAL_RET, NO_PARAM_NAMED_DATE_RET, // NO_PARAM_NAMED_SQLDATE_RET, NO_PARAM_NAMED_SQLTIME_RET, NO_PARAM_NAMED_SQLTIMESTAMP_RET,
					NO_PARAM_NAMED_SUM_LONG_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_LENGTH_RET,
					NO_PARAM_NAMED_BYTE_RET, NO_PARAM_NAMED_SHORT_RET, NO_PARAM_NAMED_INT_RET, NO_PARAM_NAMED_LONG_RET, NO_PARAM_NAMED_BIGINTEGER_RET, NO_PARAM_NAMED_FLOAT_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_BIGDECIMAL_RET, NO_PARAM_NAMED_DATE_RET, //NO_PARAM_NAMED_SQLDATE_RET, NO_PARAM_NAMED_SQLTIME_RET, NO_PARAM_NAMED_SQLTIMESTAMP_RET,
					
					NO_PARAM_ALIAS_SUM_LONG_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_LENGTH_RET,
					NO_PARAM_ALIAS_BYTE_RET, NO_PARAM_ALIAS_SHORT_RET, NO_PARAM_ALIAS_INT_RET, NO_PARAM_ALIAS_LONG_RET, NO_PARAM_ALIAS_BIGINTEGER_RET, NO_PARAM_ALIAS_FLOAT_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_BIGDECIMAL_RET, NO_PARAM_ALIAS_DATE_RET, //NO_PARAM_ALIAS_SQLTIME_RET, NO_PARAM_ALIAS_SQLTIMESTAMP_RET,
					NO_PARAM_ALIAS_SUM_LONG_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_LENGTH_RET,
					NO_PARAM_ALIAS_BYTE_RET, NO_PARAM_ALIAS_SHORT_RET, NO_PARAM_ALIAS_INT_RET, NO_PARAM_ALIAS_LONG_RET, NO_PARAM_ALIAS_BIGINTEGER_RET, NO_PARAM_ALIAS_FLOAT_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_BIGDECIMAL_RET, NO_PARAM_ALIAS_DATE_RET, //, NO_PARAM_ALIAS_SQLTIME_RET, NO_PARAM_ALIAS_SQLTIMESTAMP_RET,
					
					NO_PARAM_UNDECIDED_SUM_LONG_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_LENGTH_RET,
					NO_PARAM_UNDECIDED_BYTE_RET, NO_PARAM_UNDECIDED_SHORT_RET, NO_PARAM_UNDECIDED_INT_RET, NO_PARAM_UNDECIDED_LONG_RET, NO_PARAM_UNDECIDED_BIGINTEGER_RET, NO_PARAM_UNDECIDED_FLOAT_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_BIGDECIMAL_RET, NO_PARAM_UNDECIDED_DATE_RET, //NO_PARAM_UNDECIDED_SQLTIME_RET, NO_PARAM_UNDECIDED_SQLTIMESTAMP_RET,
					NO_PARAM_UNDECIDED_SUM_LONG_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_LENGTH_RET,
					NO_PARAM_UNDECIDED_BYTE_RET, NO_PARAM_UNDECIDED_SHORT_RET, NO_PARAM_UNDECIDED_INT_RET, NO_PARAM_UNDECIDED_LONG_RET, NO_PARAM_UNDECIDED_BIGINTEGER_RET, NO_PARAM_UNDECIDED_FLOAT_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_BIGDECIMAL_RET, NO_PARAM_UNDECIDED_DATE_RET //, NO_PARAM_UNDECIDED_SQLTIME_RET, NO_PARAM_UNDECIDED_SQLTIMESTAMP_RET

					
				> min() {
		addNoParam(Function_Aggregate.MIN);
		
		return (ISharedMap_Functions_Numeric_Undecided)this;
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public ISharedMap_Functions_Numeric_Undecided<
					MODEL, RESULT,
					NAMED_RET, ALIAS_RET, UNDECIDED_RET,
					
					NO_PARAM_NAMED_SUM_LONG_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_LENGTH_RET,
					NO_PARAM_NAMED_BYTE_RET, NO_PARAM_NAMED_SHORT_RET, NO_PARAM_NAMED_INT_RET, NO_PARAM_NAMED_LONG_RET, NO_PARAM_NAMED_BIGINTEGER_RET, NO_PARAM_NAMED_FLOAT_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_BIGDECIMAL_RET, NO_PARAM_NAMED_DATE_RET,// NO_PARAM_NAMED_SQLDATE_RET, NO_PARAM_NAMED_SQLTIME_RET, NO_PARAM_NAMED_SQLTIMESTAMP_RET,
					NO_PARAM_NAMED_SUM_LONG_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_LENGTH_RET,
					NO_PARAM_NAMED_BYTE_RET, NO_PARAM_NAMED_SHORT_RET, NO_PARAM_NAMED_INT_RET, NO_PARAM_NAMED_LONG_RET, NO_PARAM_NAMED_BIGINTEGER_RET, NO_PARAM_NAMED_FLOAT_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_BIGDECIMAL_RET, NO_PARAM_NAMED_DATE_RET,// NO_PARAM_NAMED_SQLDATE_RET, NO_PARAM_NAMED_SQLTIME_RET, NO_PARAM_NAMED_SQLTIMESTAMP_RET,
					
					NO_PARAM_ALIAS_SUM_LONG_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_LENGTH_RET,
					NO_PARAM_ALIAS_BYTE_RET, NO_PARAM_ALIAS_SHORT_RET, NO_PARAM_ALIAS_INT_RET, NO_PARAM_ALIAS_LONG_RET, NO_PARAM_ALIAS_BIGINTEGER_RET, NO_PARAM_ALIAS_FLOAT_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_BIGDECIMAL_RET, NO_PARAM_ALIAS_DATE_RET,// NO_PARAM_ALIAS_SQLDATE_RET, NO_PARAM_ALIAS_SQLTIME_RET, NO_PARAM_ALIAS_SQLTIMESTAMP_RET,
					NO_PARAM_ALIAS_SUM_LONG_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_LENGTH_RET,
					NO_PARAM_ALIAS_BYTE_RET, NO_PARAM_ALIAS_SHORT_RET, NO_PARAM_ALIAS_INT_RET, NO_PARAM_ALIAS_LONG_RET, NO_PARAM_ALIAS_BIGINTEGER_RET, NO_PARAM_ALIAS_FLOAT_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_BIGDECIMAL_RET, NO_PARAM_ALIAS_DATE_RET,//, NO_PARAM_ALIAS_SQLDATE_RET, NO_PARAM_ALIAS_SQLTIME_RET, NO_PARAM_ALIAS_SQLTIMESTAMP_RET
					
					
					NO_PARAM_UNDECIDED_SUM_LONG_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_LENGTH_RET,
					NO_PARAM_UNDECIDED_BYTE_RET, NO_PARAM_UNDECIDED_SHORT_RET, NO_PARAM_UNDECIDED_INT_RET, NO_PARAM_UNDECIDED_LONG_RET, NO_PARAM_UNDECIDED_BIGINTEGER_RET, NO_PARAM_UNDECIDED_FLOAT_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_BIGDECIMAL_RET, NO_PARAM_UNDECIDED_DATE_RET,// NO_PARAM_UNDECIDED_SQLDATE_RET, NO_PARAM_UNDECIDED_SQLTIME_RET, NO_PARAM_UNDECIDED_SQLTIMESTAMP_RET,
					NO_PARAM_UNDECIDED_SUM_LONG_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_LENGTH_RET,
					NO_PARAM_UNDECIDED_BYTE_RET, NO_PARAM_UNDECIDED_SHORT_RET, NO_PARAM_UNDECIDED_INT_RET, NO_PARAM_UNDECIDED_LONG_RET, NO_PARAM_UNDECIDED_BIGINTEGER_RET, NO_PARAM_UNDECIDED_FLOAT_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_BIGDECIMAL_RET, NO_PARAM_UNDECIDED_DATE_RET//, NO_PARAM_UNDECIDED_SQLDATE_RET, NO_PARAM_UNDECIDED_SQLTIME_RET, NO_PARAM_ALIAS_SQLTIMESTAMP_RET					
				> max() {
		addNoParam(Function_Aggregate.MAX);
		
		return (ISharedMap_Functions_Numeric_Undecided)this;
	}


	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public ISharedMap_Functions_Numeric_Undecided<
					MODEL, RESULT,
					NAMED_RET, ALIAS_RET, UNDECIDED_RET,
					
					NO_PARAM_NAMED_SUM_LONG_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_LENGTH_RET,
					NO_PARAM_NAMED_BYTE_RET, NO_PARAM_NAMED_SHORT_RET, NO_PARAM_NAMED_INT_RET, NO_PARAM_NAMED_LONG_RET, NO_PARAM_NAMED_BIGINTEGER_RET, NO_PARAM_NAMED_FLOAT_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_BIGDECIMAL_RET, NO_PARAM_NAMED_DATE_RET,
					NO_PARAM_NAMED_SUM_LONG_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_LENGTH_RET,
					NO_PARAM_NAMED_BYTE_RET, NO_PARAM_NAMED_SHORT_RET, NO_PARAM_NAMED_INT_RET, NO_PARAM_NAMED_LONG_RET, NO_PARAM_NAMED_BIGINTEGER_RET, NO_PARAM_NAMED_FLOAT_RET, NO_PARAM_NAMED_DOUBLE_RET, NO_PARAM_NAMED_BIGDECIMAL_RET, NO_PARAM_NAMED_DATE_RET,
					
					NO_PARAM_ALIAS_SUM_LONG_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_LENGTH_RET,
					NO_PARAM_ALIAS_BYTE_RET, NO_PARAM_ALIAS_SHORT_RET, NO_PARAM_ALIAS_INT_RET, NO_PARAM_ALIAS_LONG_RET, NO_PARAM_ALIAS_BIGINTEGER_RET, NO_PARAM_ALIAS_FLOAT_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_BIGDECIMAL_RET, NO_PARAM_ALIAS_DATE_RET,
					NO_PARAM_ALIAS_SUM_LONG_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_LENGTH_RET,
					NO_PARAM_ALIAS_BYTE_RET, NO_PARAM_ALIAS_SHORT_RET, NO_PARAM_ALIAS_INT_RET, NO_PARAM_ALIAS_LONG_RET, NO_PARAM_ALIAS_BIGINTEGER_RET, NO_PARAM_ALIAS_FLOAT_RET, NO_PARAM_ALIAS_DOUBLE_RET, NO_PARAM_ALIAS_BIGDECIMAL_RET, NO_PARAM_ALIAS_DATE_RET,
					
					NO_PARAM_UNDECIDED_SUM_LONG_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_LENGTH_RET,
					NO_PARAM_UNDECIDED_BYTE_RET, NO_PARAM_UNDECIDED_SHORT_RET, NO_PARAM_UNDECIDED_INT_RET, NO_PARAM_UNDECIDED_LONG_RET, NO_PARAM_UNDECIDED_BIGINTEGER_RET, NO_PARAM_UNDECIDED_FLOAT_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_BIGDECIMAL_RET, NO_PARAM_UNDECIDED_DATE_RET,
					NO_PARAM_UNDECIDED_SUM_LONG_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_LENGTH_RET,
					NO_PARAM_UNDECIDED_BYTE_RET, NO_PARAM_UNDECIDED_SHORT_RET, NO_PARAM_UNDECIDED_INT_RET, NO_PARAM_UNDECIDED_LONG_RET, NO_PARAM_UNDECIDED_BIGINTEGER_RET, NO_PARAM_UNDECIDED_FLOAT_RET, NO_PARAM_UNDECIDED_DOUBLE_RET, NO_PARAM_UNDECIDED_BIGDECIMAL_RET, NO_PARAM_UNDECIDED_DATE_RET
					
					
					> avg() {
		addNoParam(Function_Aggregate.AVG);
		
		return (ISharedMap_Functions_Numeric_Undecided)this;
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public ISharedMap_Functions_Numeric_Undecided<
				MODEL, RESULT,
				NAMED_RET, ALIAS_RET, UNDECIDED_RET,
				
				NO_PARAM_NAMED_SUM_LONG_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_LENGTH_RET,
				NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_COUNT_RET,
				NO_PARAM_NAMED_SUM_LONG_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_LENGTH_RET,
				NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_COUNT_RET, NO_PARAM_NAMED_COUNT_RET,
				
				NO_PARAM_ALIAS_SUM_LONG_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_LENGTH_RET,
				NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_COUNT_RET,
				NO_PARAM_ALIAS_SUM_LONG_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_LENGTH_RET,
				NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_COUNT_RET, NO_PARAM_ALIAS_COUNT_RET,
				
				NO_PARAM_UNDECIDED_SUM_LONG_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_LENGTH_RET,
				NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_COUNT_RET,
				NO_PARAM_UNDECIDED_SUM_LONG_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_LENGTH_RET,
				NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_COUNT_RET, NO_PARAM_UNDECIDED_COUNT_RET
				
				> count() {
					
		addNoParam(Function_Aggregate.COUNT);

		return (ISharedMap_Functions_Numeric_Undecided)this;
	}



	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public ISharedMap_Functions_String_StringResult_Undecided<
				MODEL,
				RESULT,
				NAMED_RET,
				ALIAS_RET,
				
				NAMED_LENGTH_RET,
				NAMED_LENGTH_RET,
				ALIAS_LENGTH_RET,
				ALIAS_LENGTH_RET
				
				> length() {
					
		// TODO: See if can avoid this cast? Both length() function requires StringResult and so do string functions 
		return (ISharedMap_Functions_String_StringResult_Undecided)this;
	}
}
