package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

class ComparativeClauseImpl<MODEL, RESULT, R extends Comparable<R>, L extends LogicalClauses<MODEL, RESULT>> 
	extends ConditionClauseImpl<MODEL, RESULT, R, L>
	implements ComparativeClauseTable<MODEL, RESULT, R, L>,
			   ComparativeClauseAlias<MODEL, RESULT, R, L> {

	ComparativeClauseImpl(ClausesImpl<MODEL, RESULT> clause, Getter getter) {
		super(clause, getter);
	}

	@Override
	public final LogicalClauses<MODEL, RESULT> isGreaterThan(R value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new ConditionGreaterThanImpl(getter, makeLiteralValue(value)));
	}

	
	@Override
	public final LogicalClauses<MODEL, RESULT> isGreaterThan(Param<R> value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new ConditionGreaterThanImpl(getter, makeParamValue(value)));
	}

	
	@Override
	public final <T> LogicalClauses<MODEL, RESULT> isGreaterThan(Function<T, R> getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		return addCondition(new ConditionGreaterThanImpl(this.getter, makeGetterValue(getter)));
	}

	@Override
	public final LogicalClauses<MODEL, RESULT> isGreaterOrEqualTo(R value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new ConditionGreaterThanOrEqualImpl(getter, makeLiteralValue(value)));
	}

	@Override
	public final LogicalClauses<MODEL, RESULT> isGreaterOrEqualTo(Param<R> value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new ConditionGreaterThanOrEqualImpl(getter, makeParamValue(value)));
	}

	@Override
	public final <T> LogicalClauses<MODEL, RESULT> isGreaterOrEqualTo(Function<T, R> getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		return addCondition(new ConditionGreaterThanOrEqualImpl(this.getter, makeGetterValue(getter)));
	}

	@Override
	public final LogicalClauses<MODEL, RESULT> isLesserThan(R value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new ConditionLessThanImpl(getter, makeLiteralValue(value)));
	}

	@Override
	public final LogicalClauses<MODEL, RESULT> isLesserThan(Param<R> value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new ConditionLessThanImpl(getter, makeParamValue(value)));
	}

	@Override
	public final <T> LogicalClauses<MODEL, RESULT> isLesserThan(Function<T, R> getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		return addCondition(new ConditionLessThanImpl(this.getter, makeGetterValue(getter)));
	}

	@Override
	public final LogicalClauses<MODEL, RESULT> isLesserOrEqualTo(R value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new ConditionLessThanOrEqualImpl(getter, makeLiteralValue(value)));
	}

	@Override
	public final LogicalClauses<MODEL, RESULT> isLesserOrEqualTo(Param<R> value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new ConditionLessThanOrEqualImpl(getter, makeParamValue(value)));
	}

	@Override
	public final <T> LogicalClauses<MODEL, RESULT> isLesserOrEqualTo(Function<T, R> getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		return addCondition(new ConditionLessThanOrEqualImpl(this.getter, makeGetterValue(getter)));
	}

	
	// -------------------- Alias specific --------------------

	@Override
	public final LogicalClauses<MODEL, RESULT> isGreaterThan(Supplier<R> getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		return addCondition(new ConditionGreaterThanImpl(this.getter, makeGetterValue(getter)));
	}

	@Override
	public LogicalClauses<MODEL, RESULT> isGreaterOrEqualTo(Supplier<R> getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		return addCondition(new ConditionGreaterThanOrEqualImpl(this.getter, makeGetterValue(getter)));
	}

	@Override
	public LogicalClauses<MODEL, RESULT> isLesserThan(Supplier<R> getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		return addCondition(new ConditionLessThanImpl(this.getter, makeGetterValue(getter)));
	}

	@Override
	public LogicalClauses<MODEL, RESULT> isLesserOrEqualTo(Supplier<R> getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		return addCondition(new ConditionLessThanOrEqualImpl(this.getter, makeGetterValue(getter)));
	}
}
