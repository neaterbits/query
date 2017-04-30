package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedLogical_And_Named_Values<
				MODEL,
				RESULT,
				AND_CLAUSES extends ISharedLogical_And_Named_Values<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
				NESTED_OR_CLAUSES extends ISharedLogical_Or_Named<MODEL, RESULT>>

	extends ISharedLogical_And_Named_Base<
		MODEL,
		RESULT,
		AND_CLAUSES,
		NESTED_OR_CLAUSES,
		
		ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Boolean, AND_CLAUSES>,
		ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Byte, AND_CLAUSES>,
		ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Short, AND_CLAUSES>,
		ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Integer, AND_CLAUSES>,
		ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Long, AND_CLAUSES>,
		ISharedComparison_Comparable_Common_Value<MODEL, RESULT, BigInteger, AND_CLAUSES>,
		ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Float, AND_CLAUSES>,
		ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Double, AND_CLAUSES>,
		ISharedComparison_Comparable_Common_Value<MODEL, RESULT, BigDecimal, AND_CLAUSES>,
		ISharedComparison_Comparable_String_Value<MODEL, RESULT, AND_CLAUSES>,
		ISharedComparison_Comparable_Common_Value<MODEL, RESULT, java.util.Date, AND_CLAUSES>,
		ISharedComparison_Comparable_Common_Value<MODEL, RESULT, java.util.Calendar, AND_CLAUSES>,
		
		ISharedComparison_SQLTimeType_Value<MODEL, RESULT, java.sql.Date, AND_CLAUSES>,
		ISharedComparison_SQLTimeType_Value<MODEL, RESULT, java.sql.Time, AND_CLAUSES>,
		ISharedComparison_SQLTimeType_Value<MODEL, RESULT, java.sql.Timestamp, AND_CLAUSES>,

		ISharedComparison_ByteArray_Value<MODEL, RESULT, AND_CLAUSES>> {

}
