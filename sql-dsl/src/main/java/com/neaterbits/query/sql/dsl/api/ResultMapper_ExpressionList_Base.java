package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiConsumer;

abstract class ResultMapper_ExpressionList_Base<
			MODEL,
			RESULT,
			R extends Comparable<R>, 
			AFTER extends ISharedFunction_After<MODEL,RESULT>,
			
			NEXT extends ISharedFunction_Next<MODEL, RESULT, AFTER>,
			
			
			SUM_LONG_RET  	extends ISharedFunction_Next<MODEL, RESULT, AFTER>,
			COUNT_RET       extends ISharedFunction_Next<MODEL, RESULT, AFTER>,

			SHORT_RET  		extends ISharedFunction_Next<MODEL, RESULT, AFTER>,
			INTEGER_RET  	extends ISharedFunction_Next<MODEL, RESULT, AFTER>,
			LONG_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, AFTER>,
			DOUBLE_RET   	extends ISharedFunction_Next<MODEL, RESULT, AFTER>,
			BIGDECIMAL_RET  extends ISharedFunction_Next<MODEL, RESULT, AFTER>,
			STRING_RET  extends ISharedFunction_Next<MODEL, RESULT, AFTER>
			
			>

		extends Collector_ExpressionList<
				MODEL, RESULT, R,
				AFTER,
				
				NEXT, NEXT,
				
				SUM_LONG_RET,
				COUNT_RET,
				
				SHORT_RET,
				INTEGER_RET,
				LONG_RET,
				DOUBLE_RET,
				BIGDECIMAL_RET,
				STRING_RET> 

		implements ISharedResultMapperTo<MODEL, RESULT, R, AFTER> {

	private final IMappingCollector<MODEL, RESULT> impl;
	
	ResultMapper_ExpressionList_Base(IMappingCollector<MODEL, RESULT> impl) {
		if (impl == null) {
			throw new IllegalArgumentException("impl == null");
		}

		this.impl = impl;
	}

	ResultMapper_ExpressionList_Base(Expression expression, IMappingCollector<MODEL, RESULT> impl) {
		super(expression);
		
		if (impl == null) {
			throw new IllegalArgumentException("impl == null");
		}

		this.impl = impl;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final AFTER to(BiConsumer<RESULT, R> setter) {
		
		final Expression toForward = collectExpressionListOrOne();

		impl.getMappingCollector().add(this, toForward, setter);

		return (AFTER)impl;
	}
}
