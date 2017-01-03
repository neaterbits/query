package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

final class AdhocQueryNamedList<MODEL>

		extends AdhocQueryNamedCollection<MODEL, List<Object>>

		implements IAdhocWhereOrJoinList<MODEL, Object, List<Object>>,
		IAdhocEndClauseList<MODEL, Object, List<Object>> {

	AdhocQueryNamedList(Collection<?> coll) {
		super(ECollectionType.LIST, coll);
	}
	
	@Override
	public final <L extends List<Object>> L as(Function<List<Object>, L> function) {
		final List<Object> ret = (List<Object>)get();
		
		return function.apply(ret);
	}
	

	@Override
	AdhocConditions<MODEL, List<Object>, ?> createConditions(int level) {
		return new AdhocConditionsList<>(this, level);
	}

	/**************************************************************************
	** IAdhocWhereOrJoinList
	**************************************************************************/
	
	
	@SuppressWarnings("unchecked")
	@Override
	public ISharedClauseComparableCommonValue<MODEL, List<Object>, Integer, IAdhocAndOrLogicalClausesList<MODEL, Object, List<Object>>> where(IFunctionInteger<Object> func) {
		return addComparativeWhere(func);
	}


	@SuppressWarnings("unchecked")
	@Override
	public <E extends Enum<E>> ISharedClauseConditionValue<MODEL, List<Object>, E, IAdhocAndOrLogicalClausesList<MODEL, Object, List<Object>>> where(IFunctionEnum<Object, E> func) {
		return addConditionWhere(func);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ISharedClauseComparableCommonValue<MODEL, List<Object>, BigDecimal, IAdhocAndOrLogicalClausesList<MODEL, Object, List<Object>>> where(IFunctionBigDecimal<Object> func) {
		return addComparativeWhere(func);
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public ISharedClauseComparableStringValue<MODEL, List<Object>, IAdhocAndOrLogicalClausesList<MODEL, Object, List<Object>>>
					where(StringFunction<Object> func) {

		return (ISharedClauseComparableStringValue)addWhere(func);
	}
	
	

	/**************************************************************************
	 ** IAdhocJoin
	 **************************************************************************/
	
	@Override
	public final <JOIN_TO> IAdhocWhereOrJoinList<MODEL, Object, List<Object>> innerJoin(
					Collection<JOIN_TO> joinTo,
					Consumer<IAdhocJoinSub<MODEL, List<Object>, Object, JOIN_TO>> consumer) {
		
		compileInnerJoin(joinTo, consumer);
		
		return this;
	}

	@Override
	public final <JOIN_TO> IAdhocWhereOrJoinList<MODEL, Object, List<Object>> leftJoin(
					Collection<JOIN_TO> joinTo,
					Consumer<IAdhocJoinSub<MODEL, List<Object>, Object, JOIN_TO>> consumer) {

		compileLeftJoin(joinTo, consumer);
		
		return this;
	}
}


