package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

final class ResultMapper_ExpressionList_Comparable_Alias< 
		MODEL,
		RESULT,
		R extends Comparable<R>,
		
		RET extends ISharedFunction_After<MODEL, RESULT>
	>

	extends ResultMapper_ExpressionList_Base<
			MODEL,
			RESULT,
			R,
			
			RET,
			
			ISharedFunction_After<MODEL, RESULT>, // Named
			RET,
			ISharedFunction_After<MODEL, RESULT>, // Undecided
			
			ISharedFunction_Next<MODEL, RESULT, RET>, ISharedFunction_Next<MODEL, RESULT, RET>, // Named
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, R, RET>, ISharedFunction_Next<MODEL, RESULT, RET>, // String
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, R, RET>, ISharedFunction_Next<MODEL, RESULT, RET>, // String
			
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
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, RET>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, RET>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Integer, RET>,
			
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Byte, RET>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Short, RET>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Integer, RET>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Long, RET>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, BigInteger, RET>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Float, RET>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, Double, RET>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, BigDecimal, RET>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, String, RET>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, java.util.Date, RET>,
			ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, java.util.Calendar, RET>,
			ISharedMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Date, RET>,
			ISharedMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Time, RET>,
			ISharedMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, java.sql.Timestamp, RET>,
			
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
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>,
			ISharedFunction_Next<MODEL, RESULT, ISharedFunction_After<MODEL, RESULT>>
	
	>

	implements ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, R, RET>

{
	private final IMappingCollector<MODEL, RESULT> impl;
	
	// TODO go over constructor calls and use static utility methods? 
	ResultMapper_ExpressionList_Comparable_Alias(Expression expression, IMappingCollector<MODEL, RESULT> impl) {
		super(expression /*, impl */, EFieldAccessType.ALIAS);
		
		if (impl == null) {
			throw new IllegalArgumentException("impl == null");
		}
		
		this.impl = impl;
	}


	@Override
	IMappingCollector<MODEL, RESULT> getMappingCollector(EFieldAccessType fieldAccessType) {
		if (fieldAccessType != EFieldAccessType.ALIAS) {
			throw new IllegalStateException("Expected alias");
		}
	
		return impl;
	}
}
