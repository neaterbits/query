package com.neaterbits.query.sql.dsl.api;


public interface IShared_Aggregate_Sum_Named<BYTE_RET, SHORT_RET, INT_RET, LONG_RET, BIGINTEGER_RET, FLOAT_RET, DOUBLE_RET, BIGDECIMAL_RET> {

    <T> BYTE_RET 		sum(IFunctionByte<T> field);
	
    <T> SHORT_RET 		sum(IFunctionShort<T> field);
	
    <T> INT_RET 		sum(IFunctionInteger<T> field);

    <T> LONG_RET 		sum(IFunctionLong<T> field);

    <T> BIGINTEGER_RET 	sum(IFunctionBigInteger<T> field);

    <T> FLOAT_RET 		sum(IFunctionFloat<T> field);

    <T> DOUBLE_RET 		sum(IFunctionDouble<T> field);

    <T> BIGDECIMAL_RET 	sum(IFunctionBigDecimal<T> field);

}
