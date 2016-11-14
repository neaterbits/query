package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

class ComparativeClauseImpl<MODEL, RESULT, R extends Comparable<R>, L extends LogicalClauses<MODEL, RESULT>> 
	extends ConditionClauseImpl<MODEL, RESULT, R, L>
	implements ComparativeClause<MODEL, RESULT, R, L> {

	ComparativeClauseImpl(ClausesImpl<MODEL, RESULT> clause, Function<?, ?> getter) {
		super(clause, getter);
	}

	@Override
	public final LogicalClauses<MODEL, RESULT> isGreaterThan(R value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new ConditionGreaterThanImpl(getter, makeValue(value)));
	}

	@Override
	public final <T> LogicalClauses<MODEL, RESULT> isGreaterThan(Function<T, R> getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		return addCondition(new ConditionGreaterThanImpl(getter, makeValue(getter)));
	}

	@Override
	public final LogicalClauses<MODEL, RESULT> isGreaterOrEqualTo(RESULT value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new ConditionGreaterThanOrEqualImpl(getter, makeValue(value)));
	}

	@Override
	public final <T> LogicalClauses<MODEL, RESULT> isGreaterOrEqualTo(Function<T, R> getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		return addCondition(new ConditionGreaterThanOrEqualImpl(getter, makeValue(getter)));
	}

	@Override
	public final LogicalClauses<MODEL, RESULT> isLesserThan(R value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new ConditionLessThanImpl(getter, makeValue(value)));
	}

	@Override
	public final <T> LogicalClauses<MODEL, RESULT> isLesserThan(Function<T, R> getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		return addCondition(new ConditionLessThanImpl(getter, makeValue(getter)));
	}

	@Override
	public final LogicalClauses<MODEL, RESULT> isLesserOrEqualTo(R value) {
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		return addCondition(new ConditionLessThanOrEqualImpl(getter, makeValue(value)));
	}

	@Override
	public final <T> LogicalClauses<MODEL, RESULT> isLesserOrEqualTo(Function<T, R> getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}
		
		return addCondition(new ConditionLessThanOrEqualImpl(getter, makeValue(getter)));
	}
}
