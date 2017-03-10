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
			
			ISharedResultOps_Numeric_Named<MODEL, RESULT, R, RET>,
			ISharedFunction_Next<MODEL, RESULT, RET>, // String
			
			ISharedResultOps_Numeric_Named<MODEL, RESULT, Long, RET>,
			ISharedResultOps_Numeric_Named<MODEL, RESULT, Long, RET>,
			
			ISharedResultOps_Numeric_Named<MODEL, RESULT, Short, RET>,
			ISharedResultOps_Numeric_Named<MODEL, RESULT, Integer, RET>,
			ISharedResultOps_Numeric_Named<MODEL, RESULT, Long, RET>,
			ISharedResultOps_Numeric_Named<MODEL, RESULT, Double, RET>,
			ISharedResultOps_Numeric_Named<MODEL, RESULT, BigDecimal, RET>,
			ISharedResultOps_Numeric_Named<MODEL, RESULT, String, RET>,
			

			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>
			
			>

	implements ISharedResultOps_Numeric_Named<MODEL, RESULT, R, RET>

{
	// TODO go over constructor calls and use static utility methods? 
	ResultMapper_ExpressionList_Numeric_Named(Expression expression, IMappingCollector<MODEL, RESULT> impl) {
		super(expression, impl);
	}
}
