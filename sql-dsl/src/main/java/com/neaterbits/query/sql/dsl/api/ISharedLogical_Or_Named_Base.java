package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedLogical_Or_Named_Base<
	MODEL,
	RESULT,
	OR_CLAUSES extends ISharedLogical_Or<MODEL, RESULT>,
	NESTED_AND_CLAUSES extends ISharedLogical_And_Named<MODEL, RESULT>,
	
	BOOLEAN_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Boolean, OR_CLAUSES>,
	BYTE_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Byte, OR_CLAUSES>,
	SHORT_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Short, OR_CLAUSES>,
	INTEGER_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, OR_CLAUSES>,
	LONG_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Long, OR_CLAUSES>,
	BIGINTEGER_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, BigInteger, OR_CLAUSES>,
	FLOAT_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Float, OR_CLAUSES>,
	DOUBLE_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Double, OR_CLAUSES>,
	BIGDECIMAL_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, BigDecimal, OR_CLAUSES>,
	STRING_CLAUSE extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, OR_CLAUSES>,
	DATE_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, java.util.Date, OR_CLAUSES>,
	CALENDAR_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, java.util.Calendar, OR_CLAUSES>,
	SQLDATE_CLAUSE extends ISharedCondition_SQLTimeType_Base<MODEL, RESULT, java.sql.Date, OR_CLAUSES>,
	SQLTIME_CLAUSE extends ISharedCondition_SQLTimeType_Base<MODEL, RESULT, java.sql.Time, OR_CLAUSES>,
	SQLTIMESTAMP_CLAUSE extends ISharedCondition_SQLTimeType_Base<MODEL, RESULT, java.sql.Timestamp, OR_CLAUSES>,
	BYTEARRAY_CLAUSE extends ISharedCondition_ByteArray_Base<MODEL, RESULT, OR_CLAUSES>
	>

	extends ISharedLogical_Or_Named<MODEL, RESULT> {

    <T> BOOLEAN_CLAUSE or(IFunctionBoolean<T> getter);

    <T> BYTE_CLAUSE or(IFunctionByte<T> getter);

    <T> SHORT_CLAUSE or(IFunctionShort<T> getter);

    <T> INTEGER_CLAUSE or(IFunctionInteger<T> getter);
    
    <T> LONG_CLAUSE or(IFunctionLong<T> getter);

    <T> BIGINTEGER_CLAUSE or(IFunctionBigInteger<T> getter);
    
    <T> FLOAT_CLAUSE or(IFunctionFloat<T> getter);

    <T> DOUBLE_CLAUSE or(IFunctionDouble<T> getter);

    <T> BIGDECIMAL_CLAUSE or(IFunctionBigDecimal<T> getter);

    <T> STRING_CLAUSE or(IFunctionString<T> getter);
    
    <T> DATE_CLAUSE or(IFunctionDate<T> getter);
    
    <T> CALENDAR_CLAUSE or(IFunctionCalendar<T> getter);
    
    <T> SQLDATE_CLAUSE or(IFunctionSQLDate<T> getter);
    
    <T> SQLTIME_CLAUSE or(IFunctionSQLTime<T> getter);

    <T> SQLTIMESTAMP_CLAUSE or(IFunctionSQLTimestamp<T> getter);

    <T> BYTEARRAY_CLAUSE or(IFunctionByteArray<T> getter);

    OR_CLAUSES orNest(ISharedNestedAndConsumerNamed<MODEL, RESULT, NESTED_AND_CLAUSES> andBuilder);
    
}
