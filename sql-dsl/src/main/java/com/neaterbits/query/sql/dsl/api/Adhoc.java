package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface Adhoc {

    public static <T> IAdhocNumericTableResult<Void, Integer, T> sum(IFunctionShort<T> field) {
    	return AdhocImpl.adhocImpl.sum(field);
    }
	
    public static <T> IAdhocNumericTableResult<Void, Integer, T> sum(IFunctionInteger<T> field) {
    	return AdhocImpl.adhocImpl.sum(field);
    }

    public static <T> IAdhocNumericTableResult<Void, Long, T> sum(IFunctionLong<T> field) {
    	return AdhocImpl.adhocImpl.sum(field);
    }

    public static <T> IAdhocNumericTableResult<Void, BigDecimal, T> sum(IFunctionBigDecimal<T> field) {
    	return AdhocImpl.adhocImpl.sum(field);
    }

    /* Max */
    public static <T> IAdhocNumericTableResult<Void, Integer, T> max(IFunctionShort<T> field) {
    	return AdhocImpl.adhocImpl.sum(field);
    }
	
    public static <T> IAdhocNumericTableResult<Void, Integer, T> max(IFunctionInteger<T> field) {
    	return AdhocImpl.adhocImpl.sum(field);
    }

    public static <T> IAdhocNumericTableResult<Void, Long, T> max(IFunctionLong<T> field) {
    	return AdhocImpl.adhocImpl.sum(field);
    }

    public static <T> IAdhocNumericTableResult<Void, BigDecimal, T> max(IFunctionBigDecimal<T> field) {
    	return AdhocImpl.adhocImpl.sum(field);
    }
}
