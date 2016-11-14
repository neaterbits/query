package com.neaterbits.query.sql.dsl.api;

interface ConditionVisitor<T, R> {

	R onEqualTo(ConditionEqualToImpl condition, T param);

	R onNotEqualTo(ConditionNotEqualToImpl condition, T param);

	R onIn(ConditionInImpl condition, T param);
	
	R onGreaterThan(ConditionGreaterThanImpl condition, T param);

	R onGreaterThanOrEqual(ConditionGreaterThanOrEqualImpl condition, T param);

	R onLessThan(ConditionLessThanImpl condition, T param);

	R onLessThanOrEqual(ConditionLessThanOrEqualImpl condition, T param);
	
	R onStartsWith(ConditionStringStartsWith condition, T param);

	R onEndsWith(ConditionStringEndsWith condition, T param);

	R onContains(ConditionStringContains condition, T param);

	R onMatches(ConditionStringMatches condition, T param);
}

