package com.neaterbits.query.sql.dsl.api;


public interface SumFunction<SHORT_RET, INT_RET, LONG_RET, BIGDECIMAL_RET> {

    <T> SHORT_RET sum(IFunctionShort<T> field);
	
    <T> INT_RET sum(IFunctionInteger<T> field);

    <T> LONG_RET sum(IFunctionLong<T> field);

    <T> BIGDECIMAL_RET sum(BigDecimalFunction<T> field);
}
