package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface Adhoc {

    public static <T> IAdhocNumericTableResult<Short> sum(IFunctionShort<T> field) {
    	return AdhocImpl.adhocImpl.sum(field);
    }
	
    public static <T> IAdhocNumericTableResult<Integer> sum(IFunctionInteger<T> field) {
    	return AdhocImpl.adhocImpl.sum(field);
    }

    public static <T> IAdhocNumericTableResult<Long> sum(IFunctionLong<T> field) {
    	return AdhocImpl.adhocImpl.sum(field);
    }

    public static <T> IAdhocNumericTableResult<BigDecimal> sum(IFunctionBigDecimal<T> field) {
    	return AdhocImpl.adhocImpl.sum(field);
    }
	
}
