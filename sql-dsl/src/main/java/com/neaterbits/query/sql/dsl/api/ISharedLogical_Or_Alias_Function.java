package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface ISharedLogical_Or_Alias_Function<
		MODEL,
		RESULT,
		OR_CLAUSES extends ISharedLogical_Or_Alias_Base<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES>,
		NESTED_AND_CLAUSES extends ISharedLogical_And_Alias<MODEL, RESULT>>

{

    ISharedFunctions_Transform_Initial_Alias<
		MODEL,
		RESULT,
		OR_CLAUSES,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, OR_CLAUSES>,
		ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES>>
	
		or();

}
