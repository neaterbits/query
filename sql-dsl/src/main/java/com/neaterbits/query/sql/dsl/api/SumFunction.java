package com.neaterbits.query.sql.dsl.api;


public interface SumFunction<SHORT_RET, INT_RET, LONG_RET, BIGDECIMAL_RET> {

    <T> SHORT_RET sum(ShortFunction<T> field);
	
    <T> INT_RET sum(IntegerFunction<T> field);

    <T> LONG_RET sum(LongFunction<T> field);

    <T> BIGDECIMAL_RET sum(BigDecimalFunction<T> field);
}
