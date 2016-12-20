package com.neaterbits.query.sql.dsl.api;

final class ConditionEvaluatorComparableString extends ConditionEvaluatorComparable {

	@Override
	public Boolean onContains(ConditionStringContains condition, ConditionValuesScratch param) {
		final Object lhs = param.getLhs();
		
		return lhs == null ? false : ((String)lhs).contains((String)param.getRhs());
	}

	@Override
	public Boolean onStartsWith(ConditionStringStartsWith condition, ConditionValuesScratch param) {
		final Object lhs = param.getLhs();

		return lhs == null ? false : ((String)lhs).startsWith((String)param.getRhs());
	}
	
	@Override
	public Boolean onEndsWith(ConditionStringEndsWith condition, ConditionValuesScratch param) {
		final Object lhs = param.getLhs();

		return lhs == null ? false : ((String)lhs).endsWith((String)param.getRhs());
	}

	@Override
	public Boolean onMatches(ConditionStringMatches condition, ConditionValuesScratch param) {
		throw new UnsupportedOperationException("TODO");
	}
}
