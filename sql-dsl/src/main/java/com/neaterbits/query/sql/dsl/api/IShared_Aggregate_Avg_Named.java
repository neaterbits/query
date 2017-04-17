package com.neaterbits.query.sql.dsl.api;

public interface IShared_Aggregate_Avg_Named<BYTE_RET, SHORT_RET, INT_RET, LONG_RET, BIGINTEGER_RET, FLOAT_RET, DOUBLE_RET, BIGDECIMAL_RET> {

	// Always retunrs double, to be consistent with JPA
	// TODO is this a good idea? Shouldn't avg of double return BigDecimal? 
	
    <T> DOUBLE_RET 	avg(IFunctionByte<T> field);
    
    <T> DOUBLE_RET 	avg(IFunctionShort<T> field);
	
    <T> DOUBLE_RET 	avg(IFunctionInteger<T> field);

    <T> DOUBLE_RET 	avg(IFunctionLong<T> field);

    <T> DOUBLE_RET 	avg(IFunctionBigInteger<T> field);
    
    <T> DOUBLE_RET 	avg(IFunctionFloat<T> field);
    
    <T> DOUBLE_RET 	avg(IFunctionDouble<T> field);

    <T> DOUBLE_RET 	avg(IFunctionBigDecimal<T> field);

}
