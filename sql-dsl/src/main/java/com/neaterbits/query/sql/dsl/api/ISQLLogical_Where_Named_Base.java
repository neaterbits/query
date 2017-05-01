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
		
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Boolean, AND_OR>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Byte, AND_OR>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Short, AND_OR>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Integer, AND_OR>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Long, AND_OR>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, BigInteger, AND_OR>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Float, AND_OR>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Double, AND_OR>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, BigDecimal, AND_OR>,
		ISharedCondition_OpsAndComp_String_Named<MODEL, RESULT, AND_OR>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, java.util.Date, AND_OR>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, java.util.Calendar, AND_OR>,
		ISharedCondition_OpsAndComp_SQLTimeType_Named<MODEL, RESULT, java.sql.Date, AND_OR>,
		ISharedCondition_OpsAndComp_SQLTimeType_Named<MODEL, RESULT, java.sql.Time, AND_OR>,
		ISharedCondition_OpsAndComp_SQLTimeType_Named<MODEL, RESULT, java.sql.Timestamp, AND_OR>,
		ISharedCondition_OpsAndComp_ByteArray_Named<MODEL, RESULT, AND_OR>
		> {

}
