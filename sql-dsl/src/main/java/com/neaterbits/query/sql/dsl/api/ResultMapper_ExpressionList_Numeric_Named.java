package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

final class ResultMapper_ExpressionList_Numeric_Named< 
		MODEL,
		RESULT,
		R extends Comparable<R>,
		
		RET extends ISharedFunction_After<MODEL, RESULT>>

	extends ResultMapper_ExpressionList_Base<
			MODEL,
			RESULT,
			R,
			
			RET,
			
			RET,
			ISharedFunction_After<MODEL, RESULT>, // Alias
			
			ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, R, RET>,
			ISharedFunction_Next<MODEL, RESULT, RET>, // String
			
			ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Long, RET>,
			ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Long, RET>,
			
			ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Short, RET>,
			ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Integer, RET>,
			ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Long, RET>,
			ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Double, RET>,
			ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, BigDecimal, RET>,
			ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, String, RET>,
			

			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>
			
			>

	implements ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, R, RET>

{
	private final IMappingCollector<MODEL, RESULT> impl;
	
	// TODO go over constructor calls and use static utility methods? 
	ResultMapper_ExpressionList_Numeric_Named(Expression expression, IMappingCollector<MODEL, RESULT> impl) {
		super(expression /*, impl */, EFieldAccessType.NAMED);

		if (impl == null) {
			throw new IllegalArgumentException("impl == null");
		}
		
		this.impl = impl;
	}
	
	
	@Override
	IMappingCollector<MODEL, RESULT> getMappingCollector(EFieldAccessType fieldAccessType) {
		if (fieldAccessType != EFieldAccessType.NAMED) {
			throw new IllegalStateException("Expected named");
		}
		
		return impl;
	}
}
