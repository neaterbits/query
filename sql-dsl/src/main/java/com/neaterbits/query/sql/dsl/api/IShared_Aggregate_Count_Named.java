package com.neaterbits.query.sql.dsl.api;

public interface IShared_Aggregate_Count_Named<SHORT_RET, INT_RET, LONG_RET, BIGDECIMAL_RET> {

    <T> SHORT_RET 		count(IFunctionShort<T> field);
	
    <T> INT_RET 		count(IFunctionInteger<T> field);

    <T> LONG_RET 		count(IFunctionLong<T> field);

    <T> BIGDECIMAL_RET 	count(IFunctionBigDecimal<T> field);

}
