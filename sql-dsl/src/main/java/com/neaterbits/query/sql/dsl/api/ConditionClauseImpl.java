package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

class ConditionClauseImpl<MODEL, RESULT, R, L extends LogicalClauses<MODEL, RESULT>>
	implements ConditionClause<MODEL, RESULT, R, L> {

	private final ClausesImpl<MODEL, RESULT> clause;
	final Function<?, ?> getter;

	ConditionClauseImpl(ClausesImpl<MODEL, RESULT> clause, Function<?, ?> getter) {

		if (clause == null) {
			throw new IllegalArgumentException("clause == null");
		}

		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		this.clause = clause;
		this.getter = getter;
	}

	final <V> ConditionValueImpl makeValue(V value) {
		return new ConditionValueLiteralImpl(value);
	}

	final L addCondition(ConditionImpl condition) {
		if (condition == null) {
			throw new IllegalArgumentException("condition == null");
		}

		clause.clauseCollector.add(clause, condition);
		
		return getLogicalClause();
	}
	
	
	@SuppressWarnings("unchecked")
	private L getLogicalClause() {
		return (L)clause;
	}

	@Override
	public final L isEqualTo(R other) {
		
		if (other == null) {
			throw new IllegalArgumentException("other == null");
		}
		
		return addCondition(new ConditionEqualToImpl(getter, makeValue(other)));
	}

	@Override
	public final <T> L isEqualTo(Function<T, R> other) {
		
		if (other == null) {
			throw new IllegalArgumentException("other == null");
		}
		
		return addCondition(new ConditionEqualToImpl(getter, makeValue(other)));
	}

	@Override
	public final L isNotEqualTo(R other) {

		if (other == null) {
			throw new IllegalArgumentException("other == null");
		}

		return addCondition(new ConditionNotEqualToImpl(getter, makeValue(other)));
	}

	@Override
	public final <T> L isNotEqualTo(Function<T, R> other) {

		if (other == null) {
			throw new IllegalArgumentException("other == null");
		}

		return addCondition(new ConditionNotEqualToImpl(getter, makeValue(other)));
	}

	@Override
	@SafeVarargs
	public final L in(R... values) {

		if (values.length == 0) {
			throw new IllegalArgumentException("no values");
		}

		for (int i = 0; i < values.length; ++ i) {
			if (values[i] == null) {
				throw new IllegalArgumentException("value at " + i + " is null");
			}
		}

		return addCondition(new ConditionInImpl(getter, new ConditionValueArrayImpl(values)));
	}
}
