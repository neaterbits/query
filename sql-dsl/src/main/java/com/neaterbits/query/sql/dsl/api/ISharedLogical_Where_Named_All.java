package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedLogical_Where_Named_All<
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
			SQLDATE_CLAUSE 	extends ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Date, CONDITION_CLAUSE>,
			SQLTIME_CLAUSE 	extends ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Time, CONDITION_CLAUSE>,
			SQLTIMESTAMP_CLAUSE extends ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Timestamp, CONDITION_CLAUSE>,
			
			BYTEARRAY_CLAUSE extends ISharedCondition_ByteArray_All<MODEL, RESULT, CONDITION_CLAUSE>
			>

		extends ISharedLogical_Where_Named_Base<MODEL, RESULT, CONDITION_CLAUSE,

			BOOLEAN_CLAUSE,
			BYTE_CLAUSE,
			SHORT_CLAUSE,
			INTEGER_CLAUSE,
			LONG_CLAUSE,
			BIGINTEGER_CLAUSE,
			FLOAT_CLAUSE,
			DOUBLE_CLAUSE,
			BIGDECIMAL_CLAUSE,
			STRING_CLAUSE,
			DATE_CLAUSE,
			CALENDAR_CLAUSE,
			SQLDATE_CLAUSE,
			SQLTIME_CLAUSE,
			SQLTIMESTAMP_CLAUSE,
			BYTEARRAY_CLAUSE> {

	<T, E extends Enum<E>> ISharedCondition_Equality_All<MODEL, RESULT, E, CONDITION_CLAUSE> where(IFunctionEnum<T, E> func);

	
}
