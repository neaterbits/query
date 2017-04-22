package com.neaterbits.query.sql.dsl.api;

interface FunctionVisitor<T, R> {

	R onAggregate(Function_Aggregate function, T param);
	
	// String
	R onStringLower(Function_String_Lower function, T param);

	R onStringUpper(Function_String_Upper function, T param);

	R onStringTrim(Function_String_Trim function, T param);
	
	R onStringSubstring(Function_String_Substring function, T param);
	
	@Deprecated
	R onStringConcat(Function_String_Concat function, T param);
	
	// Arithmetic
	R onArithmeticAbs(Function_Arithmetic_Abs function, T param);

	R onArithmeticSqrt(Function_Arithmetic_Sqrt function, T param);

}
