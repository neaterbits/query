package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

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
		
		ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Integer, AND_CLAUSES>,
		ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Long, AND_CLAUSES>,
		ISharedCondition_Comparable_Common_Value<MODEL, RESULT, BigDecimal, AND_CLAUSES>,
		ISharedCondition_Comparable_String_Value<MODEL, RESULT, AND_CLAUSES>> {

}
