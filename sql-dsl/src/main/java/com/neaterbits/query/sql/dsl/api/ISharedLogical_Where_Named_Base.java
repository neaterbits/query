package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedLogical_Where_Named_Base<
					MODEL,
					RESULT,
					CONDITION_CLAUSE extends ISharedLogical_Base<MODEL, RESULT>,
					
					BOOLEAN_CLAUSE 	extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Boolean, CONDITION_CLAUSE>,
					BYTE_CLAUSE 	extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Byte, CONDITION_CLAUSE>,
					SHORT_CLAUSE 	extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Short, CONDITION_CLAUSE>,
					INTEGER_CLAUSE 	extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, CONDITION_CLAUSE>,
					LONG_CLAUSE 	extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Long, CONDITION_CLAUSE>,
					BIGINTEGER_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, BigInteger, CONDITION_CLAUSE>,
					FLOAT_CLAUSE 	extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Float, CONDITION_CLAUSE>,
					DOUBLE_CLAUSE 	extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Double, CONDITION_CLAUSE>,
					BIGDECIMAL_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, BigDecimal, CONDITION_CLAUSE>,
					STRING_CLAUSE 	extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, CONDITION_CLAUSE>,
					DATE_CLAUSE 	extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, java.util.Date, CONDITION_CLAUSE>,
					CALENDAR_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, java.util.Calendar, CONDITION_CLAUSE>,
					SQLDATE_CLAUSE 	extends ISharedCondition_SQLTimeType_Base<MODEL, RESULT, java.sql.Date, CONDITION_CLAUSE>,
					SQLTIME_CLAUSE 	extends ISharedCondition_SQLTimeType_Base<MODEL, RESULT, java.sql.Time, CONDITION_CLAUSE>,
					SQLTIMESTAMP_CLAUSE extends ISharedCondition_SQLTimeType_Base<MODEL, RESULT, java.sql.Timestamp, CONDITION_CLAUSE>,
					
					BYTEARRAY_CLAUSE extends ISharedCondition_ByteArray_Base<MODEL, RESULT, CONDITION_CLAUSE>
					>

	extends ISharedLogical_Where<MODEL, RESULT> {

	
						
	<T> BOOLEAN_CLAUSE where(IFunctionBoolean<T> func);
						
	<T> BYTE_CLAUSE where(IFunctionByte<T> func);

	<T> SHORT_CLAUSE where(IFunctionShort<T> func);

	<T> INTEGER_CLAUSE where(IFunctionInteger<T> func);

	<T> LONG_CLAUSE where(IFunctionLong<T> func);

	<T> BIGINTEGER_CLAUSE where(IFunctionBigInteger<T> func);

	<T> FLOAT_CLAUSE where(IFunctionFloat<T> func);

	<T> DOUBLE_CLAUSE where(IFunctionDouble<T> func);
	
	<T> BIGDECIMAL_CLAUSE where(IFunctionBigDecimal<T> func);

	<T> STRING_CLAUSE where(StringFunction<T> func);
    
	<T> DATE_CLAUSE where(IFunctionDate<T> func);

	<T> CALENDAR_CLAUSE where(IFunctionCalendar<T> func);

	<T> SQLDATE_CLAUSE where(IFunctionSQLDate<T> func);

	<T> SQLTIME_CLAUSE where(IFunctionSQLTime<T> func);

	<T> SQLTIMESTAMP_CLAUSE where(IFunctionSQLTimestamp<T> func);
	
	<T> BYTEARRAY_CLAUSE where(IFunctionByteArray<T> func);
}
