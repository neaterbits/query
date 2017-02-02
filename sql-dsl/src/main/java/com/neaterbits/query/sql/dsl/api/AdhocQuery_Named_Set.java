package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

final class AdhocQuery_Named_Set<MODEL>
		extends AdhocQuery_Named_Collection<MODEL, Set<Object>>

		implements IAdhocWhereOrJoinSet<MODEL, Object, Set<Object>>,
				   IAdhocEnd_Set<MODEL, Object, Set<Object>> {

	AdhocQuery_Named_Set(Collection<?> coll) {
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
	 ** IAdhocWhereOrJoinSet
	 **************************************************************************/

	@SuppressWarnings("unchecked")
	@Override
	public ISharedCondition_Comparable_Common_Value<MODEL, Set<Object>, Integer, IAdhocLogical_And_Or_Set<MODEL, Object, Set<Object>>> where(IFunctionInteger<Object> func) {
		return addComparativeWhere(func);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E extends Enum<E>> ISharedCondition_Equality_Value<MODEL, Set<Object>, E, IAdhocLogical_And_Or_Set<MODEL, Object, Set<Object>>> where(IFunctionEnum<Object, E> func) {
		return addConditionWhere(func);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ISharedCondition_Comparable_Common_Value<MODEL, Set<Object>, BigDecimal, IAdhocLogical_And_Or_Set<MODEL, Object, Set<Object>>> where(IFunctionBigDecimal<Object> func) {
		return addComparativeWhere(func);
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ISharedCondition_Comparable_String_Value<MODEL, Set<Object>, IAdhocLogical_And_Or_Set<MODEL, Object, Set<Object>>> where(StringFunction<Object> func) {

		return (ISharedCondition_Comparable_String_Value)addWhereGetter(func);
	}
	
	@Override
	public IAdhocFunctions_Initial<
			MODEL,
			Set<Object>,
			Object,
			IAdhocLogical_And_Or<MODEL, Set<Object>, Object>,
			ISharedCondition_Comparable_Common_Value<MODEL, Set<Object>, Integer, IAdhocLogical_And_Or<MODEL, Set<Object>, Object>>,
					ISharedCondition_Comparable_Common_Value<MODEL, Set<Object>, Long, IAdhocLogical_And_Or<MODEL, Set<Object>, Object>>,
			ISharedCondition_Comparable_String_Value<MODEL, Set<Object>, IAdhocLogical_And_Or<MODEL, Set<Object>, Object>>> where() {
		
		return addWhere();
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
