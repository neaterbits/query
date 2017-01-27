package com.neaterbits.query.sql.dsl.api;

interface FunctionVisitor<T, R> {
	
	// String
	R onStringLower(Function_String_Lower function, T param);

	R onStringUpper(Function_String_Upper function, T param);

	R onStringTrim(Function_String_Trim function, T param);
	
	
	// Arithmetic
	R onArithmeticAbs(Function_Arithmetic_Abs function, T param);

	R onArithmeticSqrt(Function_Arithmetic_Sqrt function, T param);

}
