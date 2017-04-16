package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedLogical_Or_Named_All<
				MODEL,
				RESULT,
				OR_CLAUSES extends ISharedLogical_Or<MODEL, RESULT>,
				NESTED_AND_CLAUSES extends ISharedLogical_And_Named<MODEL, RESULT>>

		extends ISharedLogical_Or_Named_Base<
				MODEL,
				RESULT,
				OR_CLAUSES,
				NESTED_AND_CLAUSES,
				
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Boolean, OR_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Byte, OR_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, OR_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigInteger, OR_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Float, OR_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, OR_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, OR_CLAUSES>,
				ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, java.util.Date, OR_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, java.util.Calendar, OR_CLAUSES>,

				ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Date, OR_CLAUSES>,
				ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Time, OR_CLAUSES>,
				ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Timestamp, OR_CLAUSES>,
				
				ISharedCondition_ByteArray_All<MODEL, RESULT, OR_CLAUSES>
				
				> {

}
