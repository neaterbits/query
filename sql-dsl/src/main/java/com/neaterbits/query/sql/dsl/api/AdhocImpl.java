package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Function;

final class AdhocImpl implements IAdhoc {
	
	static final AdhocImpl adhocImpl = new AdhocImpl();

	@SuppressWarnings({"unchecked", "rawtypes"})
	private <T, TYPE> IAdhocNumericTableResult<TYPE> createNumeric(Function<T, TYPE> field, AggregateFunction aggregateFunction, NumericType numericType) {
		return (IAdhocNumericTableResult)new AdhocQueryClass(field, aggregateFunction, numericType);
	}
	
	@Override
	public <T> IAdhocNumericTableResult<Short> sum(ShortFunction<T> field) {
		return createNumeric(field, AggregateFunction.SUM, NumericType.SHORT);
	}

	@Override
	public <T> IAdhocNumericTableResult<Integer> sum(IntegerFunction<T> field) {
		return createNumeric(field, AggregateFunction.SUM, NumericType.INTEGER);
	}

	@Override
	public <T> IAdhocNumericTableResult<Long> sum(LongFunction<T> field) {
		return createNumeric(field, AggregateFunction.SUM, NumericType.LONG);
	}

	@Override
	public <T> IAdhocNumericTableResult<BigDecimal> sum(BigDecimalFunction<T> field) {
		return createNumeric(field, AggregateFunction.SUM, NumericType.DECIMAL);
	}
}
