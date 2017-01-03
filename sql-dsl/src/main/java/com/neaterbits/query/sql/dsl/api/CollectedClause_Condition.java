package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

class CollectedClause_Condition<MODEL, RESULT, R, L extends ISharedLogicalClauses<MODEL, RESULT>>
	implements ISharedClauseConditionNamed<MODEL, RESULT, R, L>,
			   ISharedConditionClauseAlias<MODEL, RESULT, R, L> {

	private final CollectedClauses<MODEL, RESULT> clause;
	final Getter getter;

	CollectedClause_Condition(CollectedClauses<MODEL, RESULT> clause, Getter getter) {

		if (clause == null) {
			throw new IllegalArgumentException("clause == null");
		}

		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		this.clause = clause;
		this.getter = getter;
	}

	final <V> ConditionValue_Literal_Base<V> makeLiteralValue(V value) {
		return new ConditionValue_Literal_Any<>(value);
	}

	final <V> ConditionValue_Param makeParamValue(Param<V> param) {
		return new ConditionValue_Param(param);
	}

	final <V> ConditionValue_Getter makeGetterValue(Function<?, V> value) {
		return new ConditionValue_Getter(CollectedClauses.makeGetter(value));
	}

	final <V> ConditionValue_Getter makeGetterValue(Supplier<V> value) {
		return new ConditionValue_Getter(CollectedClauses.makeGetter(value));
	}

	final L addCondition(CollectedCondition condition) {
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
		
		return addCondition(new CollectedCondition_EqualTo(getter, makeLiteralValue(other)));
	}

	@Override
	public final L isEqualTo(Param<R> other) {
		
		if (other == null) {
			throw new IllegalArgumentException("other == null");
		}
		
		return addCondition(new CollectedCondition_EqualTo(getter, makeParamValue(other)));
	}

	/* Only join in Join constructs
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
	*/

	@Override
	public final L isNotEqualTo(R other) {

		if (other == null) {
			throw new IllegalArgumentException("other == null");
		}

		return addCondition(new CollectedCondition_NotEqualTo(getter, makeLiteralValue(other)));
	}

	@Override
	public final L isNotEqualTo(Param<R> other) {

		if (other == null) {
			throw new IllegalArgumentException("other == null");
		}

		return addCondition(new CollectedCondition_NotEqualTo(getter, makeParamValue(other)));
	}

	/*
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
	*/

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

		return addCondition(new CollectedCondition_In(getter, new ConditionValue_Array(values)));
	}
}
