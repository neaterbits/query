package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
    public static <T> IAdhocNumericTableResult<Void, Short, T> max(IFunctionShort<T> field) {
    	return AdhocImpl.adhocImpl.max(field);
    }
	
    public static <T> IAdhocNumericTableResult<Void, Integer, T> max(IFunctionInteger<T> field) {
    	return AdhocImpl.adhocImpl.max(field);
    }

    public static <T> IAdhocNumericTableResult<Void, Long, T> max(IFunctionLong<T> field) {
    	return AdhocImpl.adhocImpl.max(field);
    }

    public static <T> IAdhocNumericTableResult<Void, BigDecimal, T> max(IFunctionBigDecimal<T> field) {
    	return AdhocImpl.adhocImpl.max(field);
    }

    /* MaxInstance */
    public static <T> IAdhocNumericInstanceResult<Void, T> maxInstance(IFunctionShort<T> field) {
    	return AdhocImpl.adhocImpl.maxInstance(field);
    }
	
    public static <T> IAdhocNumericInstanceResult<Void, T> maxInstance(IFunctionInteger<T> field) {
    	return AdhocImpl.adhocImpl.maxInstance(field);
    }

    public static <T> IAdhocNumericInstanceResult<Void, T> maxInstance(IFunctionLong<T> field) {
    	return AdhocImpl.adhocImpl.maxInstance(field);
    }

    public static <T> IAdhocNumericInstanceResult<Void, T> maxInstance(IFunctionBigDecimal<T> field) {
    	return AdhocImpl.adhocImpl.maxInstance(field);
    }

    /* List */
	public static <T> IAdhocWhereOrJoinList<Void, T, List<T>> list(Collection<T> collection) {
    	return AdhocImpl.adhocImpl.list(collection);
	}

    /* Set */
	public static <T> IAdhocWhereOrJoinSet<Void, T, Set<T>> set(Collection<T> collection) {
    	return AdhocImpl.adhocImpl.set(collection);
	}
}
