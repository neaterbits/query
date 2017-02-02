package com.neaterbits.query.sql.dsl.api;

public interface IAdhocFunctions_Arithmetic<
		MODEL, RESULT,
		ENTITY,
		RET extends ISharedLogical_Base<MODEL, RESULT>,
		
		COMPARABLE_CLAUSE extends ISharedCondition_Comparable_Common_Value<MODEL, RESULT, ?, RET>> {

	

	COMPARABLE_CLAUSE abs(IFunctionInteger<ENTITY> getter);
	COMPARABLE_CLAUSE abs(IFunctionLong<ENTITY> getter);
	IAdhocFunctions_Arithmetic<MODEL, RESULT, ENTITY, RET, COMPARABLE_CLAUSE> abs();


	COMPARABLE_CLAUSE sqrt(IFunctionInteger<ENTITY> getter);
	COMPARABLE_CLAUSE sqrt(IFunctionLong<ENTITY> getter);
	
	IAdhocFunctions_Arithmetic<MODEL, RESULT, ENTITY, RET, COMPARABLE_CLAUSE> sqrt();
	
	
}
