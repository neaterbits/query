package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

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
			
			ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Integer, OR_CLAUSES>,
			ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Long, OR_CLAUSES>,
			ISharedCondition_Comparable_Common_Value<MODEL, RESULT, BigDecimal, OR_CLAUSES>,
			ISharedCondition_Comparable_String_Value<MODEL, RESULT, OR_CLAUSES>> {

}
