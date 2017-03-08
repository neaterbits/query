package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Arithmetic_Named<
			MODEL,
			RESULT,

			RET extends ISharedFunction_After<MODEL, RESULT>,

			SHORT_RET  		extends ISharedFunction_Next<MODEL, RESULT, RET>,
			INTEGER_RET  	extends ISharedFunction_Next<MODEL, RESULT, RET>,
			LONG_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, RET>,
			DOUBLE_RET   	extends ISharedFunction_Next<MODEL, RESULT, RET>,
			BIGDECIMAL_RET  extends ISharedFunction_Next<MODEL, RESULT, RET>

> {
			

	<T> SHORT_RET 		abs(IFunctionShort<T> getter);
	<T> INTEGER_RET 	abs(IFunctionInteger<T> getter);
	<T> LONG_RET 		abs(IFunctionLong<T> getter);
	<T> BIGDECIMAL_RET 	abs(IFunctionBigDecimal<T> getter);

	<T> SHORT_RET 		absOfShort(ISharedSubOperandsFunction_Short_Named<MODEL, RESULT> sub);
	<T> INTEGER_RET 	absOfInteger(ISharedSubOperandsFunction_Integer_Named<MODEL, RESULT> sub);
	<T> LONG_RET 		absOfLong(ISharedSubOperandsFunction_Long_Named<MODEL, RESULT> sub);
	<T> BIGDECIMAL_RET 	absOfDecimal(ISharedSubOperandsFunction_BigDecimal_Named<MODEL, RESULT> sub);
	

	// sqrt always returns double
	<T> DOUBLE_RET sqrt(IFunctionShort<T> getter);
	<T> DOUBLE_RET sqrt(IFunctionInteger<T> getter);
	<T> DOUBLE_RET sqrt(IFunctionLong<T> getter);
	<T> DOUBLE_RET sqrt(IFunctionBigDecimal<T> getter);

	<T> DOUBLE_RET 	sqrtOf(ISharedSubOperandsFunction_Double_Named<MODEL, RESULT> sub);
	
}
