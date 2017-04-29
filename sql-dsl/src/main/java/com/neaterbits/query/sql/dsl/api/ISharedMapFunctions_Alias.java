package com.neaterbits.query.sql.dsl.api;

//NO similar named ISharedMapFunctions_Named ?
@Deprecated 
public interface ISharedMapFunctions_Alias<
		MODEL,
		RESULT,

		RET extends ISharedSelectSourceBuilder<MODEL, RESULT>,

		SUM_LONG_RET,
		COUNT_RET,
		LENGTH_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,

		BYTE_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		SHORT_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		INT_RET		 	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		BIGINTEGER_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		FLOAT_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		DOUBLE_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		BIGDECIMAL_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		DATE_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		CALENDAR_RET	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		SQLDATE_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		SQLTIME_RET		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		SQLTIMESTAMP_RET extends ISharedFunction_Next<MODEL, RESULT, RET>
	>

	extends 

		// named without no-param (because of signature collision)
		ISharedFunctions_All_Transform_Alias<MODEL, RESULT, RET, LENGTH_RET, BYTE_RET, SHORT_RET, INT_RET, LONG_RET, BIGINTEGER_RET, FLOAT_RET, DOUBLE_RET, BIGDECIMAL_RET, STRING_RET>,

		// Aggregates as well 
		ISharedFunctions_Aggregate_Alias_All<SUM_LONG_RET, COUNT_RET, BYTE_RET, SHORT_RET, INT_RET, LONG_RET, BIGINTEGER_RET, FLOAT_RET, DOUBLE_RET, BIGDECIMAL_RET, DATE_RET>

{

}
