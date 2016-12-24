package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Function;

final class AdhocImpl<MODEL> implements IAdhoc<MODEL> {
	
	static final AdhocImpl<Void> adhocImpl = new AdhocImpl<Void>();

	@SuppressWarnings({"unchecked", "rawtypes"})
	private <T, INPUT_TYPE, OUTPUT_TYPE>
				IAdhocNumericTableResult<MODEL, OUTPUT_TYPE, T> createNumeric(Function<T, INPUT_TYPE> field, EAggregateFunction aggregateFunction, ENumericType inputNnumericType, ENumericType outputNnumericType) {
		return (IAdhocNumericTableResult)new AdhocQueryClass(field, aggregateFunction, inputNnumericType, outputNnumericType);
	}
	
	@Override
	public <T> IAdhocNumericTableResult<MODEL, Integer, T> sum(IFunctionShort<T> field) {
		return createNumeric(field, EAggregateFunction.SUM, ENumericType.SHORT, ENumericType.INTEGER);
	}

	@Override
	public <T> IAdhocNumericTableResult<MODEL, Integer, T> sum(IFunctionInteger<T> field) {
		return createNumeric(field, EAggregateFunction.SUM, ENumericType.INTEGER, ENumericType.INTEGER);
	}

	@Override
	public <T> IAdhocNumericTableResult<MODEL, Long, T> sum(IFunctionLong<T> field) {
		return createNumeric(field, EAggregateFunction.SUM, ENumericType.LONG, ENumericType.LONG);
	}

	@Override
	public <T> IAdhocNumericTableResult<MODEL, BigDecimal, T> sum(IFunctionBigDecimal<T> field) {
		return createNumeric(field, EAggregateFunction.SUM, ENumericType.DECIMAL, ENumericType.DECIMAL);
	}

	/* Max */
	@Override
	public <T> IAdhocNumericTableResult<MODEL, Short, T> max(IFunctionShort<T> field) {
		return createNumeric(field, EAggregateFunction.MAX, ENumericType.SHORT, ENumericType.SHORT);
	}

	@Override
	public <T> IAdhocNumericTableResult<MODEL, Integer, T> max(IFunctionInteger<T> field) {
		return createNumeric(field, EAggregateFunction.MAX, ENumericType.INTEGER, ENumericType.INTEGER);
	}

	@Override
	public <T> IAdhocNumericTableResult<MODEL, Long, T> max(IFunctionLong<T> field) {
		return createNumeric(field, EAggregateFunction.MAX, ENumericType.LONG, ENumericType.LONG);
	}

	@Override
	public <T> IAdhocNumericTableResult<MODEL, BigDecimal, T> max(IFunctionBigDecimal<T> field) {
		return createNumeric(field, EAggregateFunction.MAX, ENumericType.DECIMAL, ENumericType.DECIMAL);
	}
}
