package com.neaterbits.query.sql.dsl.api;

interface ConditionValueVisitor<T, R> {

	R onLiteralAny(ConditionValue_Literal_Any<?> value, T param);

	R onLiteralString(ConditionValue_Literal_String value, T param);

	R onArray(ConditionValue_Array value, T param);

	R onList(ConditionValue_List value, T param);

	R onGetter(ConditionValue_Getter value, T param);

	R onParam(ConditionValue_Param value, T param);

	R onFieldReference(ConditionValue_FieldRerefence value, T param);
}
