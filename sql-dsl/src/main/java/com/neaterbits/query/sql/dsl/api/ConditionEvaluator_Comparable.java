package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Array;
import java.util.List;

class ConditionEvaluator_Comparable extends ConditionAdapterComparableBase<ConditionValuesScratch, Boolean> {

	private static boolean comparableEquals(Comparable<Object> lhs, Comparable<Object> rhs) {
		return lhs == null ? rhs == null : lhs.compareTo(rhs) == 0;
	}
	
	@Override
	public final Boolean onIsNull(CollectedCondition_IsNull condition, ConditionValuesScratch param) {
		return param.getLhsComparable() == null;
	}



	@Override
	public final Boolean onIsNotNull(CollectedCondition_IsNotNull condition, ConditionValuesScratch param) {
		return param.getLhsComparable() != null;
	}

	@Override
	public final Boolean onEqualTo(CollectedCondition_EqualTo condition, ConditionValuesScratch param) {
		final Comparable<Object> lhs = param.getLhsComparable();
		final Comparable<Object> rhs = param.getRhsComparable();
		
		return comparableEquals(lhs, rhs);
	}

	@Override
	public final Boolean onNotEqualTo(CollectedCondition_NotEqualTo condition, ConditionValuesScratch param) {
		final Comparable<Object> lhs = param.getLhsComparable();
		final Comparable<Object> rhs = param.getRhsComparable();

		return lhs == null ? rhs != null : lhs.compareTo(rhs) != 0;
	}

	@Override
	public final Boolean onIn(CollectedCondition_In condition, ConditionValuesScratch param) {

		final Comparable<Object> lhs = param.getLhsComparable();
		
		final Object rhsValues = param.getRhs();
		
		if (rhsValues instanceof List) {
			@SuppressWarnings("unchecked")
			final List<Comparable<Object>> list = (List<Comparable<Object>>)param.getRhs();
			
			for (Comparable<Object> rhs : list) {
				final boolean equals = comparableEquals(lhs, rhs);

				if (equals) {
					return true;
				}
			}
		}
		else if (rhsValues.getClass().isArray()) {
			if (Constants.IN_AS_LIST) {
				throw new UnsupportedOperationException("arrays should jave been converted to lists");
			}
			final int len = Array.getLength(rhsValues);
			
			for (int i = 0; i < len; ++ i) {
				@SuppressWarnings("unchecked")
				final Comparable<Object> rhs = (Comparable<Object>)Array.get(rhsValues, i);
				final boolean equals = comparableEquals(lhs, rhs);

				if (equals) {
					return true;
				}
			}
		}
		else {
			throw new UnsupportedOperationException("Unexpected ths type for in-query: " + rhsValues);
		}
		
		return false;
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
