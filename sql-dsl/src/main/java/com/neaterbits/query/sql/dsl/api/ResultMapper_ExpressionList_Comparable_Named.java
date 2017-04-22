package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

final class ResultMapper_ExpressionList_Comparable_Named< 
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
			
			ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, R, RET>,
			ISharedFunction_Next<MODEL, RESULT, RET>, // String
			
			ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, RET>,
			ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, RET>,
			
			ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Byte, RET>,
			ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Short, RET>,
			ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer, RET>,
			ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, RET>,
			ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigInteger, RET>,
			ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Float, RET>,
			ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Double, RET>,
			ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigDecimal, RET>,
			ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, String, RET>,
			ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Date, RET>,
			ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Calendar, RET>,
			ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Date, RET>,
			ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Time, RET>,
			ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Timestamp, RET>,
			

			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>
			
			>

	implements ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, R, RET>

{
	private final IMappingCollector<MODEL, RESULT> impl;
	
	// TODO go over constructor calls and use static utility methods? 
	ResultMapper_ExpressionList_Comparable_Named(Expression expression, IMappingCollector<MODEL, RESULT> impl) {
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
