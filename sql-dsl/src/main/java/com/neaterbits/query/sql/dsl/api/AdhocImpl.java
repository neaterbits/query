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
				IAdhocNumericNamedResult<MODEL, OUTPUT_TYPE, T> createNumeric(

						Function<T, INPUT_TYPE> field,
						EAggregateFunction aggregateFunction,
						EAggregateType hinputNnumericType,
						EAggregateType outputNnumericType) {

		return (IAdhocNumericNamedResult)new AdhocQuery_Named_Aggregate(field, aggregateFunction, hinputNnumericType, outputNnumericType);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private <T, INPUT_TYPE, OUTPUT_TYPE>
				IAdhocNumericInstanceResult<MODEL, T> createNumericInstance(

						Function<T, INPUT_TYPE> field,
						EAggregateFunction aggregateFunction,
						EAggregateType inputNnumericType,
						EAggregateType outputNnumericType) {

		return (IAdhocNumericInstanceResult)new AdhocQuery_Named_Aggregate(field, aggregateFunction, inputNnumericType, outputNnumericType);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private <T, COLL extends List<T>, INPUT_TYPE, OUTPUT_TYPE>
				IAdhocWhereOrJoinList<MODEL, T, COLL> createCollectionInstanceList(Collection<?> coll) {

		return (IAdhocWhereOrJoinList)new AdhocQuery_Named_List(coll);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private <T, COLL extends Set<T>, INPUT_TYPE, OUTPUT_TYPE>
				IAdhocWhereOrJoinSet<MODEL, T, COLL> createCollectionInstanceSet(Collection<?> coll) {

		return (IAdhocWhereOrJoinSet)new AdhocQuery_Named_Set(coll);
	}
				
	@Override
	public <T> IAdhocNumericNamedResult<MODEL, Integer, T> sum(IFunctionShort<T> field) {
		return createNumeric(field, EAggregateFunction.SUM, EAggregateType.SHORT, EAggregateType.INTEGER);
	}

	@Override
	public <T> IAdhocNumericNamedResult<MODEL, Integer, T> sum(IFunctionInteger<T> field) {
		return createNumeric(field, EAggregateFunction.SUM, EAggregateType.INTEGER, EAggregateType.INTEGER);
	}

	@Override
	public <T> IAdhocNumericNamedResult<MODEL, Long, T> sum(IFunctionLong<T> field) {
		return createNumeric(field, EAggregateFunction.SUM, EAggregateType.LONG, EAggregateType.LONG);
	}

	@Override
	public <T> IAdhocNumericNamedResult<MODEL, BigDecimal, T> sum(IFunctionBigDecimal<T> field) {
		return createNumeric(field, EAggregateFunction.SUM, EAggregateType.DECIMAL, EAggregateType.DECIMAL);
	}

	/* Max */
	@Override
	public <T> IAdhocNumericNamedResult<MODEL, Short, T> max(IFunctionShort<T> field) {
		return createNumeric(field, EAggregateFunction.MAX, EAggregateType.SHORT, EAggregateType.SHORT);
	}

	@Override
	public <T> IAdhocNumericNamedResult<MODEL, Integer, T> max(IFunctionInteger<T> field) {
		return createNumeric(field, EAggregateFunction.MAX, EAggregateType.INTEGER, EAggregateType.INTEGER);
	}

	@Override
	public <T> IAdhocNumericNamedResult<MODEL, Long, T> max(IFunctionLong<T> field) {
		return createNumeric(field, EAggregateFunction.MAX, EAggregateType.LONG, EAggregateType.LONG);
	}

	@Override
	public <T> IAdhocNumericNamedResult<MODEL, BigDecimal, T> max(IFunctionBigDecimal<T> field) {
		return createNumeric(field, EAggregateFunction.MAX, EAggregateType.DECIMAL, EAggregateType.DECIMAL);
	}

	/* MaxInstance */
	@Override
	public <T> IAdhocNumericInstanceResult<MODEL, T> maxInstance(IFunctionShort<T> field) {
		return createNumericInstance(field, EAggregateFunction.MAX_INSTANCE, EAggregateType.SHORT, EAggregateType.INSTANCE);
	}

	@Override
	public <T> IAdhocNumericInstanceResult<MODEL, T> maxInstance(IFunctionInteger<T> field) {
		return createNumericInstance(field, EAggregateFunction.MAX_INSTANCE, EAggregateType.INTEGER, EAggregateType.INSTANCE);
	}

	@Override
	public <T> IAdhocNumericInstanceResult<MODEL, T> maxInstance(IFunctionLong<T> field) {
		return createNumericInstance(field, EAggregateFunction.MAX_INSTANCE, EAggregateType.LONG, EAggregateType.INSTANCE);
	}

	@Override
	public <T> IAdhocNumericInstanceResult<MODEL, T> maxInstance(IFunctionBigDecimal<T> field) {
		return createNumericInstance(field, EAggregateFunction.MAX_INSTANCE, EAggregateType.DECIMAL, EAggregateType.INSTANCE);
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
