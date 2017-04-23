package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedLogical_Where_Named_Function<
		MODEL,
		RESULT,
		AND_OR extends ISharedLogical_Base<MODEL, RESULT>> {

	ISharedFunctions_Transform_Initial_Named<
			MODEL,
			RESULT,
			AND_OR,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, AND_OR>,

			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Byte, AND_OR>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Short, AND_OR>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, AND_OR>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Long, AND_OR>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, BigInteger, AND_OR>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Float, AND_OR>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Double, AND_OR>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, BigDecimal, AND_OR>,
			ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, AND_OR>>
			
				where();

}
