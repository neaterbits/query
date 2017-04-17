package com.neaterbits.query.sql.dsl.api;

public interface IShared_Aggregate_Count_Named<BYTE_RET, SHORT_RET, INT_RET, LONG_RET, BIGINTEGER_RET, FLOAT_RET, DOUBLE_RET, BIGDECIMAL_RET> {

    <T> BYTE_RET 		count(IFunctionByte<T> field);
	
    <T> SHORT_RET 		count(IFunctionShort<T> field);
	
    <T> INT_RET 		count(IFunctionInteger<T> field);

    <T> LONG_RET 		count(IFunctionLong<T> field);

    <T> BIGINTEGER_RET 	count(IFunctionBigInteger<T> field);

    <T> FLOAT_RET 		count(IFunctionFloat<T> field);

    <T> DOUBLE_RET 		count(IFunctionDouble<T> field);

    <T> BIGDECIMAL_RET 	count(IFunctionBigDecimal<T> field);

}
