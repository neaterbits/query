package com.neaterbits.query.sql.dsl.api;

public abstract class ConditionAdapterComparableBase<T, R> implements ConditionVisitor<T, R> {

	@Override
	public R onStartsWith(CollectedCondition_StringStartsWith condition, T param) {
		throw new UnsupportedOperationException("Not supported for numeric types");
	}

	@Override
	public R onEndsWith(CollectedCondition_StringEndsWith condition, T param) {
		throw new UnsupportedOperationException("Not supported for numeric types");
	}

	@Override
	public R onContains(CollectedCondition_StringContains condition, T param) {
		throw new UnsupportedOperationException("Not supported for numeric types");
	}

	@Override
	public R onMatches(CollectedCondition_StringMatches condition, T param) {
		throw new UnsupportedOperationException("Not supported for numeric types");
	}

	@Override
	public R onNested(CollectedCondition_Nested condition, T param) {
		throw new UnsupportedOperationException("Not supported for numeric types");
	}
}
