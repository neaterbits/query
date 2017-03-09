package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Aggregate_Named<

				// for sums, we return Long for short and int so must differentiate from other aggregate
				// fuctions, this is because sum may wrap over limits of type
				SUM_LONG_RET,
				COUNT_RET,

				// for other types aggregates, we return the same result as the input type, eg.
				// max of short-type will never be > short type
				SHORT_RET,
				INT_RET,
				LONG_RET,
				DOUBLE_RET,
				BIGDECIMAL_RET>

		extends IShared_Aggregate_Sum_Named_All<SUM_LONG_RET, BIGDECIMAL_RET>,
				IShared_Aggregate_Count_Named_All<COUNT_RET>,
				IShared_Aggregate_Max_Named<SHORT_RET, INT_RET, LONG_RET, BIGDECIMAL_RET>,
				IShared_Aggregate_Min_Named<SHORT_RET, INT_RET, LONG_RET, BIGDECIMAL_RET>,
				IShared_Aggregate_Avg_Named<SHORT_RET, INT_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET> {

}
