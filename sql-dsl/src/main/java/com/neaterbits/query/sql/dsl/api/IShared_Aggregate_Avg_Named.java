package com.neaterbits.query.sql.dsl.api;

public interface IShared_Aggregate_Avg_Named<SHORT_RET, INT_RET, LONG_RET, BIGDECIMAL_RET> {

    <T> SHORT_RET 		avg(IFunctionShort<T> field);
	
    <T> INT_RET 		avg(IFunctionInteger<T> field);

    <T> LONG_RET 		avg(IFunctionLong<T> field);

    <T> BIGDECIMAL_RET 	avg(IFunctionBigDecimal<T> field);

}
