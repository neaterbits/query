package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface ISharedLogical_Where_Named_Value<
			MODEL,
			RESULT,
			CONDITION_CLAUSE extends ISharedLogical_Base<MODEL, RESULT>>

		extends ISharedLogical_Where_Named_Base<MODEL, RESULT, CONDITION_CLAUSE,

		ISharedCondition_Comparable_Common_Value<MODEL,RESULT,Integer,CONDITION_CLAUSE>,
		ISharedCondition_Comparable_Common_Value<MODEL,RESULT,Long,CONDITION_CLAUSE>,
		ISharedCondition_Comparable_Common_Value<MODEL,RESULT,BigDecimal,CONDITION_CLAUSE>,
		ISharedCondition_Comparable_String_Value<MODEL,RESULT,CONDITION_CLAUSE>>{

		<T, E extends Enum<E>> ISharedCondition_Equality_Value<MODEL, RESULT, E, CONDITION_CLAUSE> where(IFunctionEnum<T, E> func);

}

		