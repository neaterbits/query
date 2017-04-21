package com.neaterbits.query.sql.dsl.api;

public interface ISharedLogical_And_Single_Named<
		MODEL, 
		RESULT,
		AND_CLAUSES extends ISharedLogical_And_Single_Named<MODEL, RESULT, AND_CLAUSES>>


	extends ISharedLogical_Base<MODEL, RESULT> {

    ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_CLAUSES> and(IFunctionInteger<RESULT> getter);

    ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES> and(IFunctionString<RESULT> getter);
	
}
