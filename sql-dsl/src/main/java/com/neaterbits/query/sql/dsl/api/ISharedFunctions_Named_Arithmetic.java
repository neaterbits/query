package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Named_Arithmetic<
			MODEL,
			RESULT,

			RET extends ISharedFunction_After<MODEL, RESULT>,

			INTEGER_CLAUSE  extends ISharedFunction_Next<MODEL, RESULT, RET>,
			LONG_CLAUSE 	extends ISharedFunction_Next<MODEL, RESULT, RET>

> {
			

	<T> INTEGER_CLAUSE abs(IFunctionInteger<T> getter);
	<T> LONG_CLAUSE abs(IFunctionLong<T> getter);

	<T> INTEGER_CLAUSE sqrt(IFunctionInteger<T> getter);
	<T> LONG_CLAUSE sqrt(IFunctionLong<T> getter);
	
}
