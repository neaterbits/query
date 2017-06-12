package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedLogical_And_Named_Base<
		MODEL,
		RESULT,
		AND_CLAUSES extends ISharedLogical_And<MODEL, RESULT>,
		NESTED_OR_CLAUSES extends ISharedLogical_Or_Named<MODEL, RESULT>,

		BOOLEAN_CLAUSE extends ISharedComparison_Comparable_Common_Base<MODEL, RESULT, Boolean, AND_CLAUSES>,
		BYTE_CLAUSE extends ISharedComparison_Comparable_Common_Base<MODEL, RESULT, Byte, AND_CLAUSES>,
		SHORT_CLAUSE extends ISharedComparison_Comparable_Common_Base<MODEL, RESULT, Short, AND_CLAUSES>,
		INTEGER_CLAUSE extends ISharedComparison_Comparable_Common_Base<MODEL, RESULT, Integer, AND_CLAUSES>,
		LONG_CLAUSE extends ISharedComparison_Comparable_Common_Base<MODEL, RESULT, Long, AND_CLAUSES>,
		BIGINTEGER_CLAUSE extends ISharedComparison_Comparable_Common_Base<MODEL, RESULT, BigInteger, AND_CLAUSES>,
		FLOAT_CLAUSE extends ISharedComparison_Comparable_Common_Base<MODEL, RESULT, Float, AND_CLAUSES>,
		DOUBLE_CLAUSE extends ISharedComparison_Comparable_Common_Base<MODEL, RESULT, Double, AND_CLAUSES>,
		BIGDECIMAL_CLAUSE extends ISharedComparison_Comparable_Common_Base<MODEL, RESULT, BigDecimal, AND_CLAUSES>,
		STRING_CLAUSE extends ISharedComparison_Comparable_String_Base<MODEL, RESULT, AND_CLAUSES>,
		DATE_CLAUSE extends ISharedComparison_Comparable_Common_Base<MODEL, RESULT, java.util.Date, AND_CLAUSES>,
		CALENDAR_CLAUSE extends ISharedComparison_Comparable_Common_Base<MODEL, RESULT, java.util.Calendar, AND_CLAUSES>,
		SQLDATE_CLAUSE extends ISharedComparison_SQLTimeType_Base<MODEL, RESULT, java.sql.Date, AND_CLAUSES>,
		SQLTIME_CLAUSE extends ISharedComparison_SQLTimeType_Base<MODEL, RESULT, java.sql.Time, AND_CLAUSES>,
		SQLTIMESTAMP_CLAUSE extends ISharedComparison_SQLTimeType_Base<MODEL, RESULT, java.sql.Timestamp, AND_CLAUSES>,
		BYTEARRAY_CLAUSE extends ISharedComparison_ByteArray_Base<MODEL, RESULT, AND_CLAUSES>
		>

		extends ISharedLogical_And_Named<MODEL, RESULT> {
	
    <T> BOOLEAN_CLAUSE and(IFunctionBoolean<T> getter);

    <T> BYTE_CLAUSE and(IFunctionByte<T> getter);

    <T> SHORT_CLAUSE and(IFunctionShort<T> getter);

    <T> INTEGER_CLAUSE and(IFunctionInteger<T> getter);

    <T> LONG_CLAUSE and(IFunctionLong<T> getter);

    <T> BIGINTEGER_CLAUSE and(IFunctionBigInteger<T> getter);

    <T> FLOAT_CLAUSE and(IFunctionFloat<T> getter);

    <T> DOUBLE_CLAUSE and(IFunctionDouble<T> getter);
    
    <T> BIGDECIMAL_CLAUSE and(IFunctionBigDecimal<T> getter);
    
    <T> STRING_CLAUSE and(IFunctionString<T> getter);

    <T> DATE_CLAUSE and(IFunctionDate<T> getter);

    <T> CALENDAR_CLAUSE and(IFunctionCalendar<T> getter);

    <T> SQLDATE_CLAUSE and(IFunctionSQLDate<T> getter);

    <T> SQLTIME_CLAUSE and(IFunctionSQLTime<T> getter);

    <T> SQLTIMESTAMP_CLAUSE and(IFunctionSQLTimestamp<T> getter);
    
    <T> BYTEARRAY_CLAUSE and(IFunctionByteArray<T> getter);

    AND_CLAUSES andNest(ISharedNested_Or_Consumer_Named<MODEL, RESULT, NESTED_OR_CLAUSES> orBuilder);
    
}