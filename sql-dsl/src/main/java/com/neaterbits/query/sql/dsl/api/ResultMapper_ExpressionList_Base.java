package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;

abstract class ResultMapper_ExpressionList_Base<
			MODEL,
			RESULT,
			R, //extends Comparable<R>, 
			
			OPERAND_RET extends ISharedFunction_After<MODEL, RESULT>,
			
			NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
			ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>,
			UNDECIDED_RET extends ISharedFunction_After<MODEL, RESULT>,
			
			NAMED_NUMERIC_NEXT extends ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,
			NAMED_STRING_NEXT extends ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,
			ALIAS_NUMERIC_NEXT extends ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,
			ALIAS_STRING_NEXT extends ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,
			COMMON_NUMERIC_NEXT extends ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,
			COMMON_STRING_NEXT extends ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,
			
			
			NAMED_SUM_LONG_RET  extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_COUNT_RET     extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_LENGTH_RET    extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,

			NAMED_BYTE_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_SHORT_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_INTEGER_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_LONG_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_BIGINTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_FLOAT_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_DOUBLE_RET   	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_BIGDECIMAL_RET  extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_STRING_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_DATE_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_CALENDAR_RET  extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_SQLDATE_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_SQLTIME_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_SQLTIMESTAMP_RET  extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			
			ALIAS_SUM_LONG_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_COUNT_RET     extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_LENGTH_RET    extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			
			ALIAS_BYTE_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_SHORT_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_INTEGER_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_LONG_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_BIGINTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_FLOAT_RET 	 extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_DOUBLE_RET   	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_BIGDECIMAL_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_STRING_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_DATE_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_CALENDAR_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_SQLDATE_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_SQLTIME_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_SQLTIMESTAMP_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			
			UNDECIDED_SUM_LONG_RET  extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
			UNDECIDED_COUNT_RET     extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
			UNDECIDED_LENGTH_RET    extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
			
			UNDECIDED_BYTE_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
			UNDECIDED_SHORT_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
			UNDECIDED_INTEGER_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
			UNDECIDED_LONG_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
			UNDECIDED_BIGINTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
			UNDECIDED_FLOAT_RET 	 extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
			UNDECIDED_DOUBLE_RET   	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
			UNDECIDED_BIGDECIMAL_RET  extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
			UNDECIDED_STRING_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
			UNDECIDED_DATE_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
			UNDECIDED_CALENDAR_RET  extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
			UNDECIDED_SQLDATE_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
			UNDECIDED_SQLTIME_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
			UNDECIDED_SQLTIMESTAMP_RET  extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>
			

			>

		extends Collector_ExpressionList<
				MODEL, RESULT, R,
				
				OPERAND_RET,
				
				NAMED_RET, ALIAS_RET, UNDECIDED_RET,
				
				NAMED_NUMERIC_NEXT, NAMED_STRING_NEXT,
				ALIAS_NUMERIC_NEXT, ALIAS_STRING_NEXT,
				COMMON_NUMERIC_NEXT, COMMON_STRING_NEXT,
				
				NAMED_SUM_LONG_RET,
				NAMED_COUNT_RET,
				NAMED_LENGTH_RET,
				
				NAMED_BYTE_RET,
				NAMED_SHORT_RET,
				NAMED_INTEGER_RET,
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
				ALIAS_INTEGER_RET,
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
				UNDECIDED_INTEGER_RET,
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

		implements ISharedMap_To<MODEL, RESULT, R, OPERAND_RET> {

	//private final IMappingCollector<MODEL, RESULT> impl;
			
	abstract IMappingCollector<MODEL, RESULT> getMappingCollector(EFieldAccessType fieldAccessType);
	
	ResultMapper_ExpressionList_Base(/* IMappingCollector<MODEL, RESULT> impl */) {
		/*
		if (impl == null) {
			throw new IllegalArgumentException("impl == null");
		}

		this.impl = impl;
		*/
	}

	ResultMapper_ExpressionList_Base(Expression expression /* , IMappingCollector<MODEL, RESULT> impl */, EFieldAccessType fieldAccessType) {
		super(expression, fieldAccessType);
		
		/*
		if (impl == null) {
			throw new IllegalArgumentException("impl == null");
		}

		this.impl = impl;
		*/
	}

	@Override
	@SuppressWarnings("unchecked")
	public final OPERAND_RET to(BiConsumer<RESULT, R> setter) {
		
		final Expression toForward = collectExpressionListOrOne();

		final EFieldAccessType fieldAccessType = getFieldAccessType();
		
		if (fieldAccessType == null) {
			throw new IllegalStateException("Was unable to determine field access type (named or aliased) during mapping");
		}
		
		final IMappingCollector<MODEL, RESULT> impl = getMappingCollector(fieldAccessType);
		

		impl.getMappingCollector().add(this, toForward, setter);
		
		return (OPERAND_RET)impl;
		
		/*
		impl.getMappingCollector().add(this, toForward, setter);

		return (OPERAND_RET)impl;
		*/
	}

	private class ResultMapper_Named_Functions extends
	
		NamedFunctions<
			ISharedMap_Functions_Numeric_Named<
				MODEL,
				RESULT,
				
				NAMED_RET,
				
				NAMED_SUM_LONG_RET,
				NAMED_COUNT_RET,
				NAMED_LENGTH_RET,
				
				NAMED_BYTE_RET,
				NAMED_SHORT_RET,
				NAMED_INTEGER_RET,
				NAMED_LONG_RET,
				NAMED_BIGINTEGER_RET,
				NAMED_FLOAT_RET,
				NAMED_DOUBLE_RET,
				NAMED_BIGDECIMAL_RET,
				NAMED_DATE_RET,
				
				NAMED_SUM_LONG_RET,
				NAMED_COUNT_RET,
				NAMED_LENGTH_RET,
				
				NAMED_BYTE_RET,
				NAMED_SHORT_RET,
				NAMED_INTEGER_RET,
				NAMED_LONG_RET,
				NAMED_BIGINTEGER_RET,
				NAMED_FLOAT_RET,
				NAMED_DOUBLE_RET,
				NAMED_BIGDECIMAL_RET,
				NAMED_DATE_RET
			
			>,
			ISharedMap_Functions_Numeric_Named<
				MODEL,
				RESULT,
				
				NAMED_RET,

				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,

/*				
				NAMED_SUM_LONG_RET,
				NAMED_COUNT_RET,
				
				NAMED_SHORT_RET,
				NAMED_INTEGER_RET,
				NAMED_LONG_RET,
				NAMED_DOUBLE_RET,
				NAMED_BIGDECIMAL_RET,
	*/
				
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET
				
			>,
		    ISharedMap_Functions_String_StringResult_Named<
			    	MODEL,
			    	RESULT,
			    	NAMED_RET,
			    	NAMED_STRING_RET,
			    	NAMED_STRING_RET
		    >,
		    ISharedMap_Functions_String_StringResult_Named<
				MODEL,
				RESULT,
				NAMED_RET,
				
				NAMED_LENGTH_RET,
				NAMED_LENGTH_RET
				>
		>

	implements ISharedMap_Functions_Numeric_Named<
				MODEL,
				RESULT,
				
				NAMED_RET,
				
				NAMED_SUM_LONG_RET,
				NAMED_COUNT_RET,
				NAMED_LENGTH_RET,
			
				NAMED_BYTE_RET,
				NAMED_SHORT_RET,
				NAMED_INTEGER_RET,
				NAMED_LONG_RET,
				NAMED_BIGINTEGER_RET,
				NAMED_FLOAT_RET,
				NAMED_DOUBLE_RET,
				NAMED_BIGDECIMAL_RET,
				NAMED_DATE_RET,
				
				NAMED_SUM_LONG_RET,
				NAMED_COUNT_RET,
				NAMED_LENGTH_RET,
				
				NAMED_BYTE_RET,
				NAMED_SHORT_RET,
				NAMED_INTEGER_RET,
				NAMED_LONG_RET,
				NAMED_BIGINTEGER_RET,
				NAMED_FLOAT_RET,
				NAMED_DOUBLE_RET,
				NAMED_BIGDECIMAL_RET,
				NAMED_DATE_RET
				
				>,
		ISharedMap_Functions_String_Named<
			MODEL,
			RESULT,

			NAMED_RET,
			
			NAMED_LENGTH_RET,
			NAMED_STRING_RET,
			NAMED_LENGTH_RET,
			NAMED_STRING_RET
			>,
		ISharedMap_Functions_String_StringResult_Named<
			MODEL,
			RESULT,
			NAMED_RET,
			
			NAMED_STRING_RET,
			NAMED_STRING_RET
			>
		{

		ResultMapper_Named_Functions(ISharedCollector_Functions_Callback<MODEL, RESULT, NAMED_RET> func) {
			super(func);
		}
	}
	
	@Override
	final NamedFunctions createNamedFunctions(
			ISharedCollector_Functions_Callback<MODEL, RESULT, NAMED_RET> func) {
		return new ResultMapper_Named_Functions(func);
	}

	private class ResultMapper_Alias_Functions extends
	
		AliasFunctions<  
			ISharedMap_Functions_Numeric_Alias<
				MODEL,
				RESULT,
				
				ALIAS_RET,
				
				ALIAS_SUM_LONG_RET,
				ALIAS_COUNT_RET,
				ALIAS_LENGTH_RET,
				
				ALIAS_BYTE_RET,
				ALIAS_SHORT_RET,
				ALIAS_INTEGER_RET,
				ALIAS_LONG_RET,
				ALIAS_BIGINTEGER_RET,
				ALIAS_FLOAT_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_BIGDECIMAL_RET,
				ALIAS_DATE_RET,
				ALIAS_CALENDAR_RET,
				ALIAS_SQLDATE_RET,
				ALIAS_SQLTIME_RET,
				ALIAS_SQLTIMESTAMP_RET,
				
				ALIAS_SUM_LONG_RET,
				ALIAS_COUNT_RET,
				ALIAS_LENGTH_RET,
				
				ALIAS_BYTE_RET,
				ALIAS_SHORT_RET,
				ALIAS_INTEGER_RET,
				ALIAS_LONG_RET,
				ALIAS_BIGINTEGER_RET,
				ALIAS_FLOAT_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_BIGDECIMAL_RET,
				ALIAS_DATE_RET,
				ALIAS_CALENDAR_RET,
				ALIAS_SQLDATE_RET,
				ALIAS_SQLTIME_RET,
				ALIAS_SQLTIMESTAMP_RET
			
			>,
			ISharedMap_Functions_Numeric_Alias<
				MODEL,
				RESULT,
				
				ALIAS_RET,
	
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
	
				
				
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET
				
			>,
			ISharedMap_Functions_String_StringResult_Alias<
				MODEL,
				RESULT,
	
				ALIAS_RET,
				
				ALIAS_STRING_RET,
				ALIAS_STRING_RET
			>,
			ISharedMap_Functions_String_StringResult_Alias<
				MODEL,
				RESULT,
				ALIAS_RET,
				
				ALIAS_LENGTH_RET,
				ALIAS_LENGTH_RET
			>
		>
	
	implements ISharedMap_Functions_Numeric_Alias<
				MODEL,
				RESULT,
				
				ALIAS_RET,
				
				ALIAS_SUM_LONG_RET,
				ALIAS_COUNT_RET,
				ALIAS_LENGTH_RET,
				
				ALIAS_BYTE_RET,
				ALIAS_SHORT_RET,
				ALIAS_INTEGER_RET,
				ALIAS_LONG_RET,
				ALIAS_BIGINTEGER_RET,
				ALIAS_FLOAT_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_BIGDECIMAL_RET,
				ALIAS_DATE_RET,
				ALIAS_CALENDAR_RET,
				ALIAS_SQLDATE_RET,
				ALIAS_SQLTIME_RET,
				ALIAS_SQLTIMESTAMP_RET,
				
				ALIAS_SUM_LONG_RET,
				ALIAS_COUNT_RET,
				ALIAS_LENGTH_RET,
				
				ALIAS_BYTE_RET,
				ALIAS_SHORT_RET,
				ALIAS_INTEGER_RET,
				ALIAS_LONG_RET,
				ALIAS_BIGINTEGER_RET,
				ALIAS_FLOAT_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_BIGDECIMAL_RET,
				ALIAS_DATE_RET,
				ALIAS_CALENDAR_RET,
				ALIAS_SQLDATE_RET,
				ALIAS_SQLTIME_RET,
				ALIAS_SQLTIMESTAMP_RET
				
				>,
				
		ISharedMap_Functions_String_Alias<
				MODEL,
				RESULT,

				ALIAS_RET,
				
				ALIAS_LENGTH_RET,
				ALIAS_STRING_RET,
				ALIAS_LENGTH_RET,
				ALIAS_STRING_RET
				>,				
		ISharedMap_Functions_String_StringResult_Alias<
				MODEL,
				RESULT,
				ALIAS_RET,
				
				ALIAS_STRING_RET,
				ALIAS_STRING_RET
				>
		{
	
		ResultMapper_Alias_Functions(ISharedCollector_Functions_Callback<MODEL, RESULT, ALIAS_RET> func) {
			super(func);
		}
	}
		
	@Override
	final AliasFunctions createAliasFunctions(
			ISharedCollector_Functions_Callback<MODEL, RESULT, ALIAS_RET> func) {
		
		return new ResultMapper_Alias_Functions(func);
	}

	private class ResultMapper_Undecided_Functions extends
	
		UndecidedFunctions<
		
			// arithmetic same type
			ISharedMap_Functions_Numeric_Undecided<
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
				NAMED_INTEGER_RET,
				NAMED_LONG_RET,
				NAMED_BIGINTEGER_RET,
				NAMED_FLOAT_RET,
				NAMED_DOUBLE_RET,
				NAMED_BIGDECIMAL_RET,
				NAMED_DATE_RET,
				
				NAMED_SUM_LONG_RET,
				NAMED_COUNT_RET,
				NAMED_LENGTH_RET,
				
				NAMED_BYTE_RET,
				NAMED_SHORT_RET,
				NAMED_INTEGER_RET,
				NAMED_LONG_RET,
				NAMED_BIGINTEGER_RET,
				NAMED_FLOAT_RET,
				NAMED_DOUBLE_RET,
				NAMED_BIGDECIMAL_RET,
				NAMED_DATE_RET,
				
				ALIAS_SUM_LONG_RET,
				ALIAS_COUNT_RET,
				ALIAS_LENGTH_RET,
				
				ALIAS_BYTE_RET,
				ALIAS_SHORT_RET,
				ALIAS_INTEGER_RET,
				ALIAS_LONG_RET,
				ALIAS_BIGINTEGER_RET,
				ALIAS_FLOAT_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_BIGDECIMAL_RET,
				ALIAS_DATE_RET,
				
				ALIAS_SUM_LONG_RET,
				ALIAS_COUNT_RET,
				ALIAS_LENGTH_RET,
				
				ALIAS_BYTE_RET,
				ALIAS_SHORT_RET,
				ALIAS_INTEGER_RET,
				ALIAS_LONG_RET,
				ALIAS_BIGINTEGER_RET,
				ALIAS_FLOAT_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_BIGDECIMAL_RET,
				ALIAS_DATE_RET,
			
				UNDECIDED_SUM_LONG_RET,
				UNDECIDED_COUNT_RET,
				UNDECIDED_LENGTH_RET,
				
				UNDECIDED_BYTE_RET,
				UNDECIDED_SHORT_RET,
				UNDECIDED_INTEGER_RET,
				UNDECIDED_LONG_RET,
				UNDECIDED_BIGINTEGER_RET,
				UNDECIDED_FLOAT_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_BIGDECIMAL_RET,
				UNDECIDED_DATE_RET,
				
				UNDECIDED_SUM_LONG_RET,
				UNDECIDED_COUNT_RET,
				UNDECIDED_LENGTH_RET,
				
				UNDECIDED_BYTE_RET,
				UNDECIDED_SHORT_RET,
				UNDECIDED_INTEGER_RET,
				UNDECIDED_LONG_RET,
				UNDECIDED_BIGINTEGER_RET,
				UNDECIDED_FLOAT_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_BIGDECIMAL_RET,
				UNDECIDED_DATE_RET
			>,
				
			// sqrt()
			ISharedMap_Functions_Numeric_Undecided<
				MODEL,
				RESULT,
				
				NAMED_RET,
				ALIAS_RET,
				UNDECIDED_RET,
				
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				
				
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,

				
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				
				
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_DOUBLE_RET,
				
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_DOUBLE_RET,
				
				
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_DOUBLE_RET,
				
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_DOUBLE_RET
			>,
			// aggregate same type
			ISharedMap_Functions_Numeric_Undecided<
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
				NAMED_INTEGER_RET,
				NAMED_LONG_RET,
				NAMED_BIGINTEGER_RET,
				NAMED_FLOAT_RET,
				NAMED_DOUBLE_RET,
				NAMED_BIGDECIMAL_RET,
				NAMED_DATE_RET,
				
				NAMED_SUM_LONG_RET,
				NAMED_COUNT_RET,
				NAMED_LENGTH_RET,
				
				NAMED_BYTE_RET,
				NAMED_SHORT_RET,
				NAMED_INTEGER_RET,
				NAMED_LONG_RET,
				NAMED_BIGINTEGER_RET,
				NAMED_FLOAT_RET,
				NAMED_DOUBLE_RET,
				NAMED_BIGDECIMAL_RET,
				NAMED_DATE_RET,
				
				ALIAS_SUM_LONG_RET,
				ALIAS_COUNT_RET,
				ALIAS_LENGTH_RET,
				
				ALIAS_BYTE_RET,
				ALIAS_SHORT_RET,
				ALIAS_INTEGER_RET,
				ALIAS_LONG_RET,
				ALIAS_BIGINTEGER_RET,
				ALIAS_FLOAT_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_BIGDECIMAL_RET,
				ALIAS_DATE_RET,
				
				ALIAS_SUM_LONG_RET,
				ALIAS_COUNT_RET,
				ALIAS_LENGTH_RET,
				
				ALIAS_BYTE_RET,
				ALIAS_SHORT_RET,
				ALIAS_INTEGER_RET,
				ALIAS_LONG_RET,
				ALIAS_BIGINTEGER_RET,
				ALIAS_FLOAT_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_BIGDECIMAL_RET,
				ALIAS_DATE_RET,
			
				UNDECIDED_SUM_LONG_RET,
				UNDECIDED_COUNT_RET,
				UNDECIDED_LENGTH_RET,
				
				UNDECIDED_BYTE_RET,
				UNDECIDED_SHORT_RET,
				UNDECIDED_INTEGER_RET,
				UNDECIDED_LONG_RET,
				UNDECIDED_BIGINTEGER_RET,
				UNDECIDED_FLOAT_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_BIGDECIMAL_RET,
				UNDECIDED_DATE_RET,
				
				UNDECIDED_SUM_LONG_RET,
				UNDECIDED_COUNT_RET,
				UNDECIDED_LENGTH_RET,
				
				UNDECIDED_BYTE_RET,
				UNDECIDED_SHORT_RET,
				UNDECIDED_INTEGER_RET,
				UNDECIDED_LONG_RET,
				UNDECIDED_BIGINTEGER_RET,
				UNDECIDED_FLOAT_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_BIGDECIMAL_RET,
				UNDECIDED_DATE_RET
			>,
			// aggregate sum()
			ISharedMap_Functions_Numeric_Undecided<
				MODEL,
				RESULT,
				
				NAMED_RET,
				ALIAS_RET,
				UNDECIDED_RET,
				
				NAMED_SUM_LONG_RET,
				NAMED_COUNT_RET,
				NAMED_LENGTH_RET,
				
				NAMED_SUM_LONG_RET,
				NAMED_SUM_LONG_RET,
				NAMED_SUM_LONG_RET,
				NAMED_SUM_LONG_RET,
				NAMED_BIGINTEGER_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_BIGDECIMAL_RET,
				NAMED_DATE_RET,
				
				
				NAMED_SUM_LONG_RET,
				NAMED_COUNT_RET,
				NAMED_LENGTH_RET,
				
				NAMED_SUM_LONG_RET,
				NAMED_SUM_LONG_RET,
				NAMED_SUM_LONG_RET,
				NAMED_SUM_LONG_RET,
				NAMED_BIGINTEGER_RET,
				NAMED_DOUBLE_RET,
				NAMED_DOUBLE_RET,
				NAMED_BIGDECIMAL_RET,
				NAMED_DATE_RET,
	
				
				ALIAS_SUM_LONG_RET,
				ALIAS_COUNT_RET,
				ALIAS_LENGTH_RET,
				
				ALIAS_SUM_LONG_RET,
				ALIAS_SUM_LONG_RET,
				ALIAS_SUM_LONG_RET,
				ALIAS_SUM_LONG_RET,
				ALIAS_BIGINTEGER_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_BIGDECIMAL_RET,
				ALIAS_DATE_RET,
				
				
				ALIAS_SUM_LONG_RET,
				ALIAS_COUNT_RET,
				ALIAS_LENGTH_RET,
				
				ALIAS_SUM_LONG_RET,
				ALIAS_SUM_LONG_RET,
				ALIAS_SUM_LONG_RET,
				ALIAS_SUM_LONG_RET,
				ALIAS_BIGINTEGER_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_BIGDECIMAL_RET,
				ALIAS_DATE_RET,
				
				UNDECIDED_SUM_LONG_RET,
				UNDECIDED_COUNT_RET,
				UNDECIDED_LENGTH_RET,
				
				UNDECIDED_SUM_LONG_RET,
				UNDECIDED_SUM_LONG_RET,
				UNDECIDED_SUM_LONG_RET,
				UNDECIDED_SUM_LONG_RET,
				UNDECIDED_BIGINTEGER_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_BIGDECIMAL_RET,
				UNDECIDED_DATE_RET,
				
				
				UNDECIDED_SUM_LONG_RET,
				UNDECIDED_COUNT_RET,
				UNDECIDED_LENGTH_RET,
				
				UNDECIDED_SUM_LONG_RET,
				UNDECIDED_SUM_LONG_RET,
				UNDECIDED_SUM_LONG_RET,
				UNDECIDED_SUM_LONG_RET,
				UNDECIDED_BIGINTEGER_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_BIGDECIMAL_RET,
				UNDECIDED_DATE_RET
			>,
			// aggregate count()
			ISharedMap_Functions_Numeric_Undecided<
				MODEL,
				RESULT,
				
				NAMED_RET,
				ALIAS_RET,
				UNDECIDED_RET,
				
				NAMED_SUM_LONG_RET,
				NAMED_COUNT_RET,
				NAMED_LENGTH_RET,
				
				NAMED_COUNT_RET,
				NAMED_COUNT_RET,
				NAMED_COUNT_RET,
				NAMED_COUNT_RET,
				NAMED_COUNT_RET,
				NAMED_COUNT_RET,
				NAMED_COUNT_RET,
				NAMED_COUNT_RET,
				NAMED_COUNT_RET,
				
				
				NAMED_SUM_LONG_RET,
				NAMED_COUNT_RET,
				NAMED_LENGTH_RET,
				
				NAMED_COUNT_RET,
				NAMED_COUNT_RET,
				NAMED_COUNT_RET,
				NAMED_COUNT_RET,
				NAMED_COUNT_RET,
				NAMED_COUNT_RET,
				NAMED_COUNT_RET,
				NAMED_COUNT_RET,
				NAMED_COUNT_RET,
		
				
				ALIAS_SUM_LONG_RET,
				ALIAS_COUNT_RET,
				ALIAS_LENGTH_RET,
				
				ALIAS_COUNT_RET,
				ALIAS_COUNT_RET,
				ALIAS_COUNT_RET,
				ALIAS_COUNT_RET,
				ALIAS_COUNT_RET,
				ALIAS_COUNT_RET,
				ALIAS_COUNT_RET,
				ALIAS_COUNT_RET,
				ALIAS_COUNT_RET,
				
				
				ALIAS_SUM_LONG_RET,
				ALIAS_COUNT_RET,
				ALIAS_LENGTH_RET,
				
				ALIAS_COUNT_RET,
				ALIAS_COUNT_RET,
				ALIAS_COUNT_RET,
				ALIAS_COUNT_RET,
				ALIAS_COUNT_RET,
				ALIAS_COUNT_RET,
				ALIAS_COUNT_RET,
				ALIAS_COUNT_RET,
				ALIAS_COUNT_RET,
				
				UNDECIDED_SUM_LONG_RET,
				UNDECIDED_COUNT_RET,
				UNDECIDED_LENGTH_RET,
				
				UNDECIDED_COUNT_RET,
				UNDECIDED_COUNT_RET,
				UNDECIDED_COUNT_RET,
				UNDECIDED_COUNT_RET,
				UNDECIDED_COUNT_RET,
				UNDECIDED_COUNT_RET,
				UNDECIDED_COUNT_RET,
				UNDECIDED_COUNT_RET,
				UNDECIDED_COUNT_RET,
				
				
				UNDECIDED_SUM_LONG_RET,
				UNDECIDED_COUNT_RET,
				UNDECIDED_LENGTH_RET,
				
				UNDECIDED_COUNT_RET,
				UNDECIDED_COUNT_RET,
				UNDECIDED_COUNT_RET,
				UNDECIDED_COUNT_RET,
				UNDECIDED_COUNT_RET,
				UNDECIDED_COUNT_RET,
				UNDECIDED_COUNT_RET,
				UNDECIDED_COUNT_RET,
				UNDECIDED_COUNT_RET
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
			>,
			ISharedMap_Functions_String_StringResult_Undecided<
				MODEL,
				RESULT,
				
				NAMED_RET,
				ALIAS_RET,
				
				NAMED_LENGTH_RET,
				NAMED_LENGTH_RET,

				ALIAS_LENGTH_RET,
				ALIAS_LENGTH_RET
				
				/*
				
				NAMED_LENGTH_RET,
				NAMED_LENGTH_RET,
				
				ALIAS_LENGTH_RET,
				ALIAS_LENGTH_RET
				*/
			>
		>
	
	implements ISharedMap_Functions_Numeric_Undecided<
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
				NAMED_INTEGER_RET,
				NAMED_LONG_RET,
				NAMED_BIGINTEGER_RET,
				NAMED_FLOAT_RET,
				NAMED_DOUBLE_RET,
				NAMED_BIGDECIMAL_RET,
				NAMED_DATE_RET,
				
				NAMED_SUM_LONG_RET,
				NAMED_COUNT_RET,
				NAMED_LENGTH_RET,
				
				NAMED_BYTE_RET,
				NAMED_SHORT_RET,
				NAMED_INTEGER_RET,
				NAMED_LONG_RET,
				NAMED_BIGINTEGER_RET,
				NAMED_FLOAT_RET,
				NAMED_DOUBLE_RET,
				NAMED_BIGDECIMAL_RET,
				NAMED_DATE_RET,
				
				
				ALIAS_SUM_LONG_RET,
				ALIAS_COUNT_RET,
				ALIAS_LENGTH_RET,
				
				ALIAS_BYTE_RET,
				ALIAS_SHORT_RET,
				ALIAS_INTEGER_RET,
				ALIAS_LONG_RET,
				ALIAS_BIGINTEGER_RET,
				ALIAS_FLOAT_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_BIGDECIMAL_RET,
				ALIAS_DATE_RET,
				
				ALIAS_SUM_LONG_RET,
				ALIAS_COUNT_RET,
				ALIAS_LENGTH_RET,
				
				ALIAS_BYTE_RET,
				ALIAS_SHORT_RET,
				ALIAS_INTEGER_RET,
				ALIAS_LONG_RET,
				ALIAS_BIGINTEGER_RET,
				ALIAS_FLOAT_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_BIGDECIMAL_RET,
				ALIAS_DATE_RET,
				
				UNDECIDED_SUM_LONG_RET,
				UNDECIDED_COUNT_RET,
				UNDECIDED_LENGTH_RET,
				
				UNDECIDED_BYTE_RET,
				UNDECIDED_SHORT_RET,
				UNDECIDED_INTEGER_RET,
				UNDECIDED_LONG_RET,
				UNDECIDED_BIGINTEGER_RET,
				UNDECIDED_FLOAT_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_BIGDECIMAL_RET,
				UNDECIDED_DATE_RET,
				
				UNDECIDED_SUM_LONG_RET,
				UNDECIDED_COUNT_RET,
				UNDECIDED_LENGTH_RET,
				
				UNDECIDED_BYTE_RET,
				UNDECIDED_SHORT_RET,
				UNDECIDED_INTEGER_RET,
				UNDECIDED_LONG_RET,
				UNDECIDED_BIGINTEGER_RET,
				UNDECIDED_FLOAT_RET,
				UNDECIDED_DOUBLE_RET,
				UNDECIDED_BIGDECIMAL_RET,
				UNDECIDED_DATE_RET
				>,
				
		ISharedMap_Functions_String_Undecided<
				MODEL,
				RESULT,
	
				NAMED_RET,
				ALIAS_RET,
				
				NAMED_LENGTH_RET,
				NAMED_STRING_RET,
				ALIAS_LENGTH_RET,
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
		{
	
		ResultMapper_Undecided_Functions(ISharedCollector_Functions_Callback<MODEL, RESULT, UNDECIDED_RET> func) {
			super(func);
		}

		
		
	}

	@Override
	final UndecidedFunctions createUndecidedFunctions(ISharedCollector_Functions_Callback<MODEL, RESULT, UNDECIDED_RET> func) {
		return new ResultMapper_Undecided_Functions(func);
	}
}
