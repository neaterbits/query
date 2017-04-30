package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

abstract class AdhocQuery_Named_Singular<MODEL> extends AdhocQuery_Named<MODEL, Object> 

	implements IAdhocLogical_Where_Or_Join_Singular<MODEL, Object, Object> {

	
	AdhocQuery_Named_Singular(Function<?, ?> aggregateGetter, EAggregateFunction aggregateFunction, EAggregateType aggregateNumericInputType, EAggregateType aggregateNumericOutputType) {
		super(aggregateGetter, aggregateFunction, aggregateNumericInputType, aggregateNumericOutputType);
	}
	
	@Override
	final AdhocConditions<MODEL, Object, ?> createConditions(int level) {
		return new AdhocConditions_Singular<>(this, level);
	}

	/**************************************************************************
	** IAdhocWhereOrJoinSingular
	**************************************************************************/
	
	@SuppressWarnings("unchecked")
	@Override
	public <E extends Enum<E>> ISharedComparison_Equality_Value<MODEL, Object, E, IAdhocLogical_And_Or_Singular<MODEL, Object, Object>> where(IFunctionEnum<Object, E> func) {
		return addConditionWhere(func);
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public ISharedComparison_Comparable_Common_Value<MODEL, Object, Integer, IAdhocLogical_And_Or_Singular<MODEL, Object, Object>> where(IFunctionInteger<Object> func) {
		return addComparativeWhere(func);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ISharedComparison_Comparable_Common_Value<MODEL, Object, BigDecimal, IAdhocLogical_And_Or_Singular<MODEL, Object, Object>> where(IFunctionBigDecimal<Object> func) {
		return addComparativeWhere(func);
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public ISharedComparison_Comparable_String_Value<MODEL, Object, IAdhocLogical_And_Or_Singular<MODEL, Object, Object>> where(IFunctionString<Object> func) {

		addWhereGetter(func);
		
		return (ISharedComparison_Comparable_String_Value)this;
	}


	@Override
	public final <JOIN_TO> IAdhocLogical_Where_Or_Join_Singular<MODEL, Object, Object> innerJoin(Collection<JOIN_TO> joinTo,
			Consumer<IAdhocJoinSub<MODEL, Object, Object, JOIN_TO>> consumer) {

		compileInnerJoin(joinTo, consumer);

		return this;
	}


	@Override
	public final <JOIN_TO> IAdhocLogical_Where_Or_Join_Singular<MODEL, Object, Object> leftJoin(Collection<JOIN_TO> joinTo,
			Consumer<IAdhocJoinSub<MODEL, Object, Object, JOIN_TO>> consumer) {

		compileLeftJoin(joinTo, consumer);
		
		return this;
	}
	
	
}
