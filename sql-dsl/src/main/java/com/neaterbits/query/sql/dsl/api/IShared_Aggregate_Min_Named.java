package com.neaterbits.query.sql.dsl.api;

public interface IShared_Aggregate_Min_Named<SHORT_RET, INT_RET, LONG_RET, BIGDECIMAL_RET> {

    <T> SHORT_RET 		min(IFunctionShort<T> field);
	
    <T> INT_RET 		min(IFunctionInteger<T> field);

    <T> LONG_RET 		min(IFunctionLong<T> field);

    <T> BIGDECIMAL_RET 	min(IFunctionBigDecimal<T> field);

}
