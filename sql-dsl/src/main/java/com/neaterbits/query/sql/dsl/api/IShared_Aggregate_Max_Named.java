package com.neaterbits.query.sql.dsl.api;

public interface IShared_Aggregate_Max_Named<SHORT_RET, INT_RET, LONG_RET, BIGDECIMAL_RET, DATE_RET> {

    <T> SHORT_RET 		max(IFunctionShort<T> field);
	
    <T> INT_RET 		max(IFunctionInteger<T> field);

    <T> LONG_RET 		max(IFunctionLong<T> field);

    <T> BIGDECIMAL_RET 	max(IFunctionBigDecimal<T> field);

    <T> DATE_RET 		max(IFunctionDate<T> field);

}
