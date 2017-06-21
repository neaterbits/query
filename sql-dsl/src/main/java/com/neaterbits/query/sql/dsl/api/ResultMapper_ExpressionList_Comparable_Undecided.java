package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

final class ResultMapper_ExpressionList_Comparable_Undecided< 
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
		ISharedFunction_After<MODEL, RESULT>, // Alias
		RET,
		
		ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, R, RET>, ISharedFunction_Next<MODEL, RESULT, RET>, // Named
		ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, R, RET>, ISharedFunction_Next<MODEL, RESULT, RET>, // Alias
		//ISharedResultMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, R, RET>, ISharedFunction_Next<MODEL, RESULT, RET>, // String
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, R, RET>, ISharedFunction_Next<MODEL, RESULT, RET>, // String
		
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
		
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, RET>,
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, RET>,
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Integer, RET>,
		
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Byte, RET>,
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Short, RET>,
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Integer, RET>,
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Long, RET>,
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, BigInteger, RET>,
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Float, RET>,
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, Double, RET>,
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, BigDecimal, RET>,
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, String, RET>,
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, java.util.Date, RET>,
		ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, java.util.Calendar, RET>,
		ISharedMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Date, RET>,
		ISharedMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Time, RET>,
		ISharedMap_OpsAndTo_SQLTimeType_Undecided<MODEL, RESULT, java.sql.Timestamp, RET>
	>
	
	implements ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, R, RET>
	
	{
	private final IMappingCollector<MODEL, RESULT> impl;
	
	// TODO go over constructor calls and use static utility methods? 
	ResultMapper_ExpressionList_Comparable_Undecided(Expression expression, IMappingCollector<MODEL, RESULT> impl) {
		super(expression /*, impl */, EFieldAccessType.UNDECIDED);
		
		if (impl == null) {
			throw new IllegalArgumentException("impl == null");
		}
		
		this.impl = impl;
	}
	
	
	@Override
	IMappingCollector<MODEL, RESULT> getMappingCollector(EFieldAccessType fieldAccessType) {
		if (fieldAccessType != EFieldAccessType.UNDECIDED) {
			throw new IllegalStateException("Expected undecided");
		}
		
		return impl;
	}


	@Override
	public ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, R, RET> plusOf(
			ISharedSubOperandsFunction_Undecided<MODEL, RESULT, BigDecimal> builder) {

		// Move to Collector_ExpressionList
		throw new UnsupportedOperationException("TODO");
	}
}

