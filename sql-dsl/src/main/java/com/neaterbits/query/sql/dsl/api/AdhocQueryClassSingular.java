package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Function;

abstract class AdhocQueryClassSingular<MODEL> extends AdhocQueryClass<MODEL, Object> 

	implements IAdhocWhereOrJoinSingular<MODEL, Object, Object>,
			   IAdhocAndOrLogicalClausesSingular<MODEL, Object>
{

	
	AdhocQueryClassSingular(Function<?, ?> aggregateGetter, EAggregateFunction aggregateFunction, ENumericType aggregateNumericInputType, ENumericType aggregateNumericOutputType) {
		super(aggregateGetter, aggregateFunction, aggregateNumericInputType, aggregateNumericOutputType);
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
		addCondition(null, func);
		
		return (ISharedClauseComparableStringValue)this;
	}
	
}
