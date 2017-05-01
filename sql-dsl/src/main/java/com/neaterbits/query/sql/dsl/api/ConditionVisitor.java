package com.neaterbits.query.sql.dsl.api;

interface ConditionVisitor<T, R> {

	R onIsNull(CollectedCondition_IsNull condition, T param);
	
	R onIsNotNull(CollectedCondition_IsNotNull condition, T param);

	R onEqualTo(CollectedCondition_EqualTo condition, T param);

	R onNotEqualTo(CollectedCondition_NotEqualTo condition, T param);

	R onIn(CollectedCondition_In condition, T param);
	
	R onGreaterThan(CollectedCondition_GreaterThan condition, T param);

	R onGreaterThanOrEqual(CollectedCondition_GreaterThanOrEqual condition, T param);

	R onLessThan(CollectedCondition_LessThan condition, T param);

	R onLessThanOrEqual(CollectedCondition_LessThanOrEqual condition, T param);
	
	R onStartsWith(CollectedCondition_StringStartsWith condition, T param);

	R onEndsWith(CollectedCondition_StringEndsWith condition, T param);

	R onContains(CollectedCondition_StringContains condition, T param);

	R onMatches(CollectedCondition_StringMatches condition, T param);
	
	R onNested(CollectedCondition_Nested condition, T param);
}

