package com.neaterbits.query.sql.dsl.api;

public abstract class ConditionAdapterComparableBase<T, R> implements ConditionVisitor<T, R> {

	@Override
	public R onStartsWith(ConditionStringStartsWith condition, T param) {
		throw new UnsupportedOperationException("Not supporyed for numeric types");
	}

	@Override
	public R onEndsWith(ConditionStringEndsWith condition, T param) {
		throw new UnsupportedOperationException("Not supporyed for numeric types");
	}

	@Override
	public R onContains(ConditionStringContains condition, T param) {
		throw new UnsupportedOperationException("Not supporyed for numeric types");
	}

	@Override
	public R onMatches(ConditionStringMatches condition, T param) {
		throw new UnsupportedOperationException("Not supporyed for numeric types");
	}
}
