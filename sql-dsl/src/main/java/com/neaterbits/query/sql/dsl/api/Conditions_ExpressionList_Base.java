package com.neaterbits.query.sql.dsl.api;

abstract class Conditions_ExpressionList_Base<
		MODEL,
		RESULT,
		R, //extends Comparable<R>, 
		
		//OPERAND_RET extends ISharedFunction_After<MODEL, RESULT>,
		OPERAND_RET extends ISharedLogical_Base<MODEL, RESULT>,
		
		NAMED_RET extends ISharedLogical_Base<MODEL, RESULT>,
		ALIAS_RET extends ISharedLogical_Base<MODEL, RESULT>,
		
		NUMERIC_NEXT extends ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,
		STRING_NEXT extends ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,
		
		
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
		ALIAS_SQLTIMESTAMP_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>

		>

	extends Collector_ExpressionList<
		MODEL, RESULT, R,
		
		OPERAND_RET,
		
		NAMED_RET, ALIAS_RET,
		
		NUMERIC_NEXT, STRING_NEXT,
		
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
		ALIAS_SQLTIMESTAMP_RET
		
		>  {

			//private final IMappingCollector<MODEL, RESULT> impl;

		//abstract IMappingCollector<MODEL, RESULT> getMappingCollector(EFieldAccessType fieldAccessType);

	
	private final Collector_Conditions_GroupBy<MODEL, RESULT, ?> clause;
			
	
	Conditions_ExpressionList_Base(Collector_Conditions_GroupBy<MODEL, RESULT, ?> clause, Expression expression, EFieldAccessType fieldAccessType) {
		super(expression, fieldAccessType);
		
		this.clause = clause;
	}
	
	

	/*
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
	}
	*/

	final Collector_Conditions_GroupBy<MODEL, RESULT, ?> getRetClause() {
		return clause;
	}


	private class Conditions_Named_Functions extends
	
		NamedFunctions<
			ISharedConditionFunctions_Numeric_Named<
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
			ISharedConditionFunctions_Numeric_Named<
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
			ISharedConditionFunctions_String_StringResult_Named<
			    	MODEL,
			    	RESULT,
			    	NAMED_RET,
			    	NAMED_STRING_RET,
			    	NAMED_STRING_RET
			>,
			ISharedConditionFunctions_String_StringResult_Named<
				MODEL,
				RESULT,
				NAMED_RET,
				
				NAMED_LENGTH_RET,
				NAMED_LENGTH_RET
				>
		>
		
		implements ISharedConditionFunctions_Numeric_Named<
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
		ISharedConditionFunctions_String_Named<
			MODEL,
			RESULT,
			
			NAMED_RET,
			
			NAMED_LENGTH_RET,
			NAMED_STRING_RET,
			NAMED_LENGTH_RET,
			NAMED_STRING_RET
		>,
		ISharedConditionFunctions_String_StringResult_Named<
			MODEL,
			RESULT,
			NAMED_RET,
			
			NAMED_STRING_RET,
			NAMED_STRING_RET
		> {
	
		Conditions_Named_Functions(ISharedCollector_Functions_Callback<MODEL, RESULT, NAMED_RET> func) {
			super(func);
		}
	}

	@Override
	final NamedFunctions createNamedFunctions(ISharedCollector_Functions_Callback<MODEL, RESULT, NAMED_RET> func) {
		return new Conditions_Named_Functions(func);
	}

	private class Conditions_Alias_Functions extends
	
		AliasFunctions<
		
			ISharedConditionFunctions_Numeric_Alias<
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
			ISharedConditionFunctions_Numeric_Alias<
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
			ISharedConditionFunctions_String_StringResult_Alias<
				MODEL,
				RESULT,
			
				ALIAS_RET,
				
				ALIAS_STRING_RET,
				ALIAS_STRING_RET
			>,
			ISharedConditionFunctions_String_StringResult_Alias<
				MODEL,
				RESULT,
				ALIAS_RET,
				
				ALIAS_LENGTH_RET,
				ALIAS_LENGTH_RET
			>
		>
	
	implements 
		ISharedConditionFunctions_Numeric_Alias<
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
			
		ISharedConditionFunctions_String_Alias<
			MODEL,
			RESULT,
		
			ALIAS_RET,
			
			ALIAS_LENGTH_RET,
			ALIAS_STRING_RET,
			ALIAS_LENGTH_RET,
			ALIAS_STRING_RET
			>,				
		ISharedConditionFunctions_String_StringResult_Alias<
			MODEL,
			RESULT,
			ALIAS_RET,
			
			ALIAS_STRING_RET,
			ALIAS_STRING_RET
			> {

		Conditions_Alias_Functions(ISharedCollector_Functions_Callback<MODEL, RESULT, ALIAS_RET> func) {
			super(func);
		}
	}

	@Override
	final AliasFunctions createAliasFunctions(ISharedCollector_Functions_Callback<MODEL, RESULT, ALIAS_RET> func) {
		return new Conditions_Alias_Functions(func);
	}
}

