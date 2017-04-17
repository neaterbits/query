package com.neaterbits.query.sql.dsl.api;

public interface ISQL<
		// for sums, we return Long for short and int so must differentiate from other aggregate
		// fuctions, this is because sum may wrap over limits of type
		NAMED_SUM_LONG_RET,
		NAMED_COUNT_RET,
		
		// for other types aggregates, we return the same result as the input type, eg.
		// max of short-type will never be > short type
		NAMED_BYTE_RET,
		NAMED_SHORT_RET,
		NAMED_INT_RET,
		NAMED_LONG_RET,
		NAMED_BIGINTEGER_RET,
		NAMED_FLOAT_RET,
		NAMED_DOUBLE_RET,
		NAMED_BIGDECIMAL_RET,
		NAMED_DATE_RET,
		NAMED_CALENDAR_RET,
		NAMED_SQLDATE_RET,
		NAMED_SQLTIME_RET,
		NAMED_SQLTIMESTAMP_RET,
		
		
		ALIAS_SUM_LONG_RET,
		ALIAS_COUNT_RET,
		
		ALIAS_BYTE_RET,
		ALIAS_SHORT_RET,
		ALIAS_INT_RET,
		ALIAS_LONG_RET,
		ALIAS_BIGINTEGER_RET,
		ALIAS_FLOAT_RET,
		ALIAS_DOUBLE_RET,
		ALIAS_BIGDECIMAL_RET,
		ALIAS_DATE_RET,
		ALIAS_CALENDAR_RET,
		ALIAS_SQLDATE_RET,
		ALIAS_SQLTIME_RET,
		ALIAS_SQLTIMESTAMP_RET
		>

		extends ISharedFunctions_Aggregate_Named<
			NAMED_SUM_LONG_RET,
			NAMED_COUNT_RET,
			
			NAMED_SHORT_RET,
			NAMED_INT_RET,
			NAMED_LONG_RET,
			NAMED_DOUBLE_RET,
			NAMED_BIGDECIMAL_RET,
			NAMED_DATE_RET
			>
		,

		ISharedFunctions_Aggregate_Alias<
			ALIAS_SUM_LONG_RET,
			ALIAS_COUNT_RET,
			
			ALIAS_BYTE_RET,
			ALIAS_SHORT_RET,
			ALIAS_INT_RET,
			ALIAS_LONG_RET,
			ALIAS_BIGINTEGER_RET,
			ALIAS_FLOAT_RET,
			ALIAS_DOUBLE_RET,
			ALIAS_BIGDECIMAL_RET,
			ALIAS_DATE_RET
		>,

		
		IQueryPreparation {
			
}
