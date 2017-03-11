package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;

abstract class ResultMapper_ExpressionList_Base<
			MODEL,
			RESULT,
			R extends Comparable<R>, 
			
			OPERAND_RET extends ISharedFunction_After<MODEL, RESULT>,
			
			NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
			ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>,
			
			NUMERIC_NEXT extends ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,
			STRING_NEXT extends ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,
			
			
			NAMED_SUM_LONG_RET  extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_COUNT_RET     extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,

			NAMED_SHORT_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_INTEGER_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_LONG_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_DOUBLE_RET   	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_BIGDECIMAL_RET  extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			NAMED_STRING_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
			
			ALIAS_SUM_LONG_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_COUNT_RET       extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			
			ALIAS_SHORT_RET  		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_INTEGER_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_LONG_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_DOUBLE_RET   	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_BIGDECIMAL_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
			ALIAS_STRING_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>

			>

		extends Collector_ExpressionList<
				MODEL, RESULT, R,
				
				OPERAND_RET,
				
				NAMED_RET, ALIAS_RET,
				
				NUMERIC_NEXT, STRING_NEXT,
				
				NAMED_SUM_LONG_RET,
				NAMED_COUNT_RET,
				
				NAMED_SHORT_RET,
				NAMED_INTEGER_RET,
				NAMED_LONG_RET,
				NAMED_DOUBLE_RET,
				NAMED_BIGDECIMAL_RET,
				NAMED_STRING_RET,
				
				ALIAS_SUM_LONG_RET,
				ALIAS_COUNT_RET,
				
				ALIAS_SHORT_RET,
				ALIAS_INTEGER_RET,
				ALIAS_LONG_RET,
				ALIAS_DOUBLE_RET,
				ALIAS_BIGDECIMAL_RET,
				ALIAS_STRING_RET
				> 

		implements ISharedResultMapperTo<MODEL, RESULT, R, OPERAND_RET> {

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

	private class ResultMapper_Functions extends
	
		NamedFunctions<
			ISharedMapFunctions_Numeric_Named<
				MODEL,
				RESULT,
				
				NAMED_RET,
				
				NAMED_SUM_LONG_RET,
				NAMED_COUNT_RET,
				
				NAMED_SHORT_RET,
				NAMED_INTEGER_RET,
				NAMED_LONG_RET,
				NAMED_DOUBLE_RET,
				NAMED_BIGDECIMAL_RET,
				
				NAMED_SUM_LONG_RET,
				NAMED_COUNT_RET,
				
				NAMED_SHORT_RET,
				NAMED_INTEGER_RET,
				NAMED_LONG_RET,
				NAMED_DOUBLE_RET,
				NAMED_BIGDECIMAL_RET
			
			>,
			ISharedMapFunctions_Numeric_Named<
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
				NAMED_DOUBLE_RET
				
			>,
			ISharedFunctions_String_Named<MODEL, RESULT, NAMED_RET, NAMED_STRING_RET>
		>

	implements ISharedMapFunctions_Numeric_Named<
				MODEL,
				RESULT,
				
				NAMED_RET,
				
				NAMED_SUM_LONG_RET,
				NAMED_COUNT_RET,
				
				NAMED_SHORT_RET,
				NAMED_INTEGER_RET,
				NAMED_LONG_RET,
				NAMED_DOUBLE_RET,
				NAMED_BIGDECIMAL_RET,
				
				NAMED_SUM_LONG_RET,
				NAMED_COUNT_RET,
				
				NAMED_SHORT_RET,
				NAMED_INTEGER_RET,
				NAMED_LONG_RET,
				NAMED_DOUBLE_RET,
				NAMED_BIGDECIMAL_RET
				
				>
		{

		ResultMapper_Functions(ISharedCollector_Functions_Callback_Named<MODEL, RESULT, NAMED_RET> func) {
			super(func);
		}
	}
	
	@Override
	final NamedFunctions createNamedFunctions(
			ISharedCollector_Functions_Callback_Named<MODEL, RESULT, NAMED_RET> func) {
		return new ResultMapper_Functions(func);
	}
}
