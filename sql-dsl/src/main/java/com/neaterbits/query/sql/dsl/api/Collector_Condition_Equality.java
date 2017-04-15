package com.neaterbits.query.sql.dsl.api;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

abstract class Collector_Condition_Equality<MODEL, RESULT, R, L extends ISharedLogical_Base<MODEL, RESULT>>
	implements ISharedCondition_Equality_Named<MODEL, RESULT, R, L>,
			   ISharedCondition_Equality_Alias<MODEL, RESULT, R, L> {

	private final Collector_Conditions_GroupBy<MODEL, RESULT, ?> clause;
	final Expression lhs;

	@Deprecated
	final CollectedFunctions functions;
	@Deprecated
	final Getter getter;

	Collector_Condition_Equality(Collector_Conditions_GroupBy<MODEL, RESULT, ?> clause, Expression expression) {

		if (clause == null) {
			throw new IllegalArgumentException("clause == null");
		}
		
		if (expression == null) {
			throw new IllegalArgumentException("expression == null");
		}

		this.clause = clause;
		this.lhs = expression;
		
		this.functions = null;
		this.getter = null;
	}
	
	final <V> ConditionValue_Literal_Base<V> makeLiteralValue(V value) {
		return new ConditionValue_Literal_Any<>(value);
	}

	final <V> ConditionValue_Param makeParamValue(Param<V> param) {
		return new ConditionValue_Param(param);
	}

	final <V> ConditionValue_Getter makeGetterValue(Function<?, V> value) {
		return new ConditionValue_Getter(Collector_Conditions_GroupBy.makeGetter(value));
	}

	final <V> ConditionValue_Getter makeGetterValue(Supplier<V> value) {
		return new ConditionValue_Getter(Collector_Conditions_GroupBy.makeGetter(value));
	}

	final L addCondition(CollectedCondition_NonNested condition) {
		
		// Call setter to pass on functions
		if (functions != null) {
			condition.setFunctions(functions);
		}

		return addConditionInt(condition);
	}
	
	final L addConditionInt(CollectedCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException("condition == null");
		}

		clause.clauseCollector.add(condition);
		
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
		
		return addCondition(new CollectedCondition_EqualTo(lhs, makeLiteralValue(other)));
	}

	@Override
	public final L isEqualTo(ValParam<R> other) {
		
		if (other == null) {
			throw new IllegalArgumentException("other == null");
		}
		
		return addCondition(new CollectedCondition_EqualTo(lhs, makeParamValue(other)));
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

		return addCondition(new CollectedCondition_NotEqualTo(lhs, makeLiteralValue(other)));
	}

	@Override
	public final L isNotEqualTo(ValParam<R> other) {

		if (other == null) {
			throw new IllegalArgumentException("other == null");
		}

		return addCondition(new CollectedCondition_NotEqualTo(lhs, makeParamValue(other)));
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

		final ConditionValue_Collection conditionValue =
				Constants.IN_AS_LIST 
					? new ConditionValue_List(Arrays.asList(values))
					: new ConditionValue_Array(values);
						
		
		return addCondition(new CollectedCondition_In(lhs, conditionValue));
	}

	@Override
	public final L in(InParam<R> param) {
		return addCondition(new CollectedCondition_In(lhs, new ConditionValue_Param(param)));
	}
}
