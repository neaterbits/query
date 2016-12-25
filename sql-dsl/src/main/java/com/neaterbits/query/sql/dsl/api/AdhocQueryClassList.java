package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

final class AdhocQueryClassList<MODEL>

		extends AdhocQueryClass<MODEL, List<Object>>

		implements IAdhocWhereOrJoinList<MODEL, Object, List<Object>>,
				   IAdhocAndOrLogicalClausesList<MODEL, Object, List<Object>> {

	AdhocQueryClassList(ECollectionType collectionType, Collection<?> coll) {
		super(collectionType, coll);
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public final <L extends List<Object>> L as(Function<List<Object>, L> function) {
		final List<Object> ret = (List<Object>)get();
		
		return function.apply(ret);
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
	public ISharedClauseComparableStringValue<MODEL, List<Object>, IAdhocAndOrLogicalClausesList<MODEL, Object, List<Object>>> where(StringFunction<Object> func) {
		addCondition(null, func);
		
		return (ISharedClauseComparableStringValue)this;
	}



	@Override
	public <T> ISharedClauseConditionAll<MODEL, List<Object>, Integer, IAdhocOrClauses<MODEL, List<Object>>> or(
			IFunctionInteger<T> getter) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public <T> ISharedClauseConditionAll<MODEL, List<Object>, Long, IAdhocOrClauses<MODEL, List<Object>>> or(
			IFunctionLong<T> getter) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public <T> ISharedClauseComparableStringAll<MODEL, List<Object>, IAdhocOrClauses<MODEL, List<Object>>> or(
			StringFunction<T> getter) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public <T> ISharedClauseConditionTable<MODEL, List<Object>, Integer, IAdhocAndClauses<MODEL, List<Object>>> and(
			IFunctionInteger<T> getter) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public <T> ISharedClauseConditionTable<MODEL, List<Object>, Long, IAdhocAndClauses<MODEL, List<Object>>> and(
			IFunctionLong<T> getter) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public <T> ISharedClauseComparableStringAll<MODEL, List<Object>, IAdhocAndClauses<MODEL, List<Object>>> and(
			StringFunction<T> getter) {
		// TODO Auto-generated method stub
		return null;
	}
}
