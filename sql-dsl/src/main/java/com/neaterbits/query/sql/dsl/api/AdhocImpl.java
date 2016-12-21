package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Function;

final class AdhocImpl implements IAdhoc {
	
	static final AdhocImpl adhocImpl = new AdhocImpl();

	@SuppressWarnings({"unchecked", "rawtypes"})
	private <T, TYPE> IAdhocNumericTableResult<TYPE> createNumeric(Function<T, TYPE> field, EAggregateFunction aggregateFunction, ENumericType numericType) {
		return (IAdhocNumericTableResult)new AdhocQueryClass(field, aggregateFunction, numericType);
	}
	
	@Override
	public <T> IAdhocNumericTableResult<Short> sum(IFunctionShort<T> field) {
		return createNumeric(field, EAggregateFunction.SUM, ENumericType.SHORT);
	}

	@Override
	public <T> IAdhocNumericTableResult<Integer> sum(IFunctionInteger<T> field) {
		return createNumeric(field, EAggregateFunction.SUM, ENumericType.INTEGER);
	}

	@Override
	public <T> IAdhocNumericTableResult<Long> sum(IFunctionLong<T> field) {
		return createNumeric(field, EAggregateFunction.SUM, ENumericType.LONG);
	}

	@Override
	public <T> IAdhocNumericTableResult<BigDecimal> sum(IFunctionBigDecimal<T> field) {
		return createNumeric(field, EAggregateFunction.SUM, ENumericType.DECIMAL);
	}
}
