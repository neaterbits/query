package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Must be separate interface to avoid collisions with named-implementation
 * @author nhl
 *
 */

public interface ISharedLogical_And_Alias_Function<
			MODEL,
			RESULT,
			AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
			NESTED_OR_CLAUSES extends ISharedLogical_Or_Alias<MODEL, RESULT>> {

    ISharedFunctions_Transform_Initial_Alias<
		MODEL,
		RESULT,
		AND_CLAUSES,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_CLAUSES>,

		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Byte, AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigInteger, AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Float, AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, AND_CLAUSES>,
		ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES>>
	
		and();
	
}
