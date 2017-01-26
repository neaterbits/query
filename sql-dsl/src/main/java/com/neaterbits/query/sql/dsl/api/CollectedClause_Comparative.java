package com.neaterbits.query.sql.dsl.api;

class CollectedClause_Comparative<MODEL, RESULT, R extends Comparable<R>, L extends ISharedLogicalClauses<MODEL, RESULT>> 
	extends CollectedClause_Condition<MODEL, RESULT, R, L>
	implements ISharedClauseComparableCommonAll_Compilable<MODEL, RESULT, R, L> {

	CollectedClause_Comparative(CollectedClauses<MODEL, RESULT> clause, Getter getter) {
		super(clause, getter);
	}

	@Override
	public final L isGreaterThan(R value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new CollectedCondition_GreaterThan(getter, makeLiteralValue(value)));
	}

	
	@Override
	public final L isGreaterThan(ValParam<R> value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new CollectedCondition_GreaterThan(getter, makeParamValue(value)));
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
		
		return addCondition(new CollectedCondition_GreaterThanOrEqual(getter, makeLiteralValue(value)));
	}

	@Override
	public final L isGreaterOrEqualTo(ValParam<R> value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new CollectedCondition_GreaterThanOrEqual(getter, makeParamValue(value)));
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
		
		return addCondition(new CollectedCondition_LessThan(getter, makeLiteralValue(value)));
	}

	@Override
	public final L isLesserThan(ValParam<R> value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new CollectedCondition_LessThan(getter, makeParamValue(value)));
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
		
		return addCondition(new CollectedCondition_LessThanOrEqual(getter, makeLiteralValue(value)));
	}

	@Override
	public final L isLesserOrEqualTo(ValParam<R> value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new CollectedCondition_LessThanOrEqual(getter, makeParamValue(value)));
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
