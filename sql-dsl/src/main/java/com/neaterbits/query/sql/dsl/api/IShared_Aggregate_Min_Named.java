package com.neaterbits.query.sql.dsl.api;

public interface IShared_Aggregate_Min_Named<BYTE_RET, SHORT_RET, INT_RET, LONG_RET, BIGINTEGER_RET, FLOAT_RET, DOUBLE_RET, BIGDECIMAL_RET, DATE_RET> {

	<T> BYTE_RET 		min(IFunctionByte<T> field);

    <T> SHORT_RET 		min(IFunctionShort<T> field);
	
    <T> INT_RET 		min(IFunctionInteger<T> field);

    <T> LONG_RET 		min(IFunctionLong<T> field);

    <T> BIGINTEGER_RET 	min(IFunctionBigInteger<T> field);

    <T> FLOAT_RET 		min(IFunctionFloat<T> field);

    <T> DOUBLE_RET 		min(IFunctionDouble<T> field);
    
    <T> BIGDECIMAL_RET 	min(IFunctionBigDecimal<T> field);

    <T> DATE_RET 		min(IFunctionDate<T> field);
}
