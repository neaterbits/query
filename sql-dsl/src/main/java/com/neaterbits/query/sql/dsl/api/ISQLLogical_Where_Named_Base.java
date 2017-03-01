package com.neaterbits.query.sql.dsl.api;

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
		
		ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, AND_OR>,
		ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Long, AND_OR>,
		ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, AND_OR>> {

}
