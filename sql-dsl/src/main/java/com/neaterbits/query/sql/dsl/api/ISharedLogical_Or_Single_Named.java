package com.neaterbits.query.sql.dsl.api;

public interface ISharedLogical_Or_Single_Named<
		MODEL, 
		RESULT,
		OR_CLAUSES extends ISharedLogical_Or_Single_Named<MODEL, RESULT, OR_CLAUSES>>

		extends ISharedLogical_Base<MODEL, RESULT> {

	    ISharedCondition_Equality_All<MODEL, RESULT, Integer, OR_CLAUSES> or(IFunctionInteger<RESULT> getter);
	    
	    ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES> or(StringFunction<RESULT> getter);

}
