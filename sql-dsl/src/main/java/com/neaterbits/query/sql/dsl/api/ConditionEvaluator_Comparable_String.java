package com.neaterbits.query.sql.dsl.api;

final class ConditionEvaluator_Comparable_String extends ConditionEvaluator_Comparable {

	@Override
	public Boolean onContains(CollectedCondition_StringContains condition, ConditionValuesScratch param) {
		final Object lhs = param.getLhs();
		
		return lhs == null ? false : ((String)lhs).contains((String)param.getRhs());
	}

	@Override
	public Boolean onStartsWith(CollectedCondition_StringStartsWith condition, ConditionValuesScratch param) {
		final Object lhs = param.getLhs();

		return lhs == null ? false : ((String)lhs).startsWith((String)param.getRhs());
	}
	
	@Override
	public Boolean onEndsWith(CollectedCondition_StringEndsWith condition, ConditionValuesScratch param) {
		final Object lhs = param.getLhs();

		return lhs == null ? false : ((String)lhs).endsWith((String)param.getRhs());
	}

	@Override
	public Boolean onMatches(CollectedCondition_StringMatches condition, ConditionValuesScratch param) {
		throw new UnsupportedOperationException("TODO");
	}
}
