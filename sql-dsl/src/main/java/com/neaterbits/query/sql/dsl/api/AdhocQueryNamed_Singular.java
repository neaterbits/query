package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

abstract class AdhocQueryNamed_Singular<MODEL> extends AdhocQueryNamed<MODEL, Object> 

	implements IAdhocWhereOrJoinSingular<MODEL, Object, Object> {

	
	AdhocQueryNamed_Singular(Function<?, ?> aggregateGetter, EAggregateFunction aggregateFunction, ENumericType aggregateNumericInputType, ENumericType aggregateNumericOutputType) {
		super(aggregateGetter, aggregateFunction, aggregateNumericInputType, aggregateNumericOutputType);
	}
	
	@Override
	final AdhocConditions<MODEL, Object, ?> createConditions(int level) {
		return new AdhocConditionsSingular<>(this, level);
	}

	/**************************************************************************
	** IAdhocWhereOrJoinSingular
	**************************************************************************/
	
	@SuppressWarnings("unchecked")
	@Override
	public <E extends Enum<E>> ISharedClauseConditionValue<MODEL, Object, E, IAdhocAndOrLogicalClausesSingular<MODEL, Object>> where(IFunctionEnum<Object, E> func) {
		return addConditionWhere(func);
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public ISharedClauseComparableCommonValue<MODEL, Object, Integer, IAdhocAndOrLogicalClausesSingular<MODEL, Object>> where(IFunctionInteger<Object> func) {
		return addComparativeWhere(func);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ISharedClauseComparableCommonValue<MODEL, Object, BigDecimal, IAdhocAndOrLogicalClausesSingular<MODEL, Object>> where(IFunctionBigDecimal<Object> func) {
		return addComparativeWhere(func);
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public ISharedClauseComparableStringValue<MODEL, Object, IAdhocAndOrLogicalClausesSingular<MODEL, Object>> where(StringFunction<Object> func) {

		addWhere(func);
		
		return (ISharedClauseComparableStringValue)this;
	}


	@Override
	public final <JOIN_TO> IAdhocWhereOrJoinSingular<MODEL, Object, Object> innerJoin(Collection<JOIN_TO> joinTo,
			Consumer<IAdhocJoinSub<MODEL, Object, Object, JOIN_TO>> consumer) {

		compileInnerJoin(joinTo, consumer);

		return this;
	}


	@Override
	public final <JOIN_TO> IAdhocWhereOrJoinSingular<MODEL, Object, Object> leftJoin(Collection<JOIN_TO> joinTo,
			Consumer<IAdhocJoinSub<MODEL, Object, Object, JOIN_TO>> consumer) {

		compileLeftJoin(joinTo, consumer);
		
		return this;
	}
	
	
}
