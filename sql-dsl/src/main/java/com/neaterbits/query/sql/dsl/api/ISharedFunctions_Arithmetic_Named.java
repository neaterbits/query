package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Arithmetic_Named<
			MODEL,
			RESULT,

			RET extends ISharedFunction_After<MODEL, RESULT>,

			INTEGER_RET  extends ISharedFunction_Next<MODEL, RESULT, RET>,
			LONG_RET 	 extends ISharedFunction_Next<MODEL, RESULT, RET>,
			DOUBLE_RET   extends ISharedFunction_Next<MODEL, RESULT, RET>

> {
			

	<T> INTEGER_RET abs(IFunctionInteger<T> getter);
	<T> LONG_RET abs(IFunctionLong<T> getter);

	// sqrt always returns double
	<T> DOUBLE_RET sqrt(IFunctionInteger<T> getter);
	<T> DOUBLE_RET sqrt(IFunctionLong<T> getter);
	
}
