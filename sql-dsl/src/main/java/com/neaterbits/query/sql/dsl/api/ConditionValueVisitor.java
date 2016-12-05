package com.neaterbits.query.sql.dsl.api;

interface ConditionValueVisitor<T, R> {

	R onLiteralAny(ConditionValueLiteralAnyImpl<?> value, T param);

	R onLiteralString(ConditionValueLiteralStringImpl value, T param);

	R onArray(ConditionValueArrayImpl value, T param);

	R onGetter(ConditionValueGetterImpl value, T param);

	R onParam(ConditionValueParamImpl value, T param);
}
