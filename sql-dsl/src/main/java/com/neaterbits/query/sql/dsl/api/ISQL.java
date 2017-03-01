package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface ISQL<
		// for sums, we return Long for short and int so must differentiate from other aggregate
		// fuctions, this is because sum may wrap over limits of type
		SUM_LONG_RET,
		COUNT_RET,
		
		// for other types aggregates, we return the same result as the input type, eg.
		// max of short-type will never be > short type
		SHORT_RET,
		INT_RET,
		LONG_RET,
		BIGDECIMAL_RET>

		extends IShared_Aggregate_All_Named<
			IClassicResult_Numeric_Named<Long>,
			IClassicResult_Numeric_Named<Long>,
			
			IClassicResult_Numeric_Named<Short>,
			IClassicResult_Numeric_Named<Integer>,
			IClassicResult_Numeric_Named<Long>,
			IClassicResult_Numeric_Named<BigDecimal>
			>

		{

}
