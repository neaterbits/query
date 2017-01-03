package com.neaterbits.query.sql.dsl.api;

abstract class ConditionValue extends CollectedItem {

	abstract <T, R> R visit(ConditionValueVisitor<T, R> visitor, T param);
	
}
