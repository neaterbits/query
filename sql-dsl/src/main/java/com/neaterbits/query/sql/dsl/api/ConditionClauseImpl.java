package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

class ConditionClauseImpl<MODEL, RESULT, R, L extends LogicalClauses<MODEL, RESULT>>
	implements ConditionClauseTable<MODEL, RESULT, R, L>,
			   ConditionClauseAlias<MODEL, RESULT, R, L>{

	private final ClausesImpl<MODEL, RESULT> clause;
	final Getter getter;

	ConditionClauseImpl(ClausesImpl<MODEL, RESULT> clause, Getter getter) {

		if (clause == null) {
			throw new IllegalArgumentException("clause == null");
		}

		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		this.clause = clause;
		this.getter = getter;
	}

	final <V> BaseConditionValueLiteralImpl<V> makeLiteralValue(V value) {
		return new ConditionValueLiteralAnyImpl<>(value);
	}

	final <V> ConditionValueParamImpl makeParamValue(Param<V> param) {
		return new ConditionValueParamImpl(param);
	}

	final <V> ConditionValueGetterImpl makeGetterValue(Function<?, V> value) {
		return new ConditionValueGetterImpl(ClausesImpl.makeGetter(value));
	}

	final <V> ConditionValueGetterImpl makeGetterValue(Supplier<V> value) {
		return new ConditionValueGetterImpl(ClausesImpl.makeGetter(value));
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
		
		return addCondition(new ConditionEqualToImpl(getter, makeLiteralValue(other)));
	}

	@Override
	public final L isEqualTo(Param<R> other) {
		
		if (other == null) {
			throw new IllegalArgumentException("other == null");
		}
		
		return addCondition(new ConditionEqualToImpl(getter, makeParamValue(other)));
	}

	@Override
	public final <T> L isEqualTo(Function<T, R> other) {
		
		if (other == null) {
			throw new IllegalArgumentException("other == null");
		}
		
		return addCondition(new ConditionEqualToImpl(getter, makeGetterValue(other)));
	}
	

	@Override
	public final L isEqualTo(Supplier<R> other) {

		if (other == null) {
			throw new IllegalArgumentException("other == null");
		}
		
		return addCondition(new ConditionEqualToImpl(getter, makeGetterValue(other)));
	}

	@Override
	public final L isNotEqualTo(R other) {

		if (other == null) {
			throw new IllegalArgumentException("other == null");
		}

		return addCondition(new ConditionNotEqualToImpl(getter, makeLiteralValue(other)));
	}

	@Override
	public final L isNotEqualTo(Param<R> other) {

		if (other == null) {
			throw new IllegalArgumentException("other == null");
		}

		return addCondition(new ConditionNotEqualToImpl(getter, makeParamValue(other)));
	}

	@Override
	public final <T> L isNotEqualTo(Function<T, R> other) {

		if (other == null) {
			throw new IllegalArgumentException("other == null");
		}

		return addCondition(new ConditionNotEqualToImpl(getter, makeGetterValue(other)));
	}

	
	@Override
	public final L isNotEqualTo(Supplier<R> other) {
		if (other == null) {
			throw new IllegalArgumentException("other == null");
		}

		return addCondition(new ConditionNotEqualToImpl(getter, makeGetterValue(other)));
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
