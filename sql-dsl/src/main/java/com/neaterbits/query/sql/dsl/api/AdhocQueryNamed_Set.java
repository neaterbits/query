package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

final class AdhocQueryNamed_Set<MODEL>
		extends AdhocQueryNamed_Collection<MODEL, Set<Object>>

		implements IAdhocWhereOrJoinSet<MODEL, Object, Set<Object>>,
				   IAdhocEndClauseSet<MODEL, Object, Set<Object>> {

	AdhocQueryNamed_Set(Collection<?> coll) {
		super(ECollectionType.SET, coll);
	}

	@Override
	AdhocConditions<MODEL, Set<Object>, ?> createConditions(int level) {
		return new AdhocConditionsSet<>(this, level);
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
	public ISharedCondition_Comparable_Common_Value<MODEL, Set<Object>, Integer, IAdhocAndOrLogicalClausesSet<MODEL, Object, Set<Object>>> where(IFunctionInteger<Object> func) {
		return addComparativeWhere(func);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E extends Enum<E>> ISharedCondition_Equality_Value<MODEL, Set<Object>, E, IAdhocAndOrLogicalClausesSet<MODEL, Object, Set<Object>>> where(IFunctionEnum<Object, E> func) {
		return addConditionWhere(func);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ISharedCondition_Comparable_Common_Value<MODEL, Set<Object>, BigDecimal, IAdhocAndOrLogicalClausesSet<MODEL, Object, Set<Object>>> where(IFunctionBigDecimal<Object> func) {
		return addComparativeWhere(func);
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ISharedCondition_Comparable_String_Value<MODEL, Set<Object>, IAdhocAndOrLogicalClausesSet<MODEL, Object, Set<Object>>> where(StringFunction<Object> func) {

		return (ISharedCondition_Comparable_String_Value)addWhere(func);
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
