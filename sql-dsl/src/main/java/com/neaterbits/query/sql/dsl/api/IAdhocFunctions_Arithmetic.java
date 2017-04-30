package com.neaterbits.query.sql.dsl.api;

public interface IAdhocFunctions_Arithmetic<
		MODEL, RESULT,
		ENTITY,
		RET extends ISharedLogical_Base<MODEL, RESULT>,
		
		INTEGER_CLAUSE extends ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Integer, RET>,
		LONG_CLAUSE extends ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Long, RET>> {

	

	INTEGER_CLAUSE abs(IFunctionInteger<ENTITY> getter);
	LONG_CLAUSE abs(IFunctionLong<ENTITY> getter);
	IAdhocFunctions_Arithmetic<MODEL, RESULT, ENTITY, RET, INTEGER_CLAUSE, LONG_CLAUSE> abs();


	INTEGER_CLAUSE sqrt(IFunctionInteger<ENTITY> getter);
	LONG_CLAUSE sqrt(IFunctionLong<ENTITY> getter);
	
	IAdhocFunctions_Arithmetic<MODEL, RESULT, ENTITY, RET, INTEGER_CLAUSE, LONG_CLAUSE> sqrt();
	
	
}
