package com.neaterbits.query.sql.dsl.api;


public interface IShared_Aggregate_Sum_Named<SHORT_RET, INT_RET, LONG_RET, BIGDECIMAL_RET> {

    <T> SHORT_RET sum(IFunctionShort<T> field);
	
    <T> INT_RET sum(IFunctionInteger<T> field);

    <T> LONG_RET sum(IFunctionLong<T> field);

    <T> BIGDECIMAL_RET sum(IFunctionBigDecimal<T> field);
}
