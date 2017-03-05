package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Named_Arithmetic_NoParam<
	MODEL,
	RESULT,

	RET extends ISharedFunction_After<MODEL, RESULT>,

	INTEGER_CLAUSE	extends ISharedFunction_Next<MODEL, RESULT, RET>,
	LONG_CLAUSE		extends ISharedFunction_Next<MODEL, RESULT, RET>
	
	> {

	
	ISharedFunctions_Named_Arithmetic<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE> abs();
	
	ISharedFunctions_Named_Arithmetic<MODEL, RESULT, RET, INTEGER_CLAUSE, LONG_CLAUSE> sqrt();

}
