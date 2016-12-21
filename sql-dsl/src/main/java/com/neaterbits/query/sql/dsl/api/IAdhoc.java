package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

interface IAdhoc<MODEL>
 /*extends SumFunction<
	IAdhocNumericTableResult<MODEL, Short>,
	IAdhocNumericTableResult<MODEL, Integer>,
	IAdhocNumericTableResult<MODEL, Long>,
	IAdhocNumericTableResult<MODEL, BigDecimal>> */ {
	
    <T> IAdhocNumericTableResult<MODEL, Short, T> sum(IFunctionShort<T> field);
	
    <T> IAdhocNumericTableResult<MODEL, Integer, T> sum(IFunctionInteger<T> field);

    <T> IAdhocNumericTableResult<MODEL, Long, T> sum(IFunctionLong<T> field);

    <T> IAdhocNumericTableResult<MODEL, BigDecimal, T> sum(IFunctionBigDecimal<T> field);
	

}
