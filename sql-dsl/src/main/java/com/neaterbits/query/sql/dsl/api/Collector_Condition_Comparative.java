package com.neaterbits.query.sql.dsl.api;

class Collector_Condition_Comparative<MODEL, RESULT, R extends Comparable<R>, L extends ISharedLogical_Base<MODEL, RESULT>> 
	extends Collector_Condition_Equality<MODEL, RESULT, R, L>
	implements ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, R, L> {

	
	Collector_Condition_Comparative(Collector_Conditions_GroupBy<MODEL, RESULT, ?> clause, Expression expression) {
		super(clause, expression);
	}

	@Override
	public final L isGreaterThan(R value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new CollectedCondition_GreaterThan(lhs, makeLiteralValue(value)));
	}

	
	@Override
	public final L isGreaterThan(ValParam<R> value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new CollectedCondition_GreaterThan(lhs, makeParamValue(value)));
	}

	/* ONLY in Joins
	@Override
	public final <T> L isGreaterThan(Function<T, R> getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		return addCondition(new ConditionGreaterThanImpl(this.getter, makeGetterValue(getter)));
	}
	*/

	@Override
	public final L isGreaterOrEqualTo(R value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new CollectedCondition_GreaterThanOrEqual(lhs, makeLiteralValue(value)));
	}

	@Override
	public final L isGreaterOrEqualTo(ValParam<R> value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new CollectedCondition_GreaterThanOrEqual(lhs, makeParamValue(value)));
	}

	/* ONLY in Joins
	@Override
	public final <T> L isGreaterOrEqualTo(Function<T, R> getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		return addCondition(new ConditionGreaterThanOrEqualImpl(this.getter, makeGetterValue(getter)));
	}
	*/

	@Override
	public final L isLessThan(R value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new CollectedCondition_LessThan(lhs, makeLiteralValue(value)));
	}

	@Override
	public final L isLessThan(ValParam<R> value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new CollectedCondition_LessThan(lhs, makeParamValue(value)));
	}

	/* ONLY in Joins
	@Override
	public final <T> L isLesserThan(Function<T, R> getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		return addCondition(new ConditionLessThanImpl(this.getter, makeGetterValue(getter)));
	}
	*/

	@Override
	public final L isLessOrEqualTo(R value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new CollectedCondition_LessThanOrEqual(lhs, makeLiteralValue(value)));
	}

	@Override
	public final L isLessOrEqualTo(ValParam<R> value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new CollectedCondition_LessThanOrEqual(lhs, makeParamValue(value)));
	}

	/* ONLY in Joins
	@Override
	public final <T> L isLesserOrEqualTo(Function<T, R> getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		return addCondition(new ConditionLessThanOrEqualImpl(this.getter, makeGetterValue(getter)));
	}
	*/

	
	// -------------------- Alias specific --------------------

	/* ONLY in Joins
	@Override
	public final L isGreaterThan(Supplier<R> getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		return addCondition(new ConditionGreaterThanImpl(this.getter, makeGetterValue(getter)));
	}

	@Override
	public L isGreaterOrEqualTo(Supplier<R> getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		return addCondition(new ConditionGreaterThanOrEqualImpl(this.getter, makeGetterValue(getter)));
	}

	@Override
	public L isLesserThan(Supplier<R> getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		return addCondition(new ConditionLessThanImpl(this.getter, makeGetterValue(getter)));
	}

	@Override
	public L isLesserOrEqualTo(Supplier<R> getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		return addCondition(new ConditionLessThanOrEqualImpl(this.getter, makeGetterValue(getter)));
	}
	*/
}
