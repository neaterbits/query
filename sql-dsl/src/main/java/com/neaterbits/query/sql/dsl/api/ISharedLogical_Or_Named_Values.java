package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedLogical_Or_Named_Values<
				MODEL,
				RESULT,
				OR_CLAUSES extends ISharedLogical_Or_Named_Values<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES>,
				NESTED_AND_CLAUSES extends ISharedLogical_And_Named<MODEL, RESULT>>

		extends ISharedLogical_Or_Named_Base<
			MODEL,
			RESULT,
			OR_CLAUSES,
			NESTED_AND_CLAUSES,
			
			ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Boolean, OR_CLAUSES>,
			ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Byte, OR_CLAUSES>,
			ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Short, OR_CLAUSES>,
			ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Integer, OR_CLAUSES>,
			ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Long, OR_CLAUSES>,
			ISharedComparison_Comparable_Common_Value<MODEL, RESULT, BigInteger, OR_CLAUSES>,
			ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Float, OR_CLAUSES>,
			ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Double, OR_CLAUSES>,
			ISharedComparison_Comparable_Common_Value<MODEL, RESULT, BigDecimal, OR_CLAUSES>,
			ISharedComparison_Comparable_String_Value<MODEL, RESULT, OR_CLAUSES>,
			ISharedComparison_Comparable_Common_Value<MODEL, RESULT, java.util.Date, OR_CLAUSES>,
			ISharedComparison_Comparable_Common_Value<MODEL, RESULT, java.util.Calendar, OR_CLAUSES>,
			ISharedComparison_SQLTimeType_Value<MODEL, RESULT, java.sql.Date, OR_CLAUSES>,
			ISharedComparison_SQLTimeType_Value<MODEL, RESULT, java.sql.Time, OR_CLAUSES>,
			ISharedComparison_SQLTimeType_Value<MODEL, RESULT, java.sql.Timestamp, OR_CLAUSES>,
			ISharedComparison_ByteArray_Value<MODEL, RESULT, OR_CLAUSES>
		> {

}
