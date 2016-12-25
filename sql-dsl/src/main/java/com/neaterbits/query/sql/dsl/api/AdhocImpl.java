package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

final class AdhocImpl<MODEL> implements IAdhoc<MODEL> {
	
	static final AdhocImpl<Void> adhocImpl = new AdhocImpl<Void>();

	@SuppressWarnings({"unchecked", "rawtypes"})
	private <T, INPUT_TYPE, OUTPUT_TYPE>
				IAdhocNumericTableResult<MODEL, OUTPUT_TYPE, T> createNumeric(

						Function<T, INPUT_TYPE> field,
						EAggregateFunction aggregateFunction,
						ENumericType hinputNnumericType,
						ENumericType outputNnumericType) {

		return (IAdhocNumericTableResult)new AdhocQueryClassAggregate(field, aggregateFunction, hinputNnumericType, outputNnumericType);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private <T, INPUT_TYPE, OUTPUT_TYPE>
				IAdhocNumericInstanceResult<MODEL, T> createNumericInstance(

						Function<T, INPUT_TYPE> field,
						EAggregateFunction aggregateFunction,
						ENumericType inputNnumericType,
						ENumericType outputNnumericType) {

		return (IAdhocNumericInstanceResult)new AdhocQueryClassAggregate(field, aggregateFunction, inputNnumericType, outputNnumericType);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private <T, COLL extends List<T>, INPUT_TYPE, OUTPUT_TYPE>
				IAdhocWhereOrJoinList<MODEL, T, COLL> createCollectionInstanceList(Collection<?> coll) {

		return (IAdhocWhereOrJoinList)new AdhocQueryClassList(coll);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private <T, COLL extends Set<T>, INPUT_TYPE, OUTPUT_TYPE>
				IAdhocWhereOrJoinSet<MODEL, T, COLL> createCollectionInstanceSet(Collection<?> coll) {

		return (IAdhocWhereOrJoinSet)new AdhocQueryClassSet(coll);
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

	/* MaxInstance */
	@Override
	public <T> IAdhocNumericInstanceResult<MODEL, T> maxInstance(IFunctionShort<T> field) {
		return createNumericInstance(field, EAggregateFunction.MAX_INSTANCE, ENumericType.SHORT, ENumericType.INSTANCE);
	}

	@Override
	public <T> IAdhocNumericInstanceResult<MODEL, T> maxInstance(IFunctionInteger<T> field) {
		return createNumericInstance(field, EAggregateFunction.MAX_INSTANCE, ENumericType.INTEGER, ENumericType.INSTANCE);
	}

	@Override
	public <T> IAdhocNumericInstanceResult<MODEL, T> maxInstance(IFunctionLong<T> field) {
		return createNumericInstance(field, EAggregateFunction.MAX_INSTANCE, ENumericType.LONG, ENumericType.INSTANCE);
	}

	@Override
	public <T> IAdhocNumericInstanceResult<MODEL, T> maxInstance(IFunctionBigDecimal<T> field) {
		return createNumericInstance(field, EAggregateFunction.MAX_INSTANCE, ENumericType.DECIMAL, ENumericType.INSTANCE);
	}
	
	/* List */
	@Override
	public <T> IAdhocWhereOrJoinList<MODEL, T, List<T>> list(Collection<T> coll) {
		return createCollectionInstanceList(coll);
	}

	/* Set */
	@Override
	public <T> IAdhocWhereOrJoinSet<MODEL, T, Set<T>> set(Collection<T> coll) {
		return createCollectionInstanceSet(coll);
	}
}
