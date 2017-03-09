package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;

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
			
		
	extends Collector_NestedFunctions_Named<
			MODEL,
			RESULT,
			
			RET,
			
			SUM_LONG_RET,
			COUNT_RET,
			
			SHORT_RET,
			INT_RET,
			LONG_RET,
			DOUBLE_RET,
			BIGDECIMAL_RET,
			STRING_RET,
	
			ISharedMapFunctions_Numeric_Named<
			
				MODEL, RESULT, RET,
		
				//SUM_LONG_RET, COUNT_RET, SHORT_RET, INT_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET,
		
				// Pass NO_PARAM types for both recursively, since cannot use .plus() when nested functions in this format
				NO_PARAM_SUM_LONG_RET, NO_PARAM_COUNT_RET, NO_PARAM_SHORT_RET, NO_PARAM_INT_RET, NO_PARAM_LONG_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_BIGDECIMAL_RET,
				NO_PARAM_SUM_LONG_RET, NO_PARAM_COUNT_RET, NO_PARAM_SHORT_RET, NO_PARAM_INT_RET, NO_PARAM_LONG_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_BIGDECIMAL_RET
			>,

			// for sqrt()
	
			ISharedMapFunctions_Numeric_Named<MODEL, RESULT, RET,
			
				NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET,
				NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET>,
				
				
			NO_PARAM_STRING_RET
			
			/*
			NO_PARAM_SHORT_RET,
			NO_PARAM_INT_RET,
			NO_PARAM_LONG_RET,
			NO_PARAM_DOUBLE_RET,
			NO_PARAM_BIGDECIMAL_RET,
			NO_PARAM_STRING_RET
			*/
			
			>
			
		implements ISharedMapFunctions_Named<MODEL, RESULT, RET,
			SUM_LONG_RET, COUNT_RET, SHORT_RET, INT_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET, STRING_RET,
			NO_PARAM_SUM_LONG_RET, NO_PARAM_COUNT_RET, NO_PARAM_SHORT_RET, NO_PARAM_INT_RET, NO_PARAM_LONG_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_BIGDECIMAL_RET, NO_PARAM_STRING_RET>,
		
		    ISharedFunction_Next<MODEL, RESULT, RET>,
			
		    // May be called ".to()" here if we just collect functions 
		    ISharedResultMapperTo<MODEL, RESULT, Comparable, RET>
			
			
			{

	private final IMappingCollector<MODEL, RESULT> impl;
				
	Collector_MapFunctions_Named(ISharedCollector_Functions_Callback_Named<MODEL, RESULT, RET> func, /*, Collector_NestedFunctions_Base<MODEL, RESULT> last, */ IMappingCollector<MODEL, RESULT> impl ) {
		super(func);
		
		//super(func, last);
		
		if (impl == null) {
			throw new IllegalArgumentException("impl == null");
		}
		
		this.impl = impl;
	}

	@Override
	public final RET to(BiConsumer<RESULT, Comparable> setter) {
		
		final Expression expression = collectExpression();
		
		impl.getMappingCollector().add(null, expression, setter);
		
		return (RET)impl;
	}

	
	/*
	@Override
	@SuppressWarnings("unchecked")
	<R extends Comparable<R>, CLAUSE> CLAUSE addSubNumeric(Function_Arithmetic function, ISharedSubOperandsFunction_Named<MODEL, RESULT, R> sub) {

		final Expression expression = SubExpressionUtil.addSubNumericForFunction(function, sub, null);

		return (CLAUSE)new ResultMapperOps_Numeric<>(expression, impl);
	}

	@Override
	@SuppressWarnings("unchecked")
	<CLAUSE> CLAUSE addSubString(Function_String function, ISharedSubOperandsFunction_String_Named<MODEL, RESULT> sub) {

		final Expression expression = SubExpressionUtil.addSubStringForFunction(function, sub, null);

		return (CLAUSE)new ResultMapperOps_String<>(expression, impl);
	}

	// NoParam
	@SuppressWarnings({"unchecked", "rawtypes"})
	public ISharedMapFunctions_Numeric_Named<MODEL, RESULT, RET,
			NO_PARAM_SUM_LONG_RET, NO_PARAM_COUNT_RET, NO_PARAM_SHORT_RET, NO_PARAM_INT_RET, NO_PARAM_LONG_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_BIGDECIMAL_RET,
			NO_PARAM_SUM_LONG_RET, NO_PARAM_COUNT_RET, NO_PARAM_SHORT_RET, NO_PARAM_INT_RET, NO_PARAM_LONG_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_BIGDECIMAL_RET>
				abs() {
					
		add(Function_Arithmetic_Abs.INSTANCE);
		
		return (ISharedMapFunctions_Numeric_Named)this;
	}
	*/
	
	

	/*
	@Override
	public ISharedMapFunctions_Numeric_Named<MODEL, RESULT, RET, SUM_LONG_RET, COUNT_RET, SHORT_RET, INT_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET> sqrt() {
		add(Function_Arithmetic_Sqrt.INSTANCE);

		return this;
	}
	*/

	/*
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public ISharedMapFunctions_Numeric_Named<
				MODEL, RESULT, RET,
				NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET,
				NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET, NO_PARAM_DOUBLE_RET> sqrt() {
		add(Function_Arithmetic_Sqrt.INSTANCE);

		return (ISharedMapFunctions_Numeric_Named)this;
	}
	
				
	
	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public ISharedFunctions_String_Named<MODEL, RESULT, RET, NO_PARAM_STRING_RET> lower() {
		add(Function_String_Lower.INSTANCE);
		
		return (ISharedFunctions_String_Named)this;
	}



	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
		public ISharedFunctions_String_Named<MODEL, RESULT, RET, NO_PARAM_STRING_RET> upper() {
		add(Function_String_Upper.INSTANCE);
		
		return (ISharedFunctions_String_Named)this;
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
		public ISharedFunctions_String_Named<MODEL, RESULT, RET, NO_PARAM_STRING_RET> trim() {
		add(Function_String_Trim.INSTANCE);
		
		return (ISharedFunctions_String_Named)this;
	}
	*/

	
}

