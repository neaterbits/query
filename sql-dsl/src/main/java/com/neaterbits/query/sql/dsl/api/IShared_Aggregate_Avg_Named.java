package com.neaterbits.query.sql.dsl.api;

public interface IShared_Aggregate_Avg_Named<SHORT_RET, INT_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET> {

	// Always retuns double, to be consistent with JPA
	// TODO is this a good idea? Shouldn't avg of double return BigDecimal? 
	
    <T> DOUBLE_RET 	avg(IFunctionShort<T> field);
	
    <T> DOUBLE_RET 	avg(IFunctionInteger<T> field);

    <T> DOUBLE_RET 	avg(IFunctionLong<T> field);

    <T> DOUBLE_RET 	avg(IFunctionBigDecimal<T> field);

}
