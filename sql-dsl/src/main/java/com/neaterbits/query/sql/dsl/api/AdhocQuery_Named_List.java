package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

final class AdhocQuery_Named_List<MODEL>

		extends AdhocQuery_Named_Collection<MODEL, List<Object>>

		implements IAdhocWhereOrJoinList<MODEL, Object, List<Object>>,
		IAdhocEnd_List<MODEL, Object, List<Object>> {

	AdhocQuery_Named_List(Collection<?> coll) {
		super(ECollectionType.LIST, coll);
	}
	
	@Override
	public final <L extends List<Object>> L as(Function<List<Object>, L> function) {
		final List<Object> ret = (List<Object>)get();
		
		return function.apply(ret);
	}
	

	@Override
	AdhocConditions<MODEL, List<Object>, ?> createConditions(int level) {
		return new AdhocConditions_List<>(this, level);
	}

	/**************************************************************************
	** IAdhocWhereOrJoinList
	**************************************************************************/
	
	
	@SuppressWarnings("unchecked")
	@Override
	public ISharedCondition_Comparable_Common_Value<MODEL, List<Object>, Integer, IAdhocLogical_And_Or_List<MODEL, Object, List<Object>>> where(IFunctionInteger<Object> func) {
		return addComparativeWhere(func);
	}


	@SuppressWarnings("unchecked")
	@Override
	public <E extends Enum<E>> ISharedCondition_Equality_Value<MODEL, List<Object>, E, IAdhocLogical_And_Or_List<MODEL, Object, List<Object>>> where(IFunctionEnum<Object, E> func) {
		return addConditionWhere(func);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ISharedCondition_Comparable_Common_Value<MODEL, List<Object>, BigDecimal, IAdhocLogical_And_Or_List<MODEL, Object, List<Object>>> where(IFunctionBigDecimal<Object> func) {
		return addComparativeWhere(func);
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public ISharedCondition_Comparable_String_Value<MODEL, List<Object>, IAdhocLogical_And_Or_List<MODEL, Object, List<Object>>>
					where(StringFunction<Object> func) {

		return (ISharedCondition_Comparable_String_Value)addWhere(func);
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


