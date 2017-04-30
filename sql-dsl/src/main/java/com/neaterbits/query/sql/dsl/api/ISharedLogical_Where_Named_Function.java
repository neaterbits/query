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
			ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Integer, AND_OR>,

			ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Byte, AND_OR>,
			ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Short, AND_OR>,
			ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Integer, AND_OR>,
			ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Long, AND_OR>,
			ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, BigInteger, AND_OR>,
			ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Float, AND_OR>,
			ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Double, AND_OR>,
			ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, BigDecimal, AND_OR>,
			ISharedCondition_OpsAndComp_String_Named<MODEL, RESULT, AND_OR>>
			
				where();

}
