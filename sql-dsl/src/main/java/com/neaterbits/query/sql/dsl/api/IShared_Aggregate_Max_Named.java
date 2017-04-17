package com.neaterbits.query.sql.dsl.api;

public interface IShared_Aggregate_Max_Named<BYTE_RET, SHORT_RET, INT_RET, LONG_RET, BIGINTEGER_RET, FLOAT_RET, DOUBLE_RET, BIGDECIMAL_RET, DATE_RET> {

	<T> BYTE_RET 		max(IFunctionByte<T> field);

    <T> SHORT_RET 		max(IFunctionShort<T> field);
	
    <T> INT_RET 		max(IFunctionInteger<T> field);

    <T> LONG_RET 		max(IFunctionLong<T> field);

    <T> BIGINTEGER_RET 	max(IFunctionBigInteger<T> field);

    <T> FLOAT_RET 		max(IFunctionFloat<T> field);

    <T> DOUBLE_RET 		max(IFunctionDouble<T> field);

    <T> BIGDECIMAL_RET 	max(IFunctionBigDecimal<T> field);

    <T> DATE_RET 		max(IFunctionDate<T> field);

}
