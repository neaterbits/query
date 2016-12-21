package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface Adhoc {

    public static <T> IAdhocNumericTableResult<Void, Short> sum(IFunctionShort<T> field) {
    	return AdhocImpl.adhocImpl.sum(field);
    }
	
    public static <T> IAdhocNumericTableResult<Void, Integer> sum(IFunctionInteger<T> field) {
    	return AdhocImpl.adhocImpl.sum(field);
    }

    public static <T> IAdhocNumericTableResult<Void, Long> sum(IFunctionLong<T> field) {
    	return AdhocImpl.adhocImpl.sum(field);
    }

    public static <T> IAdhocNumericTableResult<Void, BigDecimal> sum(IFunctionBigDecimal<T> field) {
    	return AdhocImpl.adhocImpl.sum(field);
    }
	
}
