package com.neaterbits.query.sql.dsl.api;

class ConditionEvaluator_Comparable extends ConditionAdapterComparableBase<ConditionValuesScratch, Boolean> {

	@Override
	public final Boolean onEqualTo(CollectedCondition_EqualTo condition, ConditionValuesScratch param) {
		final Comparable<Object> lhs = param.getLhsComparable();
		final Comparable<Object> rhs = param.getRhsComparable();
		
		return lhs == null ? rhs == null : lhs.compareTo(rhs) == 0;
	}

	@Override
	public final Boolean onNotEqualTo(CollectedCondition_NotEqualTo condition, ConditionValuesScratch param) {
		final Comparable<Object> lhs = param.getLhsComparable();
		final Comparable<Object> rhs = param.getRhsComparable();

		return lhs == null ? rhs != null : lhs.compareTo(rhs) != 0;
	}

	@Override
	public final Boolean onIn(CollectedCondition_In condition, ConditionValuesScratch param) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final Boolean onGreaterThan(CollectedCondition_GreaterThan condition, ConditionValuesScratch param) {
		final Comparable<Object> lhs = param.getLhsComparable();
		final Comparable<Object> rhs = param.getRhsComparable();

		return lhs == null ? false : lhs.compareTo(rhs) > 0;
	}

	@Override
	public final Boolean onGreaterThanOrEqual(CollectedCondition_GreaterThanOrEqual condition, ConditionValuesScratch param) {
		final Comparable<Object> lhs = param.getLhsComparable();
		final Comparable<Object> rhs = param.getRhsComparable();

		return lhs == null ? rhs == null : lhs.compareTo(rhs) >= 0;
	}

	@Override
	public final Boolean onLessThan(CollectedCondition_LessThan condition, ConditionValuesScratch param) {
		final Comparable<Object> lhs = param.getLhsComparable();
		final Comparable<Object> rhs = param.getRhsComparable();

		return lhs == null ? rhs != null : lhs.compareTo(rhs) < 0;
	}

	@Override
	public final Boolean onLessThanOrEqual(CollectedCondition_LessThanOrEqual condition, ConditionValuesScratch param) {
		final Comparable<Object> lhs = param.getLhsComparable();
		final Comparable<Object> rhs = param.getRhsComparable();

		return lhs == null ? rhs == null : lhs.compareTo(rhs) <= 0;
	}
}
