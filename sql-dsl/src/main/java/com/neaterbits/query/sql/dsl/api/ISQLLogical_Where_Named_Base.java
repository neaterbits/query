package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

// base interface to separate between queries that allows for result processing
// and those that do not

public interface ISQLLogical_Where_Named_Base<
			MODEL,
			RESULT,
			AND_OR extends ISharedLogical_And_Or_Named_All<MODEL, RESULT, ?, ?, ?, ?>>

		extends ISharedLogical_Where_Named_All<
			MODEL,
			RESULT,
			AND_OR,
		
		ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Boolean, AND_OR>,
		ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Byte, AND_OR>,
		ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Short, AND_OR>,
		ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, AND_OR>,
		ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Long, AND_OR>,
		ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, BigInteger, AND_OR>,
		ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Float, AND_OR>,
		ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Double, AND_OR>,
		ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, BigDecimal, AND_OR>,
		ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, AND_OR>,
		ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, java.util.Date, AND_OR>,
		ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, java.util.Calendar, AND_OR>,
		ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Date, AND_OR>,
		ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Time, AND_OR>,
		ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Timestamp, AND_OR>,
		ISharedCondition_ByteArray_All<MODEL, RESULT, AND_OR>
		> {

}
