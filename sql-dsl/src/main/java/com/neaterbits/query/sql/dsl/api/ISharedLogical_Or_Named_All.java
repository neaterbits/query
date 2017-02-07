package com.neaterbits.query.sql.dsl.api;

public interface ISharedLogical_Or_Named_All<
				MODEL,
				RESULT,
				OR_CLAUSES extends ISharedLogical_Or<MODEL, RESULT>,
				NESTED_AND_CLAUSES extends ISharedLogical_And_Named<MODEL, RESULT>>

		extends ISharedLogical_Or_Named_Base<
				MODEL,
				RESULT,
				OR_CLAUSES,
				NESTED_AND_CLAUSES,
				
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES>,
				ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES>> {

}
