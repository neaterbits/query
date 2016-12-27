package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

final class AdhocQueryClassSet<MODEL>
		extends AdhocQueryClassCollection<MODEL, Set<Object>>

		implements IAdhocWhereOrJoinSet<MODEL, Object, Set<Object>>,
				   IAdhocAndOrLogicalClausesSet<MODEL, Object, Set<Object>> {

	AdhocQueryClassSet(Collection<?> coll) {
		super(ECollectionType.SET, coll);
	}

	@Override
	public final <L extends Set<Object>> L as(Function<Collection<Object>, L> function) {
		final Collection<Object> ret = (Collection<Object>) get();

		return function.apply(ret);
	}


	/**************************************************************************
	 ** IAdhocWhereOrJoinList
	 **************************************************************************/

	@SuppressWarnings("unchecked")
	@Override
	public ISharedClauseComparableCommonValue<MODEL, Set<Object>, Integer, IAdhocAndOrLogicalClausesSet<MODEL, Object, Set<Object>>> where(IFunctionInteger<Object> func) {
		return addComparativeWhere(func);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E extends Enum<E>> ISharedClauseConditionValue<MODEL, Set<Object>, E, IAdhocAndOrLogicalClausesSet<MODEL, Object, Set<Object>>> where(IFunctionEnum<Object, E> func) {
		return addConditionWhere(func);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ISharedClauseComparableCommonValue<MODEL, Set<Object>, BigDecimal, IAdhocAndOrLogicalClausesSet<MODEL, Object, Set<Object>>> where(IFunctionBigDecimal<Object> func) {
		return addComparativeWhere(func);
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ISharedClauseComparableStringValue<MODEL, Set<Object>, IAdhocAndOrLogicalClausesSet<MODEL, Object, Set<Object>>> where(StringFunction<Object> func) {
		addCondition(null, func);

		return (ISharedClauseComparableStringValue) this;
	}


	/**************************************************************************
	 ** IAdhocJoin
	 **************************************************************************/
	
	@Override
	public final <JOIN_TO> IAdhocWhereOrJoinSet<MODEL, Object, Set<Object>> innerJoin(
			Collection<JOIN_TO> joinTo,
			Consumer<IAdhocJoinSub<MODEL, Set<Object>, Object, JOIN_TO>> consumer) {
		
		compileInnerJoin(joinTo, consumer);
		
		return this;
	}

	@Override
	public final <JOIN_TO> IAdhocWhereOrJoinSet<MODEL, Object, Set<Object>> leftJoin(Collection<JOIN_TO> joinTo,
			Consumer<IAdhocJoinSub<MODEL, Set<Object>, Object, JOIN_TO>> consumer) {

		compileLeftJoin(joinTo, consumer);

		return null;
	}

	
}
