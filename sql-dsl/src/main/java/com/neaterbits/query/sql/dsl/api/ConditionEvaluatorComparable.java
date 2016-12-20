package com.neaterbits.query.sql.dsl.api;

class ConditionEvaluatorComparable extends ConditionAdapterComparableBase<ConditionValuesScratch, Boolean> {

	@Override
	public Boolean onEqualTo(ConditionEqualToImpl condition, ConditionValuesScratch param) {
		final Comparable<Object> lhs = param.getLhsComparable();
		final Comparable<Object> rhs = param.getRhsComparable();
		
		return lhs == null ? rhs == null : lhs.compareTo(rhs) == 0;
	}

	@Override
	public Boolean onNotEqualTo(ConditionNotEqualToImpl condition, ConditionValuesScratch param) {
		final Comparable<Object> lhs = param.getLhsComparable();
		final Comparable<Object> rhs = param.getRhsComparable();

		return lhs == null ? rhs != null : lhs.compareTo(rhs) != 0;
	}

	@Override
	public Boolean onIn(ConditionInImpl condition, ConditionValuesScratch param) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public Boolean onGreaterThan(ConditionGreaterThanImpl condition, ConditionValuesScratch param) {
		final Comparable<Object> lhs = param.getLhsComparable();
		final Comparable<Object> rhs = param.getRhsComparable();

		return lhs == null ? false : lhs.compareTo(rhs) > 0;
	}

	@Override
	public Boolean onGreaterThanOrEqual(ConditionGreaterThanOrEqualImpl condition, ConditionValuesScratch param) {
		final Comparable<Object> lhs = param.getLhsComparable();
		final Comparable<Object> rhs = param.getRhsComparable();

		return lhs == null ? rhs == null : lhs.compareTo(rhs) >= 0;
	}

	@Override
	public Boolean onLessThan(ConditionLessThanImpl condition, ConditionValuesScratch param) {
		final Comparable<Object> lhs = param.getLhsComparable();
		final Comparable<Object> rhs = param.getRhsComparable();

		return lhs == null ? rhs != null : lhs.compareTo(rhs) < 0;
	}

	@Override
	public Boolean onLessThanOrEqual(ConditionLessThanOrEqualImpl condition, ConditionValuesScratch param) {
		final Comparable<Object> lhs = param.getLhsComparable();
		final Comparable<Object> rhs = param.getRhsComparable();

		return lhs == null ? rhs == null : lhs.compareTo(rhs) <= 0;
	}
}
