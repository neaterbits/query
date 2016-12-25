package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;
import java.util.function.Function;

public class AdhocQueryClassSet<MODEL>
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

	@Override
	public <T> ISharedClauseConditionAll<MODEL, Set<Object>, Integer, IAdhocOrClauses<MODEL, Set<Object>>> or(
			IFunctionInteger<T> getter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> ISharedClauseConditionAll<MODEL, Set<Object>, Long, IAdhocOrClauses<MODEL, Set<Object>>> or(
			IFunctionLong<T> getter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> ISharedClauseComparableStringAll<MODEL, Set<Object>, IAdhocOrClauses<MODEL, Set<Object>>> or(
			StringFunction<T> getter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> ISharedClauseConditionTable<MODEL, Set<Object>, Integer, IAdhocAndClauses<MODEL, Set<Object>>> and(
			IFunctionInteger<T> getter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> ISharedClauseConditionTable<MODEL, Set<Object>, Long, IAdhocAndClauses<MODEL, Set<Object>>> and(
			IFunctionLong<T> getter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> ISharedClauseComparableStringAll<MODEL, Set<Object>, IAdhocAndClauses<MODEL, Set<Object>>> and(
			StringFunction<T> getter) {
		// TODO Auto-generated method stub
		return null;
	}
}
