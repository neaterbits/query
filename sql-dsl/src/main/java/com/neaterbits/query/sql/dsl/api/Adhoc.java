package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface Adhoc {

    public static <T> IAdhocNumericTableResult<Short> sum(ShortFunction<T> field) {
    	return AdhocImpl.adhocImpl.sum(field);
    }
	
    public static <T> IAdhocNumericTableResult<Integer> sum(IntegerFunction<T> field) {
    	return AdhocImpl.adhocImpl.sum(field);
    }

    public static <T> IAdhocNumericTableResult<Long> sum(LongFunction<T> field) {
    	return AdhocImpl.adhocImpl.sum(field);
    }

    public static <T> IAdhocNumericTableResult<BigDecimal> sum(BigDecimalFunction<T> field) {
    	return AdhocImpl.adhocImpl.sum(field);
    }
	
}
