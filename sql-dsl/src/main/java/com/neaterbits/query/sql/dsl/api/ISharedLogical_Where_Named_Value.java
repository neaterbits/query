package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedLogical_Where_Named_Value<
			MODEL,
			RESULT,
			CONDITION_CLAUSE extends ISharedLogical_Base<MODEL, RESULT>>

		extends ISharedLogical_Where_Named_Base<MODEL, RESULT, CONDITION_CLAUSE,

		ISharedComparison_Comparable_Common_Value<MODEL,RESULT,Boolean,CONDITION_CLAUSE>,
		ISharedComparison_Comparable_Common_Value<MODEL,RESULT,Byte,CONDITION_CLAUSE>,
		ISharedComparison_Comparable_Common_Value<MODEL,RESULT,Short,CONDITION_CLAUSE>,
		ISharedComparison_Comparable_Common_Value<MODEL,RESULT,Integer,CONDITION_CLAUSE>,
		ISharedComparison_Comparable_Common_Value<MODEL,RESULT,Long,CONDITION_CLAUSE>,
		ISharedComparison_Comparable_Common_Value<MODEL,RESULT,BigInteger,CONDITION_CLAUSE>,
		ISharedComparison_Comparable_Common_Value<MODEL,RESULT,Float,CONDITION_CLAUSE>,
		ISharedComparison_Comparable_Common_Value<MODEL,RESULT,Double,CONDITION_CLAUSE>,
		ISharedComparison_Comparable_Common_Value<MODEL,RESULT,BigDecimal,CONDITION_CLAUSE>,
		ISharedComparison_Comparable_String_Value<MODEL,RESULT,CONDITION_CLAUSE>,
		ISharedComparison_Comparable_Common_Value<MODEL, RESULT, java.util.Date, CONDITION_CLAUSE>,
		ISharedComparison_Comparable_Common_Value<MODEL, RESULT, java.util.Calendar, CONDITION_CLAUSE>,
		ISharedComparison_SQLTimeType_Value<MODEL, RESULT, java.sql.Date, CONDITION_CLAUSE>,
		ISharedComparison_SQLTimeType_Value<MODEL, RESULT, java.sql.Time, CONDITION_CLAUSE>,
		ISharedComparison_SQLTimeType_Value<MODEL, RESULT, java.sql.Timestamp, CONDITION_CLAUSE>,
		ISharedComparison_ByteArray_Value<MODEL, RESULT, CONDITION_CLAUSE>
		>{

		<T, E extends Enum<E>> ISharedComparison_Equality_Value<MODEL, RESULT, E, CONDITION_CLAUSE> where(IFunctionEnum<T, E> func);

}

		