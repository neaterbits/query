package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

interface IAdhoc<MODEL>
 /*extends SumFunction<
	IAdhocNumericTableResult<MODEL, Short>,
	IAdhocNumericTableResult<MODEL, Integer>,
	IAdhocNumericTableResult<MODEL, Long>,
	IAdhocNumericTableResult<MODEL, BigDecimal>> */ {
	
    <T> IAdhocNumericNamedResult<MODEL, Integer, T> 	sum(IFunctionShort<T> field);    // Sum short to integer    (TODO: throw exception on overflow)
	
    <T> IAdhocNumericNamedResult<MODEL, Integer, T> 	sum(IFunctionInteger<T> field);  // Sum integers to integer (TODO: throw exception on overflow) 
    
    <T> IAdhocNumericNamedResult<MODEL, Long, T> 		sum(IFunctionLong<T> field);   	 // Sum integers to integer (TODO: throw exception on overflow)			

    <T> IAdhocNumericNamedResult<MODEL, BigDecimal, T> 	sum(IFunctionBigDecimal<T> field);

    /* Max */
    <T> IAdhocNumericNamedResult<MODEL, Short, T> 		max(IFunctionShort<T> field);
	
    <T> IAdhocNumericNamedResult<MODEL, Integer, T> 	max(IFunctionInteger<T> field); 
    
    <T> IAdhocNumericNamedResult<MODEL, Long, T> 		max(IFunctionLong<T> field);			

    <T> IAdhocNumericNamedResult<MODEL, BigDecimal, T> 	max(IFunctionBigDecimal<T> field);

    /* MaxInstance */
    <T> IAdhocNumericInstanceResult<MODEL, T> 	maxInstance(IFunctionShort<T> field);
	
    <T> IAdhocNumericInstanceResult<MODEL, T> 	maxInstance(IFunctionInteger<T> field); 
    
    <T> IAdhocNumericInstanceResult<MODEL, T> 	maxInstance(IFunctionLong<T> field);			

    <T> IAdhocNumericInstanceResult<MODEL, T> 	maxInstance(IFunctionBigDecimal<T> field);
    
    /* Collection results */
    <T> IAdhocWhereOrJoinList<MODEL, T, List<T>> list(Collection<T> coll);

    <T> IAdhocWhereOrJoinSet<MODEL, T, Set<T>> set(Collection<T> coll);
}
