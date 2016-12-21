package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Function;

final class AdhocImpl<MODEL> implements IAdhoc<MODEL> {
	
	static final AdhocImpl<Void> adhocImpl = new AdhocImpl<Void>();

	@SuppressWarnings({"unchecked", "rawtypes"})
	private <T, TYPE> IAdhocNumericTableResult<MODEL, TYPE, T> createNumeric(Function<T, TYPE> field, EAggregateFunction aggregateFunction, ENumericType numericType) {
		return (IAdhocNumericTableResult)new AdhocQueryClass(field, aggregateFunction, numericType);
	}
	
	@Override
	public <T> IAdhocNumericTableResult<MODEL, Short, T> sum(IFunctionShort<T> field) {
		return createNumeric(field, EAggregateFunction.SUM, ENumericType.SHORT);
	}

	@Override
	public <T> IAdhocNumericTableResult<MODEL, Integer, T> sum(IFunctionInteger<T> field) {
		return createNumeric(field, EAggregateFunction.SUM, ENumericType.INTEGER);
	}

	@Override
	public <T> IAdhocNumericTableResult<MODEL, Long, T> sum(IFunctionLong<T> field) {
		return createNumeric(field, EAggregateFunction.SUM, ENumericType.LONG);
	}

	@Override
	public <T> IAdhocNumericTableResult<MODEL, BigDecimal, T> sum(IFunctionBigDecimal<T> field) {
		return createNumeric(field, EAggregateFunction.SUM, ENumericType.DECIMAL);
	}
}
