package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedLogical_And_Named_All<
			MODEL,
			RESULT,
			AND_CLAUSES extends ISharedLogical_And<MODEL, RESULT>,
			NESTED_OR_CLAUSES extends ISharedLogical_Or_Named<MODEL, RESULT>>

		extends ISharedLogical_And_Named_Base<
				MODEL,
				RESULT,
				AND_CLAUSES,
				NESTED_OR_CLAUSES,
				
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Boolean, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Byte, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigInteger, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Float, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, AND_CLAUSES>,
				ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, java.util.Date, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, java.util.Calendar, AND_CLAUSES>,
				ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Date, AND_CLAUSES>,
				ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Time, AND_CLAUSES>,
				ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Timestamp, AND_CLAUSES>,
				ISharedCondition_ByteArray_All<MODEL, RESULT, AND_CLAUSES>
			> {

}
